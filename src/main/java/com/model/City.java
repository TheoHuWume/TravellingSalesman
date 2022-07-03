package com.model;

public class City {

	private String name;
	private double latitude;
	private double longitude;

	///

	/**
	 * 
	 * @param destination
	 * @return
	 */
	public int distance(City destination) {

		double latDistance = Math.toRadians(destination.latitude - latitude);
		double lonDistance = Math.toRadians(destination.longitude - longitude);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(destination.latitude)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		
		return (int) Math.round(6371 * c);
	}

	//////
	
	public City(String name, double latitude, double longitude) {
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public City() {
		super();
	}

	@Override
	public String toString() {
		return name;
	}

	///

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
