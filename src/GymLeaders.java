import java.util.*;

public class GymLeaders {
    private Player pewterCityLeader;
    private Player ceruleanLeader;
    private Player vermilionLeader;
    private Player celadonCityLeader;
    private Player fuchsiaCityLeader;
    private Player saffronCityLeader;
    private Player cinnabarIslandPlayer;
    private Player viridianCityLeader;
    
    public GymLeaders() {
        pewterCityLeader = new Player("Pewter City Leader", "Pewter");
        ceruleanLeader = new Player("Cerulean Leader", "Cerulean");
        vermilionLeader = new Player("Vermilion Leader", "Vermilion");
        celadonCityLeader = new Player("Celadon City Leader", "Celadon");
        fuchsiaCityLeader = new Player("Fuchsia City Leader", "Fuchsia");
        saffronCityLeader = new Player("Saffron City Leader", "Saffron");
        cinnabarIslandPlayer = new Player("Cinnabar Island Player", "Cinnabar");
        viridianCityLeader = new Player("Viridian City Leader", "Viridian");

        // pewterCityLeader set up
        pewterCityLeader.addPokemon(new Pokemon("Brock","Rock","Ground",40,80,100,30,30,20,12));   
        //pewterCityLeader.addPokemon(new Pokemon("Onix","Rock","Ground",35,45,50,30,40,50,14));

        // ceruleanLeader set up
        ceruleanLeader.addPokemon(new Pokemon("Misty","Water","None",30,45,55,70,70,85,18));
        //ceruleanLeader.addPokemon(new Pokemon("Goldeen","Water","None",45,67,60,50,50,63,21));

        // vermilionLeader set up
        //vermilionLeader.addPokemon(new Pokemon("Voltorb","Electric","None",40,30,50,55,55,100,21));
        vermilionLeader.addPokemon(new Pokemon("Lt. Surge","Electric","None",35,55,30,50,50,90,18));

        // celadonCityLeader set up
        //celadonCityLeader.addPokemon(new Pokemon("Bellsprout","Grass","Poison",50,75,35,70,70,40,29));
        celadonCityLeader.addPokemon(new Pokemon("Erica","Grass","None",65,55,115,100,100,60,24));
        //celadonCityLeader.addPokemon(new Pokemon("Oddish","Grass","Poison",45,50,55,75,75,30,29));

        // fuchsiaCityLeader set up
        fuchsiaCityLeader.addPokemon(new Pokemon("Koga","Poison","None",40,65,95,60,60,35,37));
        //fuchsiaCityLeader.addPokemon(new Pokemon("Grimer","Poison","None",80,80,50,40,40,25,39));
        //fuchsiaCityLeader.addPokemon(new Pokemon("Koffing","Poison","None",40,65,95,60,60,35,37));
        //fuchsiaCityLeader.addPokemon(new Pokemon("Nidoran-M","Poison","Ground",46,57,40,40,40,50,25));

        // saffronCityLeader set up
        saffronCityLeader.addPokemon(new Pokemon("Sabrina","Psychic","None",25,20,15,105,105,90,38));
        //saffronCityLeader.addPokemon(new Pokemon("Mr.Mime","Psychic","Fairy",40,45,65,100,100,90,37));
        //saffronCityLeader.addPokemon(new Pokemon("Venonat","Bug","Poison",60,55,50,40,40,45,38));
        //saffronCityLeader.addPokemon(new Pokemon("Jynx","Ice","Psychic",65,50,35,95,95,95,43));

        // cinnabarIslandPlayer set up
        //cinnabarIslandPlayer.addPokemon(new Pokemon("Growlithe","Fire","None",55,70,45,50,50,60,42));
        cinnabarIslandPlayer.addPokemon(new Pokemon("Blaine","Fire","None",50,85,55,65,65,90,40));
        //cinnabarIslandPlayer.addPokemon(new Pokemon("Moltres","Fire","Flying",90,100,90,125,125,90,42));
        //cinnabarIslandPlayer.addPokemon(new Pokemon("Growlithe","Fire","None",55,70,45,50,50,60,47));

        // viridianCityLeader set up
        viridianCityLeader.addPokemon(new Pokemon("Rhyhorn","Ground","Rock",80,85,95,30,30,25,45));
        //viridianCityLeader.addPokemon(new Pokemon("Diglett","Ground","None",10,55,25,45,45,95,42));
        //viridianCityLeader.addPokemon(new Pokemon("Nidoran-F","Poison","None",55,47,52,40,40,41,44));
        //viridianCityLeader.addPokemon(new Pokemon("Nidoran-M","Poison","Ground",46,57,40,40,40,50,45));
        //viridianCityLeader.addPokemon(new Pokemon("Cubone","Ground","None",50,50,95,40,40,35,50));





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

    public Player getSaffronCityLeader() {
        return saffronCityLeader;
    }

    public Player getCinnabarIslandPlayer() {
        return cinnabarIslandPlayer;
    }

    public Player getViridianCityLeader() {
        return viridianCityLeader;
    }
    
    
    
}
