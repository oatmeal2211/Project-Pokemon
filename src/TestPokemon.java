import java.util.List;

public class TestPokemon {
    public static void main(String[] args) {
        String movesFilePath = "src\\Move.csv";
        String pokemonFilePath = "src\\pokemon.csv";

        // Load moves from CSV
        List<Move> moves = Move.loadMovesFromCSV(movesFilePath);

        // Load Pokémon from CSV and assign moves
        List<Pokemon> pokemonList = Pokemon.loadPokemonFromCSV(pokemonFilePath, moves);

        // Display loaded Pokémon and their moves
        for (Pokemon pokemon : pokemonList) {
            pokemon.displayStats();
            System.out.println();
        }
    }
}