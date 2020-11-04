package com.verban.PokemonDataCollector;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * Class designed to parse each location page into its availabilities.
 * 
 * This class should never be instantiated, but instead uses static attributes to keep track of all locations.
 * @author Michael
 *
 */
public class LocationParser {

	private static String[] times = {"Morning", "Day", "Night"}; // List of times to find pokemon
	private static String[] seasons = {"Spring", "Summer", "Autumn", "Winter"}; // List of seasons (Unova)
	/**
	 * Master list of locations after all parses.
	 */
	private static List<Location> locations = new LinkedList<Location>();

	/**
	 * Takes in a link and a region to create a location and get out all its availabilities.
	 * @param link the page to parse
	 * @param region the region it is a part of
	 * @return The list of availabilities in this location
	 */
	public static List<Availability> parseLocation(String link, String region) {
		List<Availability> available = new LinkedList<Availability>();

		//Download the page
		Document doc;
		try {
			doc = Jsoup.connect(link).get();
		} catch (IOException e) {
			System.err.println("Failed to download.");
			e.printStackTrace();
			return null;
		}
		
		//Create the location from the region and page name.
		Location locationObject = new Location(doc.title().split(" - ")[0], region);
		locations.add(locationObject);

		//Ensure we have pokemon here, if not, return.
		Element pokemonHeading = doc.getElementById("Pok.C3.A9mon");
		if (pokemonHeading == null) {
			System.out.println("No pokemon in " + locationObject);
			return null;
		}
		System.out.println("Parsing: " + locationObject);

		//Loop through the list of pokemon finding availabilities.
		Element current = pokemonHeading.parent();
		boolean running = true;
		while (running) { // Loop through siblings until we hit the next main header.
			current = (Element) current.nextElementSibling();
			if (current == null) {
				running = false; // end of page???
			} else if (current.is(pokemonHeading.parent().tagName())) { // Make sure we are matching to the original header size.
				running = false;
			} else if (current.is("table")) {
				//int generation = romanToArabic(current.child(0).attr("id").split("_")[1]);

				Elements avs = current.select("tr[style='text-align:center;']");
				for (Element av : avs) {
					available.addAll(parseAvailability(av, locationObject));
				}
			}
		}
		return available;
	}

