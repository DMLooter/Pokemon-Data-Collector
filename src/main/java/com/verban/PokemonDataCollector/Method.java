package com.verban.PokemonDataCollector;
/**
 * Enum representing the different ways to catch a pokemon. Includes all methods in the first 5 generations.
 * @author Michael
 *
 */
public enum Method {
	NORMAL, SURFING, DIVING, FLYING, SEA_SKIM, SWARM, HORDE, TRAP, HIDDEN_GROTTO, SPECIAL, STARTER, GIFT, TRADE, REVIVE, FISHING, OLD_ROD, GOOD_ROD, SUPER_ROD, UNKOWN, HEADBUTT, ROCK_SMASH, RADIO_HOENN, RADIO_SINNOH, EVENT;

	/**
	 * Helper method to allow parsing of locations on bulbapedia to Methods.
	 * 
	 * @param m
	 *            the "location" listed in the table on bulbapedia
	 * @return the Method representing that location
	 */
	public static Method parseMethod(String m) {
		if (m.contains("Trade")) {
			return TRADE;
		}
		if (m.contains("Event")) {
			return EVENT;
		}
		if (m.contains("Revive")) {
			return REVIVE;
		}
		if (m.matches(".*([0-9]F)+.*")) { // For floor numbers
			return NORMAL;
		}
		if (m.contains("Dual-slot")) {
			return SPECIAL;
		}
		switch (m) {
		case "Grass":
		case "Cave":
		case "Walking":
		case "Long grass":
		case "Tall grass":
		case "Dark grass":
		case "Entrance":
		case "Basement":
		case "Deep sand":
		case "Lowest floor":
		case "Volcarona's and nearby room":
		case "Volcarona's room":
		case "Inside":
		case "Rough":
		case "Terrain": // Rhyhorn riding on route 9 Kalos
			return NORMAL;
		case "Hidden Grotto":
			return HIDDEN_GROTTO;
		case "Fake item":
		case "Trap floor":
		case "Transmitter room":
			return TRAP;
		case "Swarm":
			return SWARM;
		case "Horde Encounter":
			return HORDE;
		case "Fishing":
			return FISHING;
		case "Old Rod":
			return OLD_ROD;
		case "Good Rod":
			return GOOD_ROD;
		case "Super Rod":
			return SUPER_ROD;
		case "Gift":
		case "Egg":
			return GIFT;
		case "Surfing":
			return SURFING;
		case "Dive":
		case "Seaweed":
			return DIVING;
		case "Headbutt":
			return HEADBUTT;//HGSS
		case "Rock Smash":
			return ROCK_SMASH; // FRLG
		case "Hoenn Sound":
			return RADIO_HOENN;//HGSS
		case "Sinnoh Sound":
			return RADIO_SINNOH; // HGSS
		case "In the sky":
		case "Flocks":
		case "Midair":
			return FLYING; // Pickachu/Eevee
		case "Sea Skim":
			return SEA_SKIM; // PE
		case "Only one":
		case "Shiny":
		case "Poké Radar":
		case "Backlot":
		case "Rippling water":
		case "Rustling grass":
		case "Dust cloud":
		case "Flying Pokémon's shadow":
		case "Puddle":
		case "Rustling Berry tree":
		case "Ceiling": // Ambush
		case "Sky": // Ambush
		case "Shaking trash cans":
		case "Yellow flowers":
		case "Purple flowers":
		case "Red flowers":
		case "Rustling bush":
		case "Dirt": //Kalos route 13
		case "Swamp": // 14
		case "Snow": //17 Mammoswine riding
			return SPECIAL; // TODO Makes some more of these perhaps.
		case "Starter Pokémon":
			return STARTER;
		default:
			System.err.println("Unknown Method: " + m);
			System.exit(1);
			return UNKOWN;

		}
	}
}
