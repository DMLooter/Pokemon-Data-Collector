package com.verban.PokemonDataCollector;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Class to load the list of pokemon from Bulbapedia.
 * 
 * @author Michael
 *
 */
public class PokemonLoader { // TODO get this into its own program

	public static List<Pokemon> loadPokemon() throws IOException {
		LinkedHashSet<String> links = new LinkedHashSet<String>();
		List<Pokemon> pokedex = new ArrayList<Pokemon>(500);
		int count = 0;

		Document doc = Jsoup.connect("https://bulbapedia.bulbagarden.net/wiki/List_of_Pok%C3%A9mon_by_National_Pok%C3%A9dex_number").get();
		System.out.println(doc.title());

		//Generation 1
		Element G1 = doc.select("table").get(1);
		Elements pokemon = G1.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(new Pokemon(link.attr("title").split(" \\(")[0], count, 1, false));
				} else {// Catch the alolan forms that appear in gen 6
					pokedex.add(new Pokemon(link.attr("title").split(" \\(")[0], count, 1, true));
				}
			}
		}

		//Generation 2
		Element G2 = doc.select("table").get(2);
		pokemon = G2.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(new Pokemon(link.attr("title").split(" \\(")[0], count, 2, false));
				}
			}
		}

		//Generation 3
		Element G3 = doc.select("table").get(3);
		pokemon = G3.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(new Pokemon(link.attr("title").split(" \\(")[0], count, 3, false));
				}
			}
		}

		//Generation 4
		Element G4 = doc.select("table").get(4);
		pokemon = G4.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(new Pokemon(link.attr("title").split(" \\(")[0], count, 4, false));
				}
			}
		}

		//Generation 5
		Element G5 = doc.select("table").get(5);
		pokemon = G5.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(new Pokemon(link.attr("title").split(" \\(")[0], count, 5, false));
				}
			}
		}

		//Generation 6
		Element G6 = doc.select("table").get(6);
		pokemon = G6.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(new Pokemon(link.attr("title").split(" \\(")[0], count, 6, false));
				}
			}
		}

		//Generation 7
		Element G7 = doc.select("table").get(7);
		pokemon = G7.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(new Pokemon(link.attr("title").split(" \\(")[0], count, 7, false));
				}
			}
		}

		//Generation 8
		Element G8 = doc.select("table").get(8);
		pokemon = G8.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(new Pokemon(link.attr("title").split(" \\(")[0], count, 8, false));
				}
			}
		}
		System.out.println("Loaded: " + count + " Pokémon");

		Exporter.exportPokedex(pokedex);
		return pokedex;
	}

	public static List<Pokemon> loadHoennRSEDex() throws IOException {
		LinkedHashSet<String> links = new LinkedHashSet<String>();
		List<Pokemon> pokedex = new ArrayList<Pokemon>(500);
		int count = 0;

		Document doc = Jsoup.connect("https://bulbapedia.bulbagarden.net/wiki/List_of_Pok%C3%A9mon_by_Hoenn_Pok%C3%A9dex_number_(Generation_III)").get();
		System.out.println(doc.title());

		//Section 1
		Element G1 = doc.select("table").get(0);
		Elements pokemon = G1.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(Pokemon.getPokemon(link.attr("title").split(" \\(")[0]));
					System.out.println(link.attr("title").split(" \\(")[0]);
				}
			}
		}

		//Section 2
		Element G2 = doc.select("table").get(1);
		pokemon = G2.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(Pokemon.getPokemon(link.attr("title").split(" \\(")[0]));
					System.out.println(link.attr("title").split(" \\(")[0]);
				}
			}
		}

		//Section 3
		Element G3 = doc.select("table").get(2);
		pokemon = G3.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(Pokemon.getPokemon(link.attr("title").split(" \\(")[0]));
					System.out.println(link.attr("title").split(" \\(")[0]);
				}
			}
		}

		//Section 4
		Element G4 = doc.select("table").get(3);
		pokemon = G4.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(Pokemon.getPokemon(link.attr("title").split(" \\(")[0]));
					System.out.println(link.attr("title").split(" \\(")[0]);
				}
			}
		}

		System.out.println("Loaded: " + count + " Pokémon");
		Exporter.exportRegionalDex(pokedex, "HoennRSE");
		return pokedex;
	}

	public static List<Pokemon> loadHoennORASDex() throws IOException {
		LinkedHashSet<String> links = new LinkedHashSet<String>();
		List<Pokemon> pokedex = new ArrayList<Pokemon>(500);
		int count = 0;

		Document doc = Jsoup.connect("https://bulbapedia.bulbagarden.net/wiki/List_of_Pok%C3%A9mon_by_Hoenn_Pok%C3%A9dex_number_(Generation_VI)").get();
		System.out.println(doc.title());

		//Section 1
		Element G1 = doc.select("table").get(1);
		Elements pokemon = G1.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(Pokemon.getPokemon(link.attr("title").split(" \\(")[0]));
					System.out.println(link.attr("title").split(" \\(")[0]);
				}
			}
		}

		//Section 2
		Element G2 = doc.select("table").get(2);
		pokemon = G2.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(Pokemon.getPokemon(link.attr("title").split(" \\(")[0]));
					System.out.println(link.attr("title").split(" \\(")[0]);
				}
			}
		}

		//Section 3
		Element G3 = doc.select("table").get(3);
		pokemon = G3.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(Pokemon.getPokemon(link.attr("title").split(" \\(")[0]));
					System.out.println(link.attr("title").split(" \\(")[0]);
				}
			}
		}

		//Section 4
		Element G4 = doc.select("table").get(4);
		pokemon = G4.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(Pokemon.getPokemon(link.attr("title").split(" \\(")[0]));
					System.out.println(link.attr("title").split(" \\(")[0]);
				}
			}
		}

		System.out.println("Loaded: " + count + " Pokémon");
		Exporter.exportRegionalDex(pokedex, "HoennORAS");
		return pokedex;
	}

	public static List<Pokemon> loadJohtoHGSSDex() throws IOException {
		LinkedHashSet<String> links = new LinkedHashSet<String>();
		List<Pokemon> pokedex = new ArrayList<Pokemon>(500);
		int count = 0;

		Document doc = Jsoup.connect("https://bulbapedia.bulbagarden.net/wiki/List_of_Pok%C3%A9mon_by_Johto_Pok%C3%A9dex_number").get();
		System.out.println(doc.title());

		//Section 1
		Element G1 = doc.select("table").get(1);
		Elements pokemon = G1.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(Pokemon.getPokemon(link.attr("title").split(" \\(")[0]));
					System.out.println(link.attr("title").split(" \\(")[0]);
				}
			}
		}

		//Section 2
		Element G2 = doc.select("table").get(2);
		pokemon = G2.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(Pokemon.getPokemon(link.attr("title").split(" \\(")[0]));
					System.out.println(link.attr("title").split(" \\(")[0]);
				}
			}
		}

		//Section 3
		Element G3 = doc.select("table").get(3);
		pokemon = G3.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(Pokemon.getPokemon(link.attr("title").split(" \\(")[0]));
					System.out.println(link.attr("title").split(" \\(")[0]);
				}
			}
		}

		//Section 4
		Element G4 = doc.select("table").get(4);
		pokemon = G4.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(Pokemon.getPokemon(link.attr("title").split(" \\(")[0]));
					System.out.println(link.attr("title").split(" \\(")[0]);
				}
			}
		}

		//Section 5
		Element G5 = doc.select("table").get(5);
		pokemon = G5.select("a");

		for (Element link : pokemon) {
			if (link.attr("title").contains("Pokémon") && !link.attr("title").contains("List")) {
				if (links.add(link.attr("title"))) {
					count++;
					pokedex.add(Pokemon.getPokemon(link.attr("title").split(" \\(")[0]));
					System.out.println(link.attr("title").split(" \\(")[0]);
				}
			}
		}

		System.out.println("Loaded: " + count + " Pokémon");
		Exporter.exportRegionalDex(pokedex, "JohtoHGSS");
		return pokedex;
	}
}

