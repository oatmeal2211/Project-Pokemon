import java.util.Scanner;

public class SafariZone {

  private static String[] pokemonList;
  private final String[] cutePokemon = {"Jigglypuff", "Pikachu"};

  public SafariZone(int numberOfPokemon) {
    pokemonList = new String[numberOfPokemon];
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter the names of the Pokemon (one per line):");
    for (int i = 0; i < numberOfPokemon; i++) {
      pokemonList[i] = scanner.nextLine().toLowerCase(); // Convert input to lowercase
    }
  }

  public static String[] sortPokemon(int numberOfPokemon) {
    int centerIndex = numberOfPokemon / 2;

    System.out.println("\nSorting Process:");
    System.out.println("  Initial List: " + String.join(", ", pokemonList));

    // Place Pikachu in the center (check if it exists)
    int pikachuIndex = findIndex(pokemonList, "pikachu");
    if (pikachuIndex != -1) {
      swapElements(pokemonList, centerIndex, pikachuIndex);
      System.out.println("  After placing Pikachu in center: " + String.join(", ", pokemonList));
    }

    // Ensure Snorlax is at the end (check if it exists)
    int snorlaxIndex = findIndex(pokemonList, "snorlax");
    if (snorlaxIndex != -1 && snorlaxIndex != pokemonList.length - 1) {
      swapElements(pokemonList, snorlaxIndex, pokemonList.length - 1);
      System.out.println("  After placing Snorlax at end: " + String.join(", ", pokemonList));
    }

    // Place Eevee at the beginning (check if it exists)
    int eeveeIndex = findIndex(pokemonList, "eevee");
    if (eeveeIndex != -1 && eeveeIndex != 0) {
      swapElements(pokemonList, eeveeIndex, 0);
      System.out.println("  After placing Eevee at beginning: " + String.join(", ", pokemonList));
    }

    // Place Machop next to Snorlax (check if both exist)
    int machopIndex = findIndex(pokemonList, "machop");
    if (machopIndex != -1 && snorlaxIndex != -1) {
      swapElements(pokemonList, machopIndex, pokemonList.length - 2);
      System.out.println("  After placing Machop next to Snorlax: " + String.join(", ", pokemonList));
    }

    // Jigglypuff wants to be with cute Pokemon (prioritize Pikachu)
    int jigglypuffIndex = findIndex(pokemonList, "jigglypuff");
    int targetIndex = centerIndex - 1; // Prioritize left side of Pikachu
    if (jigglypuffIndex != -1 && jigglypuffIndex != targetIndex) {
      swapElements(pokemonList, jigglypuffIndex, targetIndex);
      System.out.println("  After placing Jigglypuff next to Pikachu: " + String.join(", ", pokemonList));
    } else if (jigglypuffIndex != -1 && targetIndex < 0) { // If left side is full, move to right
      targetIndex = centerIndex + 1;
      swapElements(pokemonList, jigglypuffIndex, targetIndex);
      System.out.println("  After placing Jigglypuff next to Pikachu (right side): " + String.join(", ", pokemonList));
    }

    // Bulbasaur won't be next to Charmander (swap if necessary)
    if ((pokemonList[0].equals("bulbasaur") && pokemonList[1].equals("charmander")) ||
        (pokemonList[pokemonList.length - 2].equals("bulbasaur") && pokemonList[pokemonList.length - 1].equals("charmander"))) {
      swapElements(pokemonList, 0, 1);
      System.out.println("  After separating Bulbasaur from Charmander: " + String.join(", ", pokemonList));
    }

    return pokemonList;
  }

  private static int findIndex(String[] list, String target) {
    for (int i = 0; i < list.length; i++) {
      if (list[i].equals(target)) {
        return i;
      }
    }
    return -1; // Target not found
  }

  private static void swapElements(String[] list, int index1, int index2) {
    String temp = list[index1];
    list[index1] = list[index2];
    list[index2] = temp;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("How many Pokemon do you want to sort? ");
    int numberOfPokemon = scanner.nextInt();
    //SafariZone sorter = new SafariZone(numberOfPokemon);
    String[] sortedList = SafariZone.sortPokemon(numberOfPokemon);

    System.out.println("\nSorted Pokemon List:");
    for (String pokemon : sortedList) {
      System.out.println(pokemon);
    }
    scanner.close();
  }
}
