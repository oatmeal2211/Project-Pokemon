public class MapPokemon {
    public static RegionExplorer<String, Integer> getMapData() {
       RegionExplorer<String, Integer> map = new RegionExplorer<>();
       String[] cities = {"Pewter City", "Viridian City", "Pallet Town", "Cinnabar Island", "Fuschia City", "Celadon City", "Saffron City", "Cerulean City", "Lavender Town", "Vermillion City"};
       for (String i : cities)
          map.addVertex(i);
       
       
       map.addEdge("Pewter City", "Cerulean City", 12);
       map.addEdge("Pewter City", "Viridian City", 8);
       map.addEdge("Viridian City", "Pallet Town", 5);
       map.addEdge("Pallet Town", "Cinnabar Island", 7);
       map.addEdge("Cinnabar Island", "Fuschia City", 5);
       map.addEdge("Fuschia City", "Celadon City", 10);
       map.addEdge("Fuschia City", "Vermillion City", 7);
       map.addEdge("Fuschia City", "Lavender Town", 11);
       map.addEdge("Celadon City", "Saffron City", 4);
       map.addEdge("Saffron City", "Vermillion City", 3);
       map.addEdge("Saffron City", "Cerulean City", 6);
       map.addEdge("Saffron City", "Lavender Town", 3);
       map.addEdge("Vermillion City", "Lavender Town", 5);
       map.addEdge("Cerulean City", "Lavender Town", 9);

 return map;
    }
  }
 
 