//@off
//Because some pokemon have multiple forms:
/*
 * Gen1:
 * 		Alolan:
 * 		Rattat, Raticate, Raichu, Sandshrew, Sandslash, Vulpix Ninetails, Diglet, Dugtrio, Meowth, Persian, Geodude, Graveler, Golem, Grimer, Muk, Execcutor, Maroak
 * 
 * Gen2:
 * 		**Unown (but not seperated on the online database)
 * 
 * Gen3:
 * 		**Castform (weather forms)
 * 		Deoxys (Maybe putin manually)
 * 
 * Gen4:
 * 		**Burmy, Wormadam (Trash, Plant, Grass) (unsure if wormadam can be encountered in wild.)
 * 		**Shellos & Gastrodon (West vs East Sea) (Again unsure if gastrodon can be wild)
 * 		**Rotom (Appliances)
 * 		**Giratina & Shaymin (Land vs Sky, Origin vs Alternate)
 * 
 * Gen5:
 * 		**Unfezant ( Male vs Female)
 * 		**Basculin (Red vs Blue
 * 		**Frilish & Jellicent (Male vs Female)
 * 		**Meloetta (Only changes in battle
 * 
 * Gen 7:
 * 		**Oricorio
 * 
 * Galarian:
 * 		Meowth, Ponyta, Rapidash, Slowpoke, Slowbro, Slowking, Farfetch'd, Weezing, Mr. Mime, Articuno, Zapdos, Moltres, Corsola, Zigzagoon, Linoone,Darumaka, Darmanitan, Yamask, Stunfisk
 */
