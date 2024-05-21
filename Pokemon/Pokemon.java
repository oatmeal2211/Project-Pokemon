package Pokemon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

    // Type effectiveness mapping
    private static final Map<String, Map<String, Double>> typeEffectiveness = new HashMap<>();

    static {
        // Initialize type effectiveness chart
        // Example values; expand as necessary
        typeEffectiveness.put("Normal", Map.of("Rock", 0.5, "Ghost", 0.0, "Steel", 0.5));
        typeEffectiveness.put("Fire", Map.of("Fire", 0.5, "Water", 0.5, "Grass", 2.0, "Ice", 2.0, "Bug", 2.0, "Rock", 0.5, "Dragon", 0.5, "Steel", 2.0));
        typeEffectiveness.put("Water", Map.of("Fire", 2.0, "Water", 0.5, "Grass", 0.5, "Ground", 2.0, "Rock", 2.0, "Dragon", 0.5));
        typeEffectiveness.put("Electric", Map.of("Water", 2.0, "Electric", 0.5, "Grass", 0.5, "Ground", 0.0, "Flying", 2.0, "Dragon", 0.5));
        typeEffectiveness.put("Grass", Map.of("Fire", 0.5, "Water", 2.0, "Grass", 0.5, "Poison", 0.5, "Ground", 2.0, "Flying", 0.5, "Bug", 0.5, "Rock", 2.0, "Dragon", 0.5, "Steel", 0.5));
        typeEffectiveness.put("Ice", Map.of("Fire", 0.5, "Water", 0.5, "Grass", 2.0, "Ice", 0.5, "Ground", 2.0, "Flying", 2.0, "Dragon", 2.0, "Steel", 0.5));
        typeEffectiveness.put("Fighting", new HashMap<>());
        typeEffectiveness.get("Fighting").put("Normal", 2.0);
        typeEffectiveness.get("Fighting").put("Ice", 2.0);
        typeEffectiveness.get("Fighting").put("Poison", 0.5);
        typeEffectiveness.get("Fighting").put("Flying", 0.5);
        typeEffectiveness.get("Fighting").put("Psychic", 0.5);
        typeEffectiveness.get("Fighting").put("Bug", 0.5);
        typeEffectiveness.get("Fighting").put("Rock", 2.0);
        typeEffectiveness.get("Fighting").put("Ghost", 0.0);
        typeEffectiveness.get("Fighting").put("Dark", 2.0);
        typeEffectiveness.get("Fighting").put("Steel", 2.0);
        typeEffectiveness.get("Fighting").put("Fairy", 0.5);
        typeEffectiveness.put("Poison", Map.of("Grass", 2.0, "Poison", 0.5, "Ground", 0.5, "Rock", 0.5, "Ghost", 0.5, "Steel", 0.0, "Fairy", 2.0));
        typeEffectiveness.put("Ground", Map.of("Fire", 2.0, "Electric", 2.0, "Grass", 0.5, "Poison", 2.0, "Flying", 0.0, "Bug", 0.5, "Rock", 2.0, "Steel", 2.0));
        typeEffectiveness.put("Flying", Map.of("Electric", 0.5, "Grass", 2.0, "Fighting", 2.0, "Bug", 2.0, "Rock", 0.5, "Steel", 0.5));
        typeEffectiveness.put("Psychic", Map.of("Fighting", 2.0, "Poison", 2.0, "Psychic", 0.5, "Dark", 0.0, "Steel", 0.5));
        typeEffectiveness.put("Bug", Map.of("Fire", 0.5, "Grass", 2.0, "Fighting", 0.5, "Flying", 0.5, "Poison", 0.5, "Ghost", 0.5, "Steel", 0.5, "Fairy", 0.5));
        typeEffectiveness.put("Rock", Map.of("Fire", 2.0, "Ice", 2.0, "Fighting", 0.5, "Ground", 0.5, "Flying", 2.0, "Bug", 2.0, "Steel", 0.5));
        typeEffectiveness.put("Ghost", Map.of("Normal", 0.0, "Psychic", 2.0, "Ghost", 2.0, "Dark", 0.5));
        typeEffectiveness.put("Dragon", Map.of("Dragon", 2.0, "Steel", 0.5, "Fairy", 0.0));
        typeEffectiveness.put("Dark", Map.of("Fighting", 0.5, "Psychic", 2.0, "Ghost", 2.0, "Dark", 0.5, "Fairy", 0.5));
        typeEffectiveness.put("Steel", Map.of("Fire", 0.5, "Water", 0.5, "Electric", 0.5, "Ice", 2.0, "Rock", 2.0, "Steel", 0.5, "Fairy", 2.0));
        typeEffectiveness.put("Fairy", Map.of("Fire", 0.5, "Fighting", 2.0, "Poison", 0.5, "Dragon", 2.0, "Dark", 2.0, "Steel", 0.5));
    }

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

    private int scaleStat(int baseStat, int level) {
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

    public void forgetMove(String moveName) {
        Move moveToForget = findMoveByName(moveName);
        if (moveToForget != null) {
            moves.remove(moveToForget);
            System.out.println(name + " forgot " + moveName + ".");
        } else {
            System.out.println(name + " does not know " + moveName + ".");
        }
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

    public void attack(Pokemon target, String moveName) {
        Move move = findMoveByName(moveName);
        if (move != null) {
            if (move.getPp() > 0) {
                move.useMove();
                // Damage calculation considering type effectiveness
                int baseDamage = this.attack + move.getPower() - target.getDefense();
                double effectiveness = calculateTypeEffectiveness(move.getType(), target.getType1(), target.getType2());
                int damage = (int) (baseDamage * effectiveness);
                if (damage < 0) damage = 1; // Ensure at least 1 damage
                target.takeDamage(damage);
                System.out.println(this.name + " used " + move.getName() + " on " + target.getName() + " causing " + damage + " damage!");
                if (effectiveness > 1) {
                    System.out.println("It's super effective!");
                } else if (effectiveness < 1) {
                    System.out.println("It's not very effective...");
                }
            } else {
                System.out.println(this.name + " tried to use " + move.getName() + " but it has no PP left!");
            }
        } else {
            System.out.println(this.name + " doesn't know " + moveName);
        }
    }

    // Method to calculate type effectiveness
    private double calculateTypeEffectiveness(String moveType, String targetType1, String targetType2) {
        double effectiveness = 1.0;
        if (typeEffectiveness.containsKey(moveType)) {
            Map<String, Double> moveTypeMap = typeEffectiveness.get(moveType);
            if (moveTypeMap.containsKey(targetType1)) {
                effectiveness *= moveTypeMap.get(targetType1);
            }
            if (!targetType2.isEmpty() && moveTypeMap.containsKey(targetType2)) {
                effectiveness *= moveTypeMap.get(targetType2);
            }
        }
        return effectiveness;
    }

    // Method to find a move by name
    private Move findMoveByName(String name) {
        for (Move move : moves) {
            if (move.getName().equalsIgnoreCase(name)) {
                return move;
            }
        }
        return null;
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
        System.out.println(this.name + " leveled up to " + this.level + "!");
    }

    @Override
    public String toString() {
        return "Pokemon{name='" + name + "', type1='" + type1 + "', type2='" + type2 + "', hp=" + hp + ", attack=" + attack +
               ", defense=" + defense + ", specialAttack=" + sp_attack + ", specialDefense=" + sp_defense + 
               ", speed=" + speed + ", level=" + level + ", experiencePoints=" + exp + ", moves=" + moves + '}';
    }

    // Method to load Pokémon from CSV file
    public static List<Pokemon> loadPokemonFromCSV(String filePath, List<Move> availableMoves) {
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
                int level = rand.nextInt(100);

                Pokemon pokemon = new Pokemon(name, type1, type2, hp, attack, defense, sp_attack, sp_defense, speed, level);
                
                // Assign random moves (for demo purposes, you can enhance logic as needed)
                for (int i = 0; i < 4 && i < availableMoves.size(); i++) {
                    pokemon.learnMove(availableMoves.get(i));
                }

                pokemonList.add(pokemon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pokemonList;
    }
}
