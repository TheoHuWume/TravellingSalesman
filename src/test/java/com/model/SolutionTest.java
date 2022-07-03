package com.model;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class SolutionTest {
	
	@Test
	public void mutationTest2Cities() {

		City a = new City("1", 1.0, 1.0);
		City b = new City("2", 2.0, 2.0);
		
		City[] parameter = { a, b };
		Solution s = new Solution(parameter);
		
		s.mutation();
		
		City[] expected = { b, a };
		
		City[] found = s.getPath();
		assertTrue("Mutation failed", Arrays.equals(found, expected));
	}

	@Test
	public void mutationTest3Cities() {
		
		City a = new City("1", 1.0, 1.0);
		City b = new City("2", 2.0, 2.0);
		City c = new City("3", 3.0, 3.0);
		
		City[] parameter = { a, b, c };
		Solution s = new Solution(parameter);
		
		s.mutation();
		
		City[] expected1 = {b, a, c};
		City[] expected2 = {c, b, a};
		City[] expected3 = {a, c, b};
		
		City[] found = s.getPath();
		assertTrue("Mutation failed", Arrays.equals(found, expected1) || Arrays.equals(found, expected2) || Arrays.equals(found, expected3));
	}
	
	@Test
	public void fitnessTest() {
		fail("Not yet implemented");
	}
	
	@Test
	public void breedTwoSolutionsTest() {
		fail("Not yet implemented");
	}
	
}
