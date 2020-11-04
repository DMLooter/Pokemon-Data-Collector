package com.verban.PokemonDataCollector;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Class that will export the data downloaded to a text file.
 * 
 * @author Michael
 *
 */
public class Exporter {

	public static void exportPokedex(List<Pokemon> pokedex) throws IOException {
		File f = new File(System.getProperty("user.home") + "/Pokemon/Data/National.dex");
		f.getParentFile().mkdirs();
		f.createNewFile();
		PrintWriter out = new PrintWriter(new FileWriter(f));

		//Format: Name -- number -- gen

		for (Pokemon p : pokedex) {
			if (p.isAlolan())
				continue;
			out.println(p.getName() + " -- " + p.getNationalNo() + " -- " + p.getGen());
		}

		out.flush();
		out.close();
	}

	/**
	 * Exports the given list of pokemon as a regionaldex with the specified
	 * name
	 * 
	 * @param dex
	 * @throws IOException
	 */
	public static void exportRegionalDex(List<Pokemon> dex, String name) throws IOException {
		File f = new File(System.getProperty("user.home") + "/Pokemon/Data/RegionalDexes/" + name + ".dex");
		f.getParentFile().mkdirs();
		f.createNewFile();
		PrintWriter out = new PrintWriter(new FileWriter(f));

		//Format: Name
		for (Pokemon p : dex) {
			out.print(p.getName() + ",");
		}

		out.flush();
		out.close();
	}

	/**
	 * Exports the given list of availabilities to a file in the users home
	 * directory.
	 * 
	 * @param toExport
	 *            the list to export
	 * @throws IOException
	 *             if anything failed
	 */
	public static void exportAvailabilities(List<Availability> toExport) throws IOException {
		File f = new File(System.getProperty("user.home") + "/Pokemon/Data/Availabilities.txt");
		f.getParentFile().mkdirs();
		f.createNewFile();
		PrintWriter out = new PrintWriter(new FileWriter(f));

		//Format: Game -- Location -- Pokemon -- Method -- Rate -- Notes

		for (Availability a : toExport) {
			System.out.println(a.getGame() + " -- " + a.getLocation() + " -- " + (a.getPokemon().isAlolan() ? "Alolan " : "") + a.getPokemon().getName() + " -- " + a.getMethod() + " -- " + a.getRate() + " -- " + a.getNotes());
			out.println(a.getGame() + " -- " + a.getLocation() + " -- " + (a.getPokemon().isAlolan() ? "Alolan " : "") + a.getPokemon().getName() + " -- " + a.getMethod() + " -- " + a.getRate() + " -- " + a.getNotes());
		}

		out.flush();
		out.close();
	}
}
