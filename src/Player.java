import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

// Player class represents a player in the Pokémon game
public class Player {
    private String name;  // Name of the player
    private String location;  // Current location of the player
    private List<Pokemon> pokemonTeam;  // List of Pokémon in the player's team
    private List<badge> badges;  // List of badges earned by the player
    private RegionExplorer<String, Integer> map; // Map data for location tracking
    private List<Move> allMoves;

    // Constructor to initialize the Player object with a name, location, and map data
    public Player(String name, String location, RegionExplorer<String, Integer> map,List<Move> allMoves) {
        this.name = name;
        this.location = location;
        this.pokemonTeam = new ArrayList<>();
        this.badges = new ArrayList<>();
        this.map = map;
        this.allMoves = allMoves;
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
      public void setLocation(String location) {
        if (map.hasVertex(location)) {
            this.location = location;
            System.out.println("Moving to " + location + "...");
        } else {
            System.out.println("Invalid location.");
        }
    }

    // Get the list of Pokémon in the player's team
    public List<Pokemon> getPokemonTeam() {
        return pokemonTeam;
    }

    public int getPokemonTeamSize(){
        return pokemonTeam.size();
    }

    public Pokemon getPokemonByIndex(int index) {
        if (index >= 0 && index < pokemonTeam.size()) {
            return pokemonTeam.get(index);
        } else {
            System.out.println("Invalid index.");
            return null;
        }
    }

    // Get the list of badges earned by the player
    public List<badge> getBadges() {
        return badges;
    }

    public boolean hasBadge(String badgeName) {
        for (badge badge : badges) {
            if (badge.getName().equals(badgeName)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAllBadges() {
        // Assuming there are 8 unique badges to collect
        return badges.size() == 6;
    }

    
    // Methods for the Player class

    // Move the player to a new location
    public void move(String newLocation) {
        if (map.hasEdge(location, newLocation)) {
            this.location = newLocation;
            System.out.println("Moving to " + newLocation + "...");
        } else {
            System.out.println("Cannot move to " + newLocation + " from current location.");
        }
    }

    // Add a Pokémon to the player's team
    public void addPokemon(Pokemon Pokemon) { //需要接数据库
        if (pokemonTeam.size() < 6) {
            // Assign 4 random moves to the Pokémon
            Random rand = new Random();
            for (int i = 0; i < 4; i++) {
                Pokemon.learnMove(allMoves.get(rand.nextInt(allMoves.size())));
            }
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
    public void earnBadge(badge badge) {
    badges.add(badge);
    System.out.println("Badge earned: " + badge.getName()); // Assuming the Badge class has a getName() method
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
        for (badge badge : badges) {
            System.out.println(badge.getName());
        }
    }
    

    // Save the player's progress using file I/O
    public void saveProgress(String email,Integer saveSlot,String playername,  String location, String  badges,List<Pokemon> pokemonTeams) { //接数据库
        // Implement file I/O to save player data

        DatabaseManager dbManager = new DatabaseManager();
        String query = "SELECT count(*) FROM game_progress WHERE email = ?";
        int count = 0;
        try (Connection conn =  dbManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            while (true) {
                if ( rs.next() ) {
                    count = rs.getInt(1);
                }else{
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
        System.out.println("Progress saved.");
        
        if(count < 3){
            String id = IdUtils.simpleUUID();
            String sql = "INSERT INTO game_progress (id,email, save_slot, player_name, location, badges) VALUES (?,?,?,?,?,?)";
            try (Connection conn = dbManager.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, id);
                pstmt.setString(2, email);
                pstmt.setInt(3, count + 1);
                pstmt.setString(4, playername);
                pstmt.setString(5, location);
                pstmt.setString(6, badges);
                System.out.println(pstmt.executeUpdate() > 0);
            
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
    
            if(pokemonTeams != null && pokemonTeams.size() > 0){
                for(Pokemon pokemon : pokemonTeams){
                   String sql2 = "INSERT INTO pokemon_team (game_progress_id, name, level, experience_points) VALUES (?,?,?,?)";
                    try (Connection conn = dbManager.connect();
                         PreparedStatement pstmt = conn.prepareStatement(sql2)) {
                        pstmt.setString(1, id);
                        pstmt.setString(2, pokemon.getName());
                        pstmt.setInt(3, pokemon.getLevel());
                        pstmt.setInt(4, pokemon.getExperiencePoints());
                        System.out.println(pstmt.executeUpdate() > 0);
                    } catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }else{
            System.out.println("You have reached the maximum number of saves.");
        }
        
    }
}