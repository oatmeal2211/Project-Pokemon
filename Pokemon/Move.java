package Pokemon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Move {
    private String name;
    private String type;
    private Integer power;
    private Double accuracy;
    private int pp; // Power Points

    public Move(String name, String type, Integer power, Double accuracy, int pp) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public int getPp() {
        return pp;
    }

    public void useMove() {
        if (pp > 0) {
            pp--;
        } else {
            System.out.println(name + " has no PP left!");
        }
    }

    @Override
    public String toString() {
        return "Move{name='" + name + "', type='" + type + "', power=" + power + ", accuracy=" + accuracy + ", pp=" + pp + '}';
    }

    public static List<Move> loadMovesFromCSV(String filePath) {
        List<Move> moveList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean headerSkipped = false;
            while ((line = br.readLine()) != null) {
                //if (line.startsWith("ID")) continue; // Skip header    
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue; // Skip header
                }          
                String[] data = line.split(",");
                String name = data[1];
                System.out.println(name);
                String type = data[2];
                System.out.println(type);
                Integer power = data[5].equalsIgnoreCase("null") ? null : Integer.parseInt(data[5]);
                System.out.println(power);
                Double accuracy;
                if (data[6].equalsIgnoreCase("null")) {
                    accuracy = null;
                } else if (Double.parseDouble(data[6]) == -1.0) {
                    accuracy = Double.POSITIVE_INFINITY; // Infinite accuracy
                } else {
                    accuracy = Double.parseDouble(data[6]);
                }
                System.out.println(accuracy);
                int pp = Integer.parseInt(data[4]);
                System.out.println(pp);

                Move move = new Move(name, type, power, accuracy, pp);
                moveList.add(move);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return moveList;
    }
}
