import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Race {
    private RegionExplorer<String, Integer> map;
    private String currentLocation;
    private String destination;
    private ArrayList<String> nonAdjacentCities;

    public Race(RegionExplorer<String, Integer> map, String currentLocation) {
        this.map = map;
        this.currentLocation = currentLocation;
        this.nonAdjacentCities = new ArrayList<>();
        findNonAdjacentCities();
        selectRandomDestination();
    }

    private void findNonAdjacentCities() {
        ArrayList<String> allCities = map.getAllVertexObjects();
        Set<String> adjacentCities = new HashSet<>(map.getNeighbours(currentLocation));
        for (String city : allCities) {
            if (!city.equals(currentLocation) && !adjacentCities.contains(city)) {
                nonAdjacentCities.add(city);
            }
        }
    }

    private void selectRandomDestination() {
        if (!nonAdjacentCities.isEmpty()) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(nonAdjacentCities.size());
            this.destination = nonAdjacentCities.get(randomIndex);
        } else {
            System.out.println("No non-adjacent cities available.");
        }
    }

    public void startRace() {
        if (destination == null) {
            System.out.println("Race cannot start, no valid destination.");
            return;
        }

        System.out.println("The battle has begun! Your rival Gary has challenged you to a race to " + destination + ".");
        System.out.println();
        System.out.println("Shortest Path:");
        ArrayList<String> path = findShortestPath(currentLocation, destination);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("Good luck on your race!");
        System.out.println("+-----------------------------------------------------+");
    }

    private ArrayList<String> findShortestPath(String start, String end) {
        ArrayList<String> visited = new ArrayList<>();
        ArrayList<String> path = new ArrayList<>();
        findShortestPathHelper(start, end, visited, path);
        return path;
    }

    private boolean findShortestPathHelper(String current, String end, ArrayList<String> visited, ArrayList<String> path) {
        visited.add(current);
        if (current.equals(end)) {
            path.addAll(visited);
            return true;
        }

        ArrayList<String> neighbors = map.getNeighbours(current);
        for (String neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                if (findShortestPathHelper(neighbor, end, visited, path)) {
                    return true;
                }
            }
        }
        visited.remove(visited.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        RegionExplorer<String, Integer> map = MapPokemon.getMapData();
        String currentLocation = "Saffron City";
        Race race = new Race(map, currentLocation);
        race.startRace();
    }
}
