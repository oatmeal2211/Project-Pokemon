import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

public class Pokemon {
    private String name;
    private int level;
    private int exp;
    private String type1;
    private String type2;
    private int baseHp;
    private int baseAttack;
    private int baseDefense;
    private int baseSpecialAttack;
    private int baseSpecialDefense;
    private int baseSpeed;
    private int hp;
    private int attack;
    private int defense;
    private int sp_attack;
    private int sp_defense;
    private int speed;
    private List<Move> moves;

    public Pokemon(String name, String type1, String type2, int baseHp, int baseAttack, int baseDefense, int baseSpecialAttack, int baseSpecialDefense, int baseSpeed, int level) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.baseHp = baseHp;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.baseSpecialAttack = baseSpecialAttack;
        this.baseSpecialDefense = baseSpecialDefense;
        this.baseSpeed = baseSpeed;
        this.level = level;
        this.exp = 0; // Starting XP is 0
        this.moves = new ArrayList<>();

        this.hp = scaleStat(baseHp, level);
        this.attack = scaleStat(baseAttack, level);
        this.defense = scaleStat(baseDefense, level);
        this.sp_attack = scaleStat(baseSpecialAttack, level);
        this.sp_defense = scaleStat(baseSpecialDefense, level);
        this.speed = scaleStat(baseSpeed, level);
    }

    public static int scaleStat(int baseStat, int level) {
        return baseStat + (int)((baseStat * level / 100.0) * 2);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType1(){
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2(){
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getBaseHp(){
        return baseHp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialAttack() {
        return sp_attack;
    }

    public void setSpecialAttack(int sp_attack) {
        this.sp_attack = sp_attack;
    }

    public int getSpecialDefense() {
        return sp_defense;
    }

    public void setSpecialDefense(int sp_defense) {
        this.sp_defense = sp_defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void restoreFullHealth() {
        this.hp = this.scaleStat(this.baseHp, this.level);
    }
    

    public int getExperiencePoints() {
        return exp;
    }

    public void setExperiencePoints(int exp) {
        this.exp = exp;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void learnMove(Move move) {
        if (moves.size() < 4) { // A Pokémon can have up to 4 moves
            moves.add(move);
        } else {
            System.out.println(name + " already knows 4 moves. Forget a move to learn a new one.");
        }
    }

    public Move getMove(int index) {
        if (index >= 0 && index < moves.size()) {
            return moves.get(index);
        } else {
            return null; // Or throw an exception if desired
        }
    }

    public void forgetMove(Move move) {
        moves.remove(move);
    }

    public void displayStats() {
        System.out.println("Name: " + name);
        System.out.println("Type(s): " + type1 + ", " + type2);
        System.out.println("HP: " + hp);
        System.out.println("Attack: " + attack);
        System.out.println("Defense: " + defense);
        System.out.println("Special Attack: " + sp_attack);
        System.out.println("Special Defense: " + sp_defense);
        System.out.println("Speed: " + speed);
        System.out.println("Level: " + level);
        System.out.println("XP: " + exp);
        System.out.println("Moves: ");
        for (Move move : moves) {
            System.out.println(move);
        }
    }
    
    // Method to take damage
    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) this.hp = 0;
    }

    // Method to gain experience
    public void gainExperience(int exp) {
        this.exp += exp;
        // For simplicity, let's say each level requires 100 XP
        if(this.level <= 10){
            while (this.exp >= 100) {
                this.exp -= 100;
                levelUp();
            }
        }
        else if(this.level <= 30){
            while (this.exp >= 200) {
                this.exp -= 200;
                levelUp();
            }
        }
        else{
            while (this.exp >= 300) {
                this.exp -= 300;
                levelUp();
            }
        }
    }

    // Method to level up
    private void levelUp() {
    this.level++;
    this.hp += 10; // Simple stat increase for demonstration
    this.attack += 2;
    this.defense += 2;
    this.sp_attack += 2;
    this.sp_defense += 2;
    this.speed += 2;
    
    // Construct the message for JOptionPane
    String message = this.name + " leveled up to " + this.level + "!\n";
    message += "HP +10\n";
    message += "Attack +2\n";
    message += "Defense +2\n";
    message += "Special Attack +2\n";
    message += "Special Defense +2\n";
    message += "Speed +2";
    
    JOptionPane.showMessageDialog(null, message, "Level Up", JOptionPane.INFORMATION_MESSAGE);
}


    @Override
    public String toString() {
        return "Pokemon{name='" + name + "', type1='" + type1 + "', type2='" + type2 + "', hp=" + hp + ", attack=" + attack +
               ", defense=" + defense + ", specialAttack=" + sp_attack + ", specialDefense=" + sp_defense + 
               ", speed=" + speed + ", level=" + level + ", experiencePoints=" + exp + ", moves=" + moves + '}';
    }

    // Method to load Pokémon from CSV file
    public static List<Pokemon> loadPokemonFromCSV(String filePath) {
        List<Pokemon> pokemonList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("id")) continue; // Skip header
                String[] data = line.split(",");
                String name = data[1];
                String type1 = data[2];
                String type2 = data.length > 2 ? data[3] : "";
                int hp = Integer.parseInt(data[5]);
                int attack = Integer.parseInt(data[6]);
                int defense = Integer.parseInt(data[7]);
                int sp_attack = Integer.parseInt(data[8]);
                int sp_defense = Integer.parseInt(data[9]);
                int speed = Integer.parseInt(data[10]);
                //int level = Integer.parseInt(data[9]);
                Random rand = new Random();
                int level = rand.nextInt(99) + 1;

                Pokemon pokemon = new Pokemon(name, type1, type2, hp, attack, defense, sp_attack, sp_defense, speed, level);

                pokemonList.add(pokemon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pokemonList;
    }
}