	/**
	 * Parses a single availability out of a row from the table.
	 * 
	 * @param tableRow
	 *            the row to parse.
	 * @return an availability representing this location
	 */
	public static List<Availability> parseAvailability(Element tableRow, Location L) {
		List<Availability> availability = new LinkedList<Availability>();

		String notes = "";

		Elements cells = tableRow.children();
		String pokemonName = cells.get(0).select("a span").get(0).text();
		Pokemon p;
		if (pokemonName.contains("Alola Form")) {
			p = Pokemon.getAlolanPokemon(pokemonName.replace("Alola Form", ""));
		} else if (pokemonName.contains("Ghost")) {
			return availability; // These things can't be caught actually, so it makes no sense to put them in here.
		} else {
			p = Pokemon.getPokemon(pokemonName);
		}

		int nextCell = 3; // Cell after the games indicators.
		List<String> gamesIn = new LinkedList<String>(); // List of games this availability is in

		//Get games:
		String game1 = cells.get(1).select("span").text().trim();
		if (game1.equals("R")) { // We have R B and Y or RSE
			nextCell = 4;
			if (cells.get(2).select("span").text().trim().equals("B")) { // We have R B Y
				if (!cells.get(1).attr("style").contains("#FFFFFF"))
					gamesIn.add("Red");
				if (!cells.get(2).attr("style").contains("#FFFFFF"))
					gamesIn.add("Blue");
				if (!cells.get(3).attr("style").contains("#FFFFFF"))
					gamesIn.add("Yellow");
			} else { //											 We Have R S E
				if (!cells.get(1).attr("style").contains("#FFFFFF"))
					gamesIn.add("Ruby");
				if (!cells.get(2).attr("style").contains("#FFFFFF"))
					gamesIn.add("Saphire");
				if (!cells.get(3).attr("style").contains("#FFFFFF"))
					gamesIn.add("Emerald");

			}

		} else if (game1.equals("G")) { // We have G S C
			nextCell = 4;

			if (!cells.get(1).attr("style").contains("#FFFFFF"))
				gamesIn.add("Gold");
			if (!cells.get(2).attr("style").contains("#FFFFFF"))
				gamesIn.add("Silver");
			if (!cells.get(3).attr("style").contains("#FFFFFF"))
				gamesIn.add("Crystal");

		} else if (game1.equals("FR")) { // We have FR LG or possibly FR LG E
			nextCell = 3;

			if (!cells.get(1).attr("style").contains("#FFFFFF"))
				gamesIn.add("FireRed");
			if (!cells.get(2).attr("style").contains("#FFFFFF"))
				gamesIn.add("LeafGreen");

			if (cells.get(3).is("th")) { // Check for Emerald
				nextCell = 4;
				if (!cells.get(3).attr("style").contains("#FFFFFF"))
					gamesIn.add("Emerald");
			}

		} else if (game1.equals("D")) { // We have D P Pt
			nextCell = 4;

			if (!cells.get(1).attr("style").contains("#FFFFFF"))
				gamesIn.add("Diamond");
			if (!cells.get(2).attr("style").contains("#FFFFFF"))
				gamesIn.add("Pearl");
			if (!cells.get(3).attr("style").contains("#FFFFFF"))
				gamesIn.add("Platinum");

		} else if (game1.equals("HG")) { // We have HG SS
			nextCell = 3;

			if (!cells.get(1).attr("style").contains("#FFFFFF"))
				gamesIn.add("HeartGold");
			if (!cells.get(2).attr("style").contains("#FFFFFF"))
				gamesIn.add("SoulSilver");

		} else if (game1.equals("B")) { // We have B W
			nextCell = 3;

			if (!cells.get(1).attr("style").contains("#FFFFFF"))
				gamesIn.add("Black");
			if (!cells.get(2).attr("style").contains("#FFFFFF"))
				gamesIn.add("White");

		} else if (game1.equals("B2")) { // We have B2 W2
			nextCell = 3;

			if (!cells.get(1).attr("style").contains("#FFFFFF"))
				gamesIn.add("Black 2");
			if (!cells.get(2).attr("style").contains("#FFFFFF"))
				gamesIn.add("White 2");

		} else if (game1.equals("OR")) { // We have OR AS
			nextCell = 3;

			if (!cells.get(1).attr("style").contains("#FFFFFF"))
				gamesIn.add("Omega Ruby");
			if (!cells.get(2).attr("style").contains("#FFFFFF"))
				gamesIn.add("Alpha Saphire");

		} else if (game1.equals("P")) { // We have Lets go P + E
			nextCell = 3;

			if (!cells.get(1).attr("style").contains("#FFFFFF"))
				gamesIn.add("Let's Go Pikachu!");
			if (!cells.get(2).attr("style").contains("#FFFFFF"))
				gamesIn.add("Let's Go Eevee!");

		} else if (game1.equals("X")) { // We have XY
			nextCell = 3;

			if (!cells.get(1).attr("style").contains("#FFFFFF"))
				gamesIn.add("X");
			if (!cells.get(2).attr("style").contains("#FFFFFF"))
				gamesIn.add("Y");

		} else {
			System.err.println("Unknown Game Letter: " + game1);
			System.exit(0); // Because this is undefined, we will just exit so we can fix this!!!!!!!!
			return availability;
		}

		//This shit is all fucked up, lots of special cases.
		// Method:
		String location;
		Elements span;
		if (!((span = cells.get(nextCell).select("a span")).isEmpty())) { // Most cases
			location = span.text().trim();
		} else {
			location = cells.get(nextCell).select("td").get(2).text().trim(); // Seen on "Gift"
		}

		// Fishing is special ( but not in BW or SM)
		if (location.equals("Fishing") && !game1.equals("B") && !game1.equals("B2") && !game1.equals("S") && !game1.equals("US")) {
			location = cells.get(nextCell).select("span small").text().trim();
		}
		if (location.equals("Fake item") || location.matches(".*([0-9]F)+.*") || location.equals("Volcarona's and nearby room") || location.equals("Lowest floor") || location.equals("Volcarona's room") ) {
			notes = location + ", ";
		}

		Method method = Method.parseMethod(location); // Parse out the method
		if (method == Method.TRADE) {
			String tradedPokemon = location.substring(location.indexOf(" ") + 1);
			notes = notes + tradedPokemon + ", ";
		} else if (method == Method.REVIVE) {
			String fosil = location.substring(location.indexOf(" ") + 1);
			notes = notes + fosil + ", ";
		} else if (method == Method.EVENT) {
			String event = location.substring(location.indexOf(" ") + 1);
			notes = notes + event + ", ";
		} else if (method == Method.SPECIAL) {
			String event = location.substring(location.indexOf(" ") + 1);
			notes = notes + event + ", ";
		}

		//Rate:
		int rate = 0;
		if (game1.equals("G") || game1.equals("HG") || game1.equals("D")) {  //Gens 2 and 4 can have different rates for times of day
			if (cells.size() > nextCell + 3) { //We have split Morning/Day/Night rates
				for (int cellOffset = 0; cellOffset <= 2; cellOffset++) { //Loop through those cells
					String text = cells.get(nextCell + 2 + cellOffset).text().trim().replace("%", "");
					if (text.equals("-")) {
						rate = 0;
					} else {
						try {
							rate = Integer.parseInt(text);
						} catch (NumberFormatException e) {
							//TODO put this in notes field
							System.err.println("No rate info for: " + pokemonName + "(" + game1 + ") " + times[cellOffset]);
						}
					}

					for (String game : gamesIn) { //Add the availability per game
						availability.add(new Availability(game, L, p, rate, method, notes));
					}
				}
			} else { //Just one rate
				String text = cells.get(nextCell + 2).text().trim().replace("%", "");
				if (text.equals("-")) {
					rate = 0;
				} else {
					try {
						rate = Integer.parseInt(text);
					} catch (NumberFormatException e) {
						//TODO put this in notes field
						System.err.println("No rate info for: " + pokemonName + "(" + game1 + ")");
					}
				}

				for (String game : gamesIn) { //Add the availability per game
					availability.add(new Availability(game, L, p, rate, method, notes));
				}
			}
		} else if (game1.equals("B") || game1.equals("B2")) { // We may have split Season rates in Gen 5
			if (cells.size() > nextCell + 3) { //We have split rates
				for (int cellOffset = 0; cellOffset <= 3; cellOffset++) { //Loop through those cells
					String text = cells.get(nextCell + 2 + cellOffset).text().trim().replace("%", "");
					if (text.equals("-")) {
						rate = 0;
					} else {
						try {
							rate = Integer.parseInt(text);
						} catch (NumberFormatException e) {
							//TODO put this in notes field
							System.err.println("No rate info for: " + pokemonName + "(" + game1 + ") " + seasons[cellOffset]);
						}
					}

					for (String game : gamesIn) { //Add the availability per game
						availability.add(new Availability(game, L, p, rate, method, notes));
					}
				}
			} else { //Just one rate
				String text = cells.get(nextCell + 2).text().trim().replace("%", "");
				if (text.equals("-")) {
					rate = 0;
				} else {
					try {
						rate = Integer.parseInt(text);
					} catch (NumberFormatException e) {
						//TODO put this in notes field
						System.err.println("No rate info for: " + pokemonName + "(" + game1 + ")");
					}
				}

				for (String game : gamesIn) { //Add the availability per game
					availability.add(new Availability(game, L, p, rate, method, notes));
				}
			}
		} else {
			String text = cells.get(nextCell + 2).text().trim().replace("%", "");
			if (text.equals("-")) {
				rate = 0;
			} else {
				try {
					rate = Integer.parseInt(text);
				} catch (NumberFormatException e) {
					//TODO put this in notes field
					System.err.println("No rate info for: " + pokemonName + "(" + game1 + ")");
				}
			}

			for (String game : gamesIn) { //Add the availability per game
				availability.add(new Availability(game, L, p, rate, method, notes));
			}
		}

		return availability;
	}

	//SHIT TO CONVERT ROMAN NUMERALS TO ARABIC
	//FUCK YOU NINTENDO FOR MAKING ME DO THIS
	//AND IT TURNS OUT I DIDNT HAVE TO BECAUSE THERE IS NO USE FOR THE GENERATION NUMBER WHEN I HAVE THE GAME NAME...
	//FUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUCKL
	static Hashtable<Character, Integer> ht = new Hashtable<Character, Integer>();
	static {
		ht.put('i', 1);
		ht.put('x', 10);
		ht.put('v', 5);
	}

	public static int romanToArabic(String num) {
		int intNum = 0;
		int prev = 0;
		for (int i = num.length() - 1; i >= 0; i--) {
			int temp = ht.get(num.charAt(i));
			if (temp < prev)
				intNum -= temp;
			else
				intNum += temp;
			prev = temp;
		}
		return intNum;
	}
}
