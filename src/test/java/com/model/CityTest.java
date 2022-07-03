package com.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CityTest {

	@Test
	public void distanceTest() {
		City nice = new City("nice", 43.42, 7.16);
		City marseille = new City("marseille", 43.18, 5.22);
		
		assertTrue("", 100 < nice.distance(marseille) && nice.distance(marseille) < 200);
	}

}
