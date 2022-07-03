package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javax.swing.SwingUtilities;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import com.model.City;
import com.model.Generation;
import com.model.Solution;
import com.view.Panel;

public class Application {

	// TODO: Find optimal parameters (size & mutation prob)
	// TODO: Show distance graph
	private static final int BATCH_SIZE = 1000;
	private static final int TARGET = 3200;
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Application app = new Application();
					app.getBestSolution();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private Generation generation;
	
	@SuppressWarnings("unchecked")
	public Application() throws Exception {
		ArrayList<Solution> firstSolutionSet = new ArrayList<>();
		
		Yaml yaml = new Yaml(new Constructor(Collection.class));
        Collection<City> collection = null;
        
        try (InputStream in = new FileInputStream(new File("src/main/resources/cities.yml"))){
            collection = (Collection<City>) yaml.load(in);
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
        ArrayList<City> citiesList = new ArrayList<>(collection);
        int len = citiesList.size();
        
		for (int i = 0; i < BATCH_SIZE; i++) {
			Collections.shuffle(citiesList);
			firstSolutionSet.add(new Solution(citiesList.toArray(new City[len])));
		}
		
		generation = new Generation(firstSolutionSet);
	}

	private void getBestSolution() throws Exception {
		int distance = generation.getBestSolution().fitness();
		int previousDistance = distance;
		
		while (distance > TARGET) {
			generation = Generation.breedNextGeneration(generation);
			
			distance = generation.getBestSolution().fitness();
			
			if (distance < previousDistance) {
				System.out.println(String.format("Best distance yet: %s km", distance));
				previousDistance = distance;
			}
		}
		
		new Panel(this);
		
		System.out.println(Arrays.toString(generation.getBestSolution().getPath()));
	}

	public Generation getGeneration() {
		return generation;
	}

	public void setGeneration(Generation generation) {
		this.generation = generation;
	}

}
