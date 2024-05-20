package Pokemon;

import java.util.*;
// Player class represents a player in the Pokémon game
public class Player {
    private String name;  // Name of the player
    private String location;  // Current location of the player
    private List<Pokemon> pokemonTeam;  // List of Pokémon in the player's team
    private List<String> badges;  // List of badges earned by the player

    // Constructor to initialize the Player object with a name and location
    public Player(String name, String location) {
        this.name = name;
        this.location = location;
        this.pokemonTeam = new ArrayList<>();
        this.badges = new ArrayList<>();
    }

    // Getters and Setters for name, location, pokemonTeam, and badges

    // Get the name of the player
    public String getName() {
        return name;
    }

    // Set the name of the player
    public void setName(String name) {
        this.name = name;
    }

    // Get the current location of the player
    public String getLocation() {
        return location;
    }

    // Set the current location of the player
    public void setLocation(String location) {//需要接map
        this.location = location;
    }

    // Get the list of Pokémon in the player's team
    public List<Pokemon> getPokemonTeam() {
        return pokemonTeam;
    }

    // Get the list of badges earned by the player
    public List<String> getBadges() {
        return badges;
    }

    // Methods for the Player class

    // Move the player to a new location
    public void move(String newLocation) { //需要接map
        this.location = newLocation;
        System.out.println("Moving to " + newLocation + "...");
    }

    // Add a Pokémon to the player's team
    public void addPokemon(Pokemon Pokemon) { //需要接数据库
        if (pokemonTeam.size() < 6) {
            pokemonTeam.add(Pokemon);
            System.out.println(Pokemon.getName() + " added to your team!");
        } else {
            System.out.println("You can only have 6 Pokémon in your team.");
        }
    }

    // Remove a Pokémon from the player's team
    public void removePokemon(Pokemon Pokemon) { //需要接数据库
        if (pokemonTeam.contains(Pokemon)) {
            pokemonTeam.remove(Pokemon);
            System.out.println(Pokemon.getName() + " removed from your team.");
        } else {
            System.out.println("This Pokémon is not in your team.");
        }
    }

    // Add a badge to the player's collection of badges
    public void earnBadge(String badge) {
        badges.add(badge);
        System.out.println("Badge earned: " + badge);
    }

    // Display the Pokémon in the player's team
    public void showPokemon() {
        System.out.println("Your Pokémon team:");
        for (Pokemon Pokemon : pokemonTeam) {
            System.out.println(Pokemon);
        }
    }

    // Display the badges earned by the player
    public void showBadges() {
        System.out.println("Your badges:");
        for (String badge : badges) {
            System.out.println(badge);
        }
    }

    // Save the player's progress using file I/O
    public void saveProgress() {
        // Implement file I/O to save player data
        System.out.println("Progress saved.");
    }

    // Load the player's progress using file I/O
    public void loadProgress() {
        // Implement file I/O to load player data
        System.out.println("Progress loaded.");
    }
}