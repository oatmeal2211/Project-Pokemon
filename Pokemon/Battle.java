package Pokemon;

import java.util.*;

/**
 * The Battle class represents a battle between two Pokémon.
 */
public class Battle {
    private Pokemon playerPokemon; // The Pokémon controlled by the player
    private Pokemon opponentPokemon; // The opponent's Pokémon

    /**
     * Constructor for the Battle class.
     * @param playerPokemon The Pokémon controlled by the player
     * @param opponentPokemon The opponent's Pokémon
     */
    public Battle(Pokemon playerPokemon, Pokemon opponentPokemon) {
        this.playerPokemon = playerPokemon;
        this.opponentPokemon = opponentPokemon;
    }

    /**
     * Starts the battle between the player's Pokémon and the opponent's Pokémon.
     */
    public void startBattle() {
        System.out.println("A wild " + opponentPokemon.getName() + " appeared!");
        System.out.println("Go! " + playerPokemon.getName() + "!");

        while (!isBattleOver(playerPokemon, opponentPokemon)) {
            // Player's turn
            Move playerMove = chooseMove(playerPokemon);
            attack(playerPokemon, opponentPokemon, playerMove);

            if (isBattleOver(playerPokemon, opponentPokemon)) {
                break;
            }

            // Opponent's turn
            Move opponentMove = chooseMove(opponentPokemon);
            attack(opponentPokemon, playerPokemon, opponentMove);
        }

        if (playerPokemon.getHp() <= 0) {
            System.out.println(playerPokemon.getName() + " fainted! You lost the battle.");
        } else if (opponentPokemon.getHp() <= 0) {
            System.out.println(opponentPokemon.getName() + " fainted! You won the battle.");
        }
    }

    /**
     * Chooses a move for the given Pokémon. 
     * @param pokemon The Pokémon for which a move is to be chosen
     * @return The chosen move
     */
    private Move chooseMove(Pokemon pokemon) {
        return pokemon.getMoves().get(0);
    }

    /**
     * Performs an attack by the attacker Pokémon on the defender Pokémon using the given move.
     * @param attacker The attacking Pokémon
     * @param defender The defending Pokémon
     * @param move The move being used for the attack
     */
    private void attack(Pokemon attacker, Pokemon defender, Move move) {
        System.out.println(attacker.getName() + " used " + move.getName() + "!");
        int damage = calculateDamage(attacker, defender, move);
        defender.setHp(defender.getHp() - damage);
        System.out.println(defender.getName() + " took " + damage + " damage! Remaining HP: " + defender.getHp());
    }

    /**
     * Calculates the damage caused by the attacker Pokémon on the defender Pokémon using the given move.
     * @param attacker The attacking Pokémon
     * @param defender The defending Pokémon
     * @param move The move being used for the attack
     * @return The damage caused by the attack
     */
    private int calculateDamage(Pokemon attacker, Pokemon defender, Move move) {
        Random random = new Random();
        int damage = (attacker.getAttack() * move.getPower() / defender.getDefense()) / 2;
        return damage + random.nextInt(5);  // Adding a random factor for variability
    }

    /**
     * Checks if the battle is over. The battle is over when either the player's Pokémon or the opponent's Pokémon has fainted.
     * @param playerPokemon The Pokémon controlled by the player
     * @param opponentPokemon The opponent's Pokémon
     * @return true if the battle is over, false otherwise
     */
    private boolean isBattleOver(Pokemon playerPokemon, Pokemon opponentPokemon) {
        return playerPokemon.getHp() <= 0 || opponentPokemon.getHp() <= 0;
    }
}


