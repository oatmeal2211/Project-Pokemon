package Pokemon;


import java.util.List;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;


public class Battle {
    private Pokemon playerPokemon;
    private Pokemon opponentPokemon;
    private Scanner scanner;

    public Battle(Pokemon playerPokemon, Pokemon opponentPokemon) {
        this.playerPokemon = playerPokemon;
        this.opponentPokemon = opponentPokemon;
        this.scanner = new Scanner(System.in);
    }

    // Method to calculate damage
    private DamageInfo calculateDamage(Pokemon attacker, Pokemon defender, Move move) {
        if (move.getCategory().equalsIgnoreCase("Status")) {
            // Status moves do not deal damage
            return new DamageInfo(0, "no effect");
        }

        double typeEffectiveness = TypeEffectiveness.getEffectiveness(move.getType(), defender.getType1());
        if (defender.getType2() != null) {
            typeEffectiveness *= TypeEffectiveness.getEffectiveness(move.getType(), defender.getType2());
        }

        String effectivenessMessage;
        if (typeEffectiveness > 1) {
            effectivenessMessage = "It's super effective!";
        } else if (typeEffectiveness < 1) {
            effectivenessMessage = "It's not very effective...";
        } else {
            effectivenessMessage = "";
        }

        double randomFactor = 0.85 + (new Random().nextDouble() * 0.15);
        int power = move.getPower() != null ? move.getPower() : 0;
        int damage = (int) (((2 * attacker.getLevel() / 5 + 2) * power * (move.getCategory().equalsIgnoreCase("Physical") ? attacker.getAttack() : attacker.getSpecialAttack()) / (move.getCategory().equalsIgnoreCase("Physical") ? defender.getDefense() : defender.getSpecialDefense()) / 50 + 2) * typeEffectiveness * randomFactor);

        return new DamageInfo(damage, effectivenessMessage);
    }

    // Method to simulate one turn of the battle
    public void executeTurn(Move playerMove, Move opponentMove) {
        // Determine the attack order based on speed
        boolean playerFirst = playerPokemon.getSpeed() >= opponentPokemon.getSpeed();

        if (playerFirst) {
            performMove(playerPokemon, opponentPokemon, playerMove);
            if (opponentPokemon.getHp() > 0) {
                try {
                    Thread.sleep(1000); // 1-second delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                performMove(opponentPokemon, playerPokemon, opponentMove);
            }
        } else {
            performMove(opponentPokemon, playerPokemon, opponentMove);
            if (playerPokemon.getHp() > 0) {
                try {
                    Thread.sleep(1000); // 1-second delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                performMove(playerPokemon, opponentPokemon, playerMove);
            }
        }

        displayPokemonHealth();
    }

    private void performMove(Pokemon attacker, Pokemon defender, Move move) {
        System.out.println(attacker.getName() + " used " + move.getName() + "!");
        DamageInfo damageInfo = calculateDamage(attacker, defender, move);
        defender.setHp(defender.getHp() - damageInfo.getDamage());
        System.out.println("It dealt " + damageInfo.getDamage() + " damage!");
        if (!damageInfo.getEffectivenessMessage().isEmpty()) {
            System.out.println(damageInfo.getEffectivenessMessage());
        }

        move.setPp(move.getPp() - 1); // Decrement PP after use

        if (defender.getHp() <= 0) {
            System.out.println(defender.getName() + " fainted!");
        }

    }

    public void displayPokemonHealth() {
        System.out.println("\nPlayer's Pokémon: " + playerPokemon.getName() + " - HP: " + playerPokemon.getHp());
        System.out.println("Opponent's Pokémon: " + opponentPokemon.getName() + " - HP: " + opponentPokemon.getHp() + "\n");
    }

    // Method to let the player choose a move
    private Move chooseMove(Pokemon pokemon) {
        System.out.println("Choose a move:");
        for (int i = 0; i < pokemon.getMoves().size(); i++) {

            Move move = pokemon.getMoves().get(i);
            System.out.println((i + 1) + ": " + move.getName() + " (PP: " + move.getPp() + ")");
        }

        int choice = -1;
        while (choice < 1 || choice > pokemon.getMoves().size()) {
            System.out.print("Enter the number of the move: ");
            choice = scanner.nextInt();
        }

        return pokemon.getMoves().get(choice - 1);
    }

    // Method to start the battle
    public void startBattle() {
        System.out.println("A wild " + opponentPokemon.getName() + " appeared!");
        System.out.println("Go " + playerPokemon.getName() + "!");

        // Simple turn-based battle loop
        while (playerPokemon.getHp() > 0 && opponentPokemon.getHp() > 0) {
            Move playerMove = chooseMove(playerPokemon);
            // For simplicity, the opponent chooses a random move
            Move opponentMove = opponentPokemon.getMoves().get(new Random().nextInt(opponentPokemon.getMoves().size()));
            executeTurn(playerMove, opponentMove);
        }

        if (playerPokemon.getHp() > 0) {
            System.out.println("You won the battle!");
            System.out.println("You have gained " + opponentPokemon.getLevel()*5 + " exp");
            playerPokemon.gainExperience(opponentPokemon.getLevel()*5);
        } else {
            System.out.println("You lost the battle...");
        }
    }

    public static void main(String[] args) {
        String movesFilePath = "Pokemon/Move.csv";
        String pokemonFilePath = "Pokemon/pokemon.csv";

        List<Move> moves = Move.loadMovesFromCSV(movesFilePath);
        List<Pokemon> pokemonList = Pokemon.loadPokemonFromCSV(pokemonFilePath, moves);

        // Generate random Pokémon for player and opponent
        Random rand = new Random();
        Pokemon playerPokemon = pokemonList.get(rand.nextInt(pokemonList.size()));
        Pokemon opponentPokemon = pokemonList.get(rand.nextInt(pokemonList.size()));

        // Start a battle
        Battle battle = new Battle(playerPokemon, opponentPokemon);
        battle.startBattle();
    }
}

class DamageInfo {
    private int damage;
    private String effectivenessMessage;

    public DamageInfo(int damage, String effectivenessMessage) {
        this.damage = damage;
        this.effectivenessMessage = effectivenessMessage;
    }

    public int getDamage() {
        return damage;
    }

    public String getEffectivenessMessage() {
        return effectivenessMessage;
    }
}
