package Pokemon;

import java.util.HashMap;
import java.util.Map;

public class TypeEffectiveness {
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

    public static double getEffectiveness(String attackType, String defenseType) {
        if (typeEffectiveness.containsKey(attackType)) {
            return typeEffectiveness.get(attackType).getOrDefault(defenseType, 1.0);
        }
        return 1.0;
    }
}
