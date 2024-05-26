package Pokemon;
import java.util.List;

public class TestPokemon {
    public static void main(String[] args) {
        String movesFilePath = "Pokemon/Move.csv";
        String pokemonFilePath = "Pokemon/pokemon.csv";

        // Load moves from CSV
        List<Move> moves = Move.loadMovesFromCSV(movesFilePath);

        // Load Pokémon from CSV and assign moves
        List<Pokemon> pokemonList = Pokemon.loadPokemonFromCSV(pokemonFilePath, moves);

        // Display loaded Pokémon and their moves
        for (Pokemon pokemon : pokemonList) {
            pokemon.displayStats();
            System.out.println();
        }

        // Example battle scenario
        if (pokemonList.size() >= 2) {
            Pokemon pokemon1 = pokemonList.get(0);
            Pokemon pokemon2 = pokemonList.get(1);
            pokemon1.attack(pokemon2, pokemon1.getMoves().get(0).getName());
            pokemon2.attack(pokemon1, pokemon2.getMoves().get(0).getName());
        }

        // Example of forgetting a move and learning a new one
        if (!pokemonList.isEmpty()) {
            Pokemon examplePokemon = pokemonList.get(0);
            examplePokemon.forgetMove(examplePokemon.getMoves().get(0).getName());
            if (moves.size() > 4) { // Ensuring there's a move to learn
                examplePokemon.learnMove(moves.get(4)); // Attempting to learn a new move
            }
            examplePokemon.displayStats(); // Display stats after learning new move
        }
    }
}