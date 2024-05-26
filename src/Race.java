import java.util.*;

public class Race {
    private RegionExplorer<String, Integer> map;
    private String startCity;
    private String targetCity;
    private List<String> path;

    public Race(RegionExplorer<String, Integer> map, String targetCity) {
        this.map = map;
        this.startCity = "Saffron City";
        this.targetCity = targetCity;
        this.path = findShortestPath();
    }

    private List<String> findShortestPath() {
        PriorityQueue<VertexDistance> pq = new PriorityQueue<>(Comparator.comparingInt(vd -> vd.distance));
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        Set<String> visited = new HashSet<>();

        for (String city : map.getAllVertexObjects()) {
            distances.put(city, Integer.MAX_VALUE);
        }
        distances.put(startCity, 0);

        pq.add(new VertexDistance(startCity, 0));

        while (!pq.isEmpty()) {
            VertexDistance current = pq.poll();
            String currentCity = current.city;

            if (!visited.add(currentCity)) {
                continue;
            }

            for (String neighbor : map.getNeighbours(currentCity)) {
                int newDist = distances.get(currentCity) + map.getEdgeWeight(currentCity, neighbor);

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    previous.put(neighbor, currentCity);
                    pq.add(new VertexDistance(neighbor, newDist));
                }
            }
        }

        List<String> path = new ArrayList<>();
        for (String at = targetCity; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    public void printRaceDetails() {
        System.out.println("+----------------------------------------------+");
        System.out.println("The battle has begun! Your rival Gary has challenged you to a race to " + targetCity + ".");
        System.out.println();
        System.out.println("Shortest Path:");
        System.out.println(String.join(" -> ", path));
        System.out.println();
        System.out.println("Good luck on your race!");
        System.out.println("+----------------------------------------------+");
    }

    private static class VertexDistance {
        String city;
        int distance;

        VertexDistance(String city, int distance) {
            this.city = city;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        RegionExplorer<String, Integer> map = MapPokemon.getMapData();

        List<String> adjacentCities = map.getNeighbours("Saffron City");
        List<String> allCities = map.getAllVertexObjects();
        List<String> eligibleCities = new ArrayList<>();

        for (String city : allCities) {
            if (!city.equals("Saffron City") && !adjacentCities.contains(city)) {
                eligibleCities.add(city);
            }
        }

        System.out.println("You are currently in Saffron City:");
        System.out.println("Move to:");
        char option = 'a';
        for (String city : eligibleCities) {
            System.out.println(option + ". " + city);
            option++;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Your choice: ");
        char choice = scanner.next().charAt(0);
        int selectedIndex = choice - 'a';

        if (selectedIndex < 0 || selectedIndex >= eligibleCities.size()) {
            System.out.println("Invalid choice!");
            return;
        }

        String targetCity = eligibleCities.get(selectedIndex);

        Race race = new Race(map, targetCity);
        race.printRaceDetails();
    }
}
