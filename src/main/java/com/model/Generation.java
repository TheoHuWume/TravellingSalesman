package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Generation {

	public static final int MUTATION_PROBABILITY = 15; // %

	private List<Solution> batch;

	///

	/**
	 * 
	 * @param previous
	 * @return
	 * @throws Exception
	 */
	public static Generation breedNextGeneration(Generation previous) throws Exception {
		List<Solution> parents = Generation.naturalSelection(previous);
		List<Solution> newBatch = new ArrayList<>();

		for (int i = 0; i < parents.size() / 2; i++) {
			Solution[] children = Solution.breedTwoSolutions(parents.get(2 * i), parents.get(2 * i + 1));
			newBatch.add(children[0]);
			newBatch.add(children[1]);
		}

		newBatch.addAll(parents);

		return new Generation(newBatch);
	}

	/**
	 * Compare couple of solutions to select only half of the population (tournament
	 * selection)
	 * 
	 * @param previousBatch
	 * @return
	 */
	public static List<Solution> naturalSelection(Generation previous) {
		List<Solution> batch = previous.getBatch();
		List<Solution> newParents = new ArrayList<>();
		Solution previousBest = previous.getBestSolution();

		for (int i = 0; i < batch.size() / 2; i++) {
			newParents.add((batch.get(2 * i + 1).fitness() < batch.get(2 * i).fitness()) ? batch.get(2 * i + 1)
					: batch.get(2 * i));

			if (newParents.get(i) != previousBest
					&& ThreadLocalRandom.current().nextInt(0, 100) < MUTATION_PROBABILITY) {
				newParents.get(i).mutation();
			}
		}

		return newParents;
	}

	/**
	 * 
	 * @return
	 */
	public Solution getBestSolution() {
		Solution best = null;
		int bestFitness = Integer.MAX_VALUE;

		for (Solution s : batch) {
			int newFitness = s.fitness();
			if (newFitness < bestFitness) {
				best = s;
				bestFitness = newFitness;
			}
		}

		return best;
	}

	//////

	public Generation(List<Solution> batch) throws Exception {
		super();
		if (batch.size() % 2 != 0) {
			throw new Exception("Batch cannot be odd");
		}
		this.batch = batch;
	}

	///

	public List<Solution> getBatch() {
		return batch;
	}

	public void setBatch(List<Solution> batch) {
		this.batch = batch;
	}

}
