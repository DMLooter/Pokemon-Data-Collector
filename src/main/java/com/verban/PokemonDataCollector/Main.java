package com.verban.PokemonDataCollector;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * This program uses Bulbapedia to compile a master list of every place in the
 * first 5 generations of pokemon games, and what pokemon can be found there.
 *
 * @author Michael
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		PokemonLoader.loadKantoRBY_FRLG_PEDex();
		PokemonLoader.loadJohtoGSCDex();
		PokemonLoader.loadHoennRSEDex();
		PokemonLoader.loadSinnohDPPDex();
		PokemonLoader.loadJohtoHGSSDex();
		PokemonLoader.loadHoennORASDex();
		PokemonLoader.loadGalarSWSHDex();

		//List of Combinations of Pokemon, Place, Game, and Method
		List<Availability> availabilities = new LinkedList<Availability>();

		//List of likes to pages that have Pokemon
		LinkedHashSet<String> pages = new LinkedHashSet<String>();
		// TODO start using lists of game locations

		//Kanto
		availabilities.addAll(parsePage("https://bulbapedia.bulbagarden.net/wiki/Category:Kanto_locations", "Kanto", pages));

		//Johto
		availabilities.addAll(parsePage("https://bulbapedia.bulbagarden.net/wiki/Category:Johto_locations", "Johto", pages));

		//Hoenn
		availabilities.addAll(parsePage("https://bulbapedia.bulbagarden.net/wiki/Category:Hoenn_locations", "Hoenn", pages));

		//Sinnoh
		availabilities.addAll(parsePage("https://bulbapedia.bulbagarden.net/wiki/Category:Sinnoh_locations", "Sinnoh", pages));

		//Unova
		availabilities.addAll(parsePage("https://bulbapedia.bulbagarden.net/wiki/Category:Unova_locations", "Unova", pages));

		//Kalos
		availabilities.addAll(parsePage("https://bulbapedia.bulbagarden.net/wiki/Category:Kalos_locations", "Kalos", pages));

		//Alola
		availabilities.addAll(parsePage("https://bulbapedia.bulbagarden.net/wiki/Category:Alola_locations", "Alola", pages));

		Exporter.exportAvailabilities(availabilities);
	}

	/**
	 * Begins the processs of processing one page from bulbapedia, and returns
	 * the list of availability. This method was created to reduce complexity in
	 * this main file.
	 *
	 * @param page
	 *            The full link to the page to scan
	 * @param regionName
	 *            The name of the region that the page refers to
	 * @param pages
	 *            The Set of already parsed pages, so as to avoid double
	 *            parsing.
	 *
	 * @return the list of new availabilities parsed from the given page.
	 * @throws IOException
	 *             if something failed.
	 */
	public static List<Availability> parsePage(String page, String regionName, Set<String> pages) throws IOException {
		//List of Combinations of Pokemon, Place, Game, and Method
		List<Availability> availabilities = new LinkedList<Availability>();

		Document doc = Jsoup.connect(page).get();
		System.out.println(doc.title());
		Elements places = doc.select("#mw-pages a");
		for (Element place : places) {
			if (pages.add(place.attr("abs:href"))) { // Make sure we don't scan a page twice
				List<Availability> a = LocationParser.parseLocation(place.attr("abs:href"), regionName);
				if (a != null) {
					availabilities.addAll(a);
				}

			}
		}
		return availabilities;
	}
}
