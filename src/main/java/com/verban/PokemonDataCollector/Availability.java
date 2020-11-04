package com.verban.PokemonDataCollector;
/**
 * Represents one probability of one species in one location in one game via one
 * method
 * (swimming, trade, walking, fishing)
 * 
 * @author Michael
 *
 */
public class Availability {
	private Location location;
	private String game;
	private Pokemon pokemon;
	private int rate;
	private Method method; // Method used to capture this pokemon.
	private String notes;

	/**
	 * Create a new AVailability
	 * @param game The game this occurs in
	 * @param loc the location it is found
	 * @param poke the pokemon that can be caught
	 * @param percent the percentage chance of the encounter (if applicable)
	 * @param m the method used to encounter this pokemon
	 * @param notes any addional notes about the availability.
	 */
	public Availability(String game, Location loc, Pokemon poke, int percent, Method m, String notes) {
		this.game = game;
		this.location = loc;
		this.pokemon = poke;
		this.rate = percent;
		this.method = m;
		this.notes = notes;
	}

	@Override
	public String toString() {
		return pokemon.getName() + " in " + location + " by " + method.toString() + " in " + game + "(" + rate + "%, " + notes + ")";
	}

	/**
	 * @return the rate at which this event occurs
	 */
	public int getRate() {
		return rate;
	}

	/**
	 * @param rate
	 */
	public void setRate(int rate) {
		this.rate = rate;
	}

	/**
	 * @return the notes for this availability
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the location in which this pokemon can be caught
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @return the game in which this pokemon can be caught here
	 */
	public String getGame() {
		return game;
	}

	/**
	 * @return the pokemon which can be caught
	 */
	public Pokemon getPokemon() {
		return pokemon;
	}

	/**
	 * @return the method by which this pokemon can be caught
	 */
	public Method getMethod() {
		return method;
	}

}
