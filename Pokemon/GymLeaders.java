package Pokemon;

import java.util.*;

public class GymLeaders {
    private Player pewterCityLeader;
    private Player ceruleanLeader;
    private Player vermilionLeader;
    private Player celadonCityLeader;
    private Player fuchsiaCityLeader;
    private Player saffronCityLeader;
    private Player cinnabarIslanndPlayer;
    private Player viridianCityLeader;
    
    public GymLeaders() {
        pewterCityLeader = new Player("Pewter City Leader", "Pewter");
        ceruleanLeader = new Player("Cerulean Leader", "Cerulean");
        vermilionLeader = new Player("Vermilion Leader", "Vermilion");
        celadonCityLeader = new Player("Celadon City Leader", "Celadon");
        fuchsiaCityLeader = new Player("Fuchsia City Leader", "Fuchsia");
        saffronCityLeader = new Player("Saffron City Leader", "Saffron");
        cinnabarIslanndPlayer = new Player("Cinnabar Island Player", "Cinnabar");
        viridianCityLeader = new Player("Viridian City Leader", "Viridian");

        // pewterCityLeader set up
        pewterCityLeader.addPokemon(new Pokemon("Geodude","Rock","Ground",));

    }
    
    public Player getPewterCityLeader() {
        return pewterCityLeader;
    }
    
    public Player getCeruleanLeader() {
        return ceruleanLeader;
    }
    
    public Player getVermilionLeader() {
        return vermilionLeader;
    }
    
    public Player getCeladonCityLeader() {
        return celadonCityLeader;
    }
    
    public Player getFuchsiaCityLeader() {
        return fuchsiaCityLeader;
    }
    

    
}
