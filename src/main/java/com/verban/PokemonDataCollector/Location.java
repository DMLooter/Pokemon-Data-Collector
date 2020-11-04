package com.verban.PokemonDataCollector;
/**
 * Represents a location in a region (Route, City, Ship, Etc.).
 * @author Michael
 *
 */
public class Location {
	private String name;
	private String region;

	public Location(String name, String region) {
		this.name = name;
		this.region = region;
	}

	public String toString() {
		return name + " (" + region + ")";
	}
}
