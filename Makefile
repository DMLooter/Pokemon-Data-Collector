clean:
	mvn clean

build:
	mvn clean package

run:
	mvn clean package
	java -jar target/PokemonDataCollector-0.0.3-SNAPSHOT-jar-with-dependencies.jar
