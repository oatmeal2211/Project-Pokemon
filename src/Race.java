import java.util.*;

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
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v.distance));
        Map<String, Vertex> vertices = new HashMap<>();

        for (String city : map.getAllVertexObjects()) {
            vertices.put(city, new Vertex(city, Integer.MAX_VALUE));
        }

        vertices.get(start).distance = 0;
        pq.add(vertices.get(start));

        while (!pq.isEmpty()) {
            Vertex current = pq.poll();
            if (current.name.equals(end)) {
                break;
            }

            for (String neighbor : map.getNeighbours(current.name)) {
                int weight = map.getEdgeWeight(current.name, neighbor);
                Vertex neighborVertex = vertices.get(neighbor);

                int newDist = current.distance + weight;
                if (newDist < neighborVertex.distance) {
                    neighborVertex.distance = newDist;
                    neighborVertex.previous = current;
                    pq.add(neighborVertex);
                }
            }
        }

        ArrayList<String> path = new ArrayList<>();
        for (Vertex at = vertices.get(end); at != null; at = at.previous) {
            path.add(at.name);
        }
        Collections.reverse(path);
        return path;
    }

    private static class Vertex {
        String name;
        int distance;
        Vertex previous;

        Vertex(String name, int distance) {
            this.name = name;
            this.distance = distance;
            this.previous = null;
        }
    }

    public static void main(String[] args) {
        RegionExplorer<String, Integer> map = MapPokemon.getMapData();
        String currentLocation = "Saffron City";
        Race race = new Race(map, currentLocation);
        race.startRace();
    }
}
