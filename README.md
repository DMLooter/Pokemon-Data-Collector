# Pokemon-Data-Collector
A program for scraping bulbapedia sources into an offline format usable by other programs

This program uses [Jsoup](https://jsoup.org/) to scrape [Bulbapedia](https://bulbapedia.bulbagarden.net/wiki/Main_Page) for data on the National and Regional Pokedexes, as well as where pokemon can be found. The data is stored in an offline format that can be utilized by other projects so they do not require internet access, and their data can be updated only as needed.

Currently the program simply does three things:
1. Download the National Pokedex with the name of every pokemon, their NationalDex #, and what generation they were introduced in.
2. Download three regional dexes: Gen III Hoenn Dex, Gen VI Hoenn Dex, Gen IV Johto Dex.
3. Downloads and parses availabilities (where pokemon can be found at what rates) for the regions Kanto, Johto, Hoenn, Sinnoh, Unova, and Kalos.

All data is stored in the users home directory, under the folder Pokemon/Data.

More functionality is on the way, including all Regional dexes, Alola and Galar availabilities, and command line options to determine what to download when running the program.
