package Pokemon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Move {
    private String name;
    private String type;
    private String category;
    private Integer power;
    private Double accuracy;
    private int pp; //Power points
    private String effect;


    public Move(String name, String type, String category, Integer power, Double accuracy, int pp, String effect) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.power = power;
        this.accuracy = accuracy;
        this.pp = pp;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCategory(){
        return category;
    }

    public Integer getPower() {
        return power;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public int getPp() {
        return pp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public void setPp(int pp) {
        this.pp = pp;
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
        return String.format("Move{name='%s', type='%s', category='%s', power=%s, accuracy=%s, pp=%d}", name, type, category, power, accuracy, pp);
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
                String type = data[2];
                String category = data[3];
                Integer power = data[5].equalsIgnoreCase("null") ? null : Integer.parseInt(data[5]);
                Double accuracy;
                if (data[6].equalsIgnoreCase("null")) {
                    accuracy = null;
                } else if (Double.parseDouble(data[6]) == -1.0) {
                    accuracy = Double.POSITIVE_INFINITY; // Infinite accuracy
                } else {
                    accuracy = Double.parseDouble(data[6]);
                }
                int pp = Integer.parseInt(data[4]);

                Move move = new Move(name, type, category, power, accuracy, pp, null);
                moveList.add(move);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return moveList;
    }
}
