package com.verban.PokemonDataCollector;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a Pokemon, their National index number, generation, and where they can be
 * found.
 * 
 * @author Michael
 *
 */
public class Pokemon {

	/**
	 * List of pokemon that is loaded statically, before any of the parsing is done.
	 */
	private static List<Pokemon> pokedex;
	static {
		try {
			pokedex = PokemonLoader.loadPokemon();
		} catch (IOException e) {
			System.err.println("Could not load pokemon from online database");
			e.printStackTrace();
		}
	}

	private String name;
	private int nationalNo;
	private int gen;
	private List<Availability> availabilities;

	// true if this pokemon is alolan form of a gen 1.
	private boolean alolan = false;

	public Pokemon(String name, int number, int generation, boolean alolan) {
		this.name = name.trim();
		this.nationalNo = number;
		this.gen = generation;
		this.alolan = alolan;
		availabilities = new LinkedList<Availability>();
	}

	/**
	 * Private helper method to allow construction of barebones pokemon with
	 * only
	 * what is needed to match them to an existing pokemon.
	 * 
	 * @param name
	 * @param alolan
	 */
	private Pokemon(String name, boolean alolan) {
		this.name = name.trim();
		this.alolan = alolan;
	}

	@Override
	public String toString() {
		return "#" + nationalNo + ": " + (alolan ? "Alolan " : "") + name + " (Gen " + gen + ")";
	}

	/**
	 * Returns true if the provided pokemon has the same name as the current
	 * pokemon and the two are not different forms (Alolan vs normal)
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Pokemon) {
			return ((Pokemon) o).alolan == this.alolan && ((Pokemon) o).name.equals(this.name);
		}
		return false;
	}

	/**
	 * @return the entire pokedex
	 */
	public static List<Pokemon> getPokedex() {
		return pokedex;
	}

	/**
	 * @return the name of this Pokémon
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the nationalDex number of this Pokémon
	 */
	public int getNationalNo() {
		return nationalNo;
	}

	/**
	 * @return the generation number that this Pokémon was added during
	 */
	public int getGen() {
		return gen;
	}

	/**
	 * @return the List of ways this Pokémon can be obtained
	 */
	public List<Availability> getAvailabilities() {
		return availabilities;
	}

	/**
	 * @return whether or not this Pokémon is an Alolan Form
	 */
	public boolean isAlolan() {
		return alolan;
	}

	/**
	 * Attempts to find the pokemon with the given name and return it. If the
	 * pokemon has an alolan form this method will return the normal one.
	 * 
	 * @see getAlolanPokemon
	 * @param name
	 *            the name of the pokemon to find
	 * @return an instance of the requested pokemon
	 */
	public static Pokemon getPokemon(String name) {
		/*@offif (name.contains("Deoxys")) // Catch this shit
			name = "Deoxys";
		if (name.contains("Partner")) // Because no one cares that eevee and pikachu are special in any way. Fuck them
			name = name.replace("Partner", "");
		if (name.contains("Pikachu")) // BECAUSE THERE APARENTLY NEEDS TO BE SPECIAL COSTUMES FOR THIS CUNT!!!! 
			name = "Pikachu";
		if (name.contains("Pichu")) // Hey they did it with Pichu too........ 
			name = "Pichu";
		if(name.contains("Burmy")) //This is fair, i may to do this with Wormadam too. Maybe Rotom?
			name = "Burmy";*/
		//I just realized this can all be replaced with a simple case to ignore second lines......@on
		
		if (name.contains("Pichu")) // Except Pichu..., Fucker
			name = "Pichu";
		Pokemon test = new Pokemon(name.trim(), false);
		int i = pokedex.indexOf(test);
		if (i == -1)
			return null;
		return pokedex.get(i);
	}

	/**
	 * Attempts to find the alolan pokemon with the given form and return it.
	 * 
	 * @see getAlolanPokemon
	 * @param name
	 *            the name of the pokemon to find
	 * @return an instance of the requested pokemon
	 */
	public static Pokemon getAlolanPokemon(String name) {
		Pokemon test = new Pokemon(name.trim(), true);
		int i = pokedex.indexOf(test);
		if (i == -1)
			return null;
		return pokedex.get(i);
	}

}
