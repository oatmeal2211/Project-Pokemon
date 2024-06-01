import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PokemonSort extends javax.swing.JFrame {
    private SafariZone safariZone;
    private static String[] pokemonList;
    private final String[] cutePokemon = {"Jigglypuff", "Pikachu"};
    static int numberOfPokemon;
    private static int selectedCount = 0;
    private Image backgroundImage;
    static Player player;

    public PokemonSort(int numberOfPokemon, Player player) throws IOException, FontFormatException {
        this.numberOfPokemon = numberOfPokemon;
        pokemonList = new String[numberOfPokemon];
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(2147483647, 2147483647));
        initComponents();
        loadCustomFont();
    }

    private void loadCustomFont() throws FontFormatException {
        try (InputStream is = getClass().getResourceAsStream("/PressStart2P-Regular.ttf")) {
            if (is == null) {
                throw new FileNotFoundException("Font file not found in resources");
            }
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            Font font = customFont.deriveFont(Font.PLAIN, 10);
            Font title = customFont.deriveFont(Font.PLAIN, 15);
            Font bigTitle = customFont.deriveFont(Font.PLAIN, 30);
            jLabel1.setFont(bigTitle);
            jLabel2.setFont(bigTitle);
            jTextArea1.setFont(title);
            jButton1.setFont(font);
            jComboBox1.setFont(title);
            jLabel1.setForeground(Color.WHITE);
            jLabel2.setForeground(Color.WHITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() throws IOException {
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jLabel1.setText("Safari Zone");
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        jLabel2.setText("Select your pokemon:");
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton1ActionPerformed(evt);
                } catch (FontFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws FontFormatException, IOException {
                MainMenu mm = new MainMenu(player);
                mm.setVisible(true);
                mm.pack();
                mm.setLocationRelativeTo(null);
                dispose();
            }
        });

        getContentPane().setLayout(new BorderLayout());

        // Load the background image
        try (InputStream is = getClass().getResourceAsStream("/a56806bce812d4ec85665792d83b809b.png")) {
            if (is == null) {
                throw new FileNotFoundException("Background image file not found in resources");
            }
            backgroundImage = ImageIO.read(is);
        }

        // Create a custom JPanel to draw the background image
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        getContentPane().add(backgroundPanel, BorderLayout.CENTER);

        // Add components to the background panel
        GroupLayout layout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(88, 88, 88)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 913, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 202, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 989, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(37, 37, 37)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(271, 271, 271))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(32, 32, 32)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(18, 18, 18)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(145, Short.MAX_VALUE))
        );

        pack();
    }

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
        String selectedPokemon = jComboBox1.getSelectedItem().toString();
        jTextArea1.append("Selected pokemon: " + selectedPokemon + "\n\n");
        // Add the selected Pokemon to the pokemonList array
        pokemonList[selectedCount] = selectedPokemon;

        // Increment the counter for selected Pokemon
        selectedCount++;

        // Check if all Pokemon have been selected
        if (selectedCount == numberOfPokemon) {
            // Perform sorting when all Pokemon are selected
            String[] sortedList = sortPokemon(numberOfPokemon);
            jTextArea1.append("Sorted Pokemon List:\n\n");
            for (int i = 0; i < sortedList.length; i++) {
                jTextArea1.append(sortedList[i]);
                if (i < sortedList.length - 1) {
                    jTextArea1.append(", ");
                }
            }
        }
    }

    public static String[] sortPokemon(int numberOfPokemon) {
        int centerIndex = numberOfPokemon / 2;
        StringBuilder sortingSteps = new StringBuilder();
        sortingSteps.append("\nSorting Process:\n\n");
        sortingSteps.append("Initial List: ").append(String.join(", ", pokemonList)).append("\n");

        int pikachuIndex = findIndex(pokemonList, "Pikachu");
        if (pikachuIndex != -1) {
            swapElements(pokemonList, centerIndex, pikachuIndex);
            sortingSteps.append("After placing Pikachu in center: ");
            sortingSteps.append("\n");
            sortingSteps.append(String.join(", ", pokemonList)).append("\n\n");
        }

        int snorlaxIndex = findIndex(pokemonList, "Snorlax");
        if (snorlaxIndex != -1 && snorlaxIndex != pokemonList.length - 1) {
            swapElements(pokemonList, snorlaxIndex, pokemonList.length - 1);
            sortingSteps.append("After placing Snorlax at end: ");
            sortingSteps.append("\n");
            sortingSteps.append(String.join(", ", pokemonList)).append("\n\n");
        }

        int eeveeIndex = findIndex(pokemonList, "Eevee");
        if (eeveeIndex != -1 && eeveeIndex != 0) {
            swapElements(pokemonList, eeveeIndex, 0);
            sortingSteps.append("After placing Eevee at beginning: ");
            sortingSteps.append("\n");
            sortingSteps.append(String.join(", ", pokemonList)).append("\n\n");
        }

        int machopIndex = findIndex(pokemonList, "Machop");
        if (machopIndex != -1 && snorlaxIndex != -1) {
            swapElements(pokemonList, machopIndex, pokemonList.length - 2);
            sortingSteps.append("After placing Machop next to Snorlax: ");
            sortingSteps.append("\n");
            sortingSteps.append(String.join(", ", pokemonList)).append("\n\n");
        }

        int jigglypuffIndex = findIndex(pokemonList, "Jigglypuff");
        int targetIndex = centerIndex - 1;
        if (jigglypuffIndex != -1 && jigglypuffIndex != targetIndex) {
            swapElements(pokemonList, jigglypuffIndex, targetIndex);
            sortingSteps.append("After placing Jigglypuff next to Pikachu: ");
            sortingSteps.append("\n");
            sortingSteps.append(String.join(", ", pokemonList)).append("\n\n");
        } else if (jigglypuffIndex != -1 && targetIndex < 0) {
            targetIndex = centerIndex + 1;
            swapElements(pokemonList, jigglypuffIndex, targetIndex);
            sortingSteps.append("After placing Jigglypuff next to Pikachu (right side): ");
            sortingSteps.append("\n");
            sortingSteps.append(String.join(", ", pokemonList)).append("\n\n");
        }

        if ((pokemonList[0].equals("Bulbasaur") && pokemonList[1].equals("Charmander")) ||
            (pokemonList[pokemonList.length - 2].equals("Bulbasaur") && pokemonList[pokemonList.length - 1].equals("Charmander"))) {
            swapElements(pokemonList, 0, 1);
            sortingSteps.append("After separating Bulbasaur from Charmander: ");
            sortingSteps.append("\n");
            sortingSteps.append(String.join(", ", pokemonList)).append("\n\n");
        }

        jTextArea1.append(sortingSteps.toString());

        return pokemonList;
    }

    private static int findIndex(String[] list, String target) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }

    private static void swapElements(String[] list, int index1, int index2) {
        String temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }

    public void loadPokemon(int numberOfPokemon) {
        pokemonList = new String[numberOfPokemon];

        String pokemonFilePath = "src\\pokemon.csv";
        List<Move> availableMoves = new ArrayList<>(); // You need to populate this list with moves
        List<Pokemon> pokemonData = Pokemon.loadPokemonFromCSV(pokemonFilePath, availableMoves);

        jComboBox1.removeAllItems();

        for (Pokemon pokemon : pokemonData) {
            jComboBox1.addItem(pokemon.getName());
        }

        jComboBox1.addActionListener(this::jComboBox1ActionPerformed);
    }

    public class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
            setLayout(new BorderLayout());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PokemonSort pokemonSort;
                try {
                    pokemonSort = new PokemonSort(numberOfPokemon, player);
                    pokemonSort.setVisible(true);
                    pokemonSort.setLocationRelativeTo(null);
                } catch (IOException | FontFormatException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to load resources: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton jButton1;
    // End of variables declaration
}
