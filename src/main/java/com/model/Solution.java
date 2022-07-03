package com.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Solution {

	private City[] path;

	///

	/**
	 * 
	 * @param parent1
	 * @param parent2
	 * @return
	 * @throws Exception
	 */
	public static Solution[] breedTwoSolutions(Solution parent1, Solution parent2) throws Exception {
		int len = parent1.getPath().length;
		if (len != parent2.getPath().length) {
			throw new Exception("Cannot breed solutions of different lengths");
		}

		Solution[] children = { new Solution(new City[len]), new Solution(new City[len]) };

		/// Breed interval

		int a = 0;
		int b = 0;

		while (b - a <= 1 || b - a > len / 2) {
			a = ThreadLocalRandom.current().nextInt(0, len);
			b = ThreadLocalRandom.current().nextInt(0, len);
		}
		
		/// Variables

		City[] child1 = parent1.getPath().clone();
		City[] child2 = parent2.getPath().clone();
		City[] subChild1 = Arrays.copyOfRange(child1, a, b);
		City[] subChild2 = Arrays.copyOfRange(child2, a, b);
		
		ArrayList<City> redundantIn1 = new ArrayList<>();
		ArrayList<City> redundantIn2 = new ArrayList<>();
		ArrayList<City> missingFrom1 = new ArrayList<>();
		ArrayList<City> missingFrom2 = new ArrayList<>();

		/// Prepare fixes
		
		for (int i = 0; i < b - a; i++) {
			redundantIn1.add(subChild2[i]);
			redundantIn2.add(subChild1[i]);
			missingFrom1.add(subChild1[i]);
			missingFrom2.add(subChild2[i]);
		}
		
		for (int i = 0; i < b - a; i++) {
			redundantIn1.remove(subChild1[i]);
			redundantIn2.remove(subChild2[i]);
			missingFrom1.remove(subChild2[i]);
			missingFrom2.remove(subChild1[i]);
		}
		
		/// Replace
		
		for (int i = a; i < b; i++) {
			child1[i] = subChild2[i - a];
			child2[i] = subChild1[i - a];
		}

		/// Fix redundancies
		
		for (int i = 0; i < redundantIn1.size(); i++) {
			int j = 0; 
			while (child1[j] != redundantIn1.get(i)) { 
				j++;
				
				if (j == a) {
					j = b;
				}
			}
			child1[j] = missingFrom1.get(i);
			
			int k = 0; 
			while (child2[k] != redundantIn2.get(i)) { 
				k++;
				
				if (k == a) {
					k = b;
				}
			}
			child2[k] = missingFrom2.get(i);
		}
		
		children[0] = new Solution(child1);
		children[1] = new Solution(child2);
		
		return children;
	}

	/**
	 * 
	 */
	public void mutation() {
		int a = 0;
		int b = 0;

		while (a == b) {
			a = ThreadLocalRandom.current().nextInt(0, this.path.length);
			b = ThreadLocalRandom.current().nextInt(0, this.path.length);
		}

		City temp = this.path[a];
		this.path[a] = this.path[b];
		this.path[b] = temp;
	}

	/**
	 * 
	 * @return
	 */
	public int fitness() {

		int distance = 0;

		for (int i = 0; i < path.length - 1; i++) {
			distance += path[i].distance(path[i + 1]);
		}

		return distance;
	}

	//////

	public Solution(City[] path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return Arrays.toString(path);
	}

	///

	public City[] getPath() {
		return path;
	}

	public void setPath(City[] path) {
		this.path = path;
	}

}
