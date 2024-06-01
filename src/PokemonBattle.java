import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class PokemonBattle extends javax.swing.JFrame {
    private Battle battle;
    private Pokemon playerPokemon;
    private Pokemon opponentPokemon;
    static Player player;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private Image backgroundImage;
    private static String currentLocation;
    private int originalPlayerPokemonHP;
    
    public PokemonBattle(Player player,String currentLocation) throws FontFormatException, IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(2147483647, 2147483647));
        this.player = player;
        this.currentLocation = currentLocation;
        // Initialize Pokemon and battle
        initBattle();
        
        // Initialize components
        initComponents();
        loadCustomFont();
        
        // Set move buttons with moves and their action listeners
        setMoveButtons();
        
        // Start battle
        displayInitialBattleState();
    }
    
    
    private void loadCustomFont() throws FontFormatException {
        try (InputStream is = getClass().getResourceAsStream("/PressStart2P-Regular.ttf")) {
             if (is == null) {
                 throw new FileNotFoundException("Font file not found in resources");
             }
             Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
             Font font = customFont.deriveFont(Font.PLAIN, 10);
             Font title = customFont.deriveFont(Font.PLAIN, 15);
             Font bigTitle = customFont.deriveFont(Font.PLAIN, 20);
             jLabel1.setFont(bigTitle);
             jLabel2.setFont(bigTitle);
             jLabel3.setFont(title);
             jLabel4.setFont(title);
             jLabel5.setFont(bigTitle);
             jLabel6.setFont(bigTitle);
             jTextArea1.setFont(title);
             jButton1.setFont(font);
             jButton2.setFont(font);
             jButton3.setFont(font);
             jButton4.setFont(font);
             jButton5.setFont(font);
             Color whiteColor = Color.WHITE;
         jLabel1.setForeground(whiteColor);
         jLabel2.setForeground(whiteColor);
         jLabel5.setForeground(whiteColor);
         jLabel6.setForeground(whiteColor);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

    private void initBattle() {
        String movesFilePath = "src/Move.csv";
        String pokemonFilePath = "src/pokemon.csv";

        List<Move> moves = Move.loadMovesFromCSV(movesFilePath);
        List<Pokemon> pokemonList = Pokemon.loadPokemonFromCSV(pokemonFilePath, moves);

        // Generate random Pokémon for player and opponent
        Random rand = new Random();
        if (!player.getPokemonTeam().isEmpty()) {
            playerPokemon = player.getPokemonTeam().get(rand.nextInt(player.getPokemonTeam().size()));
            originalPlayerPokemonHP = playerPokemon.getHp();
        }
        opponentPokemon = pokemonList.get(rand.nextInt(pokemonList.size()));
        opponentPokemon.setLevel(playerPokemon.getLevel() + rand.nextInt(5) - rand.nextInt(5));
        opponentPokemon.setHp(Pokemon.scaleStat(opponentPokemon.getBaseHp(), opponentPokemon.getLevel()));
        opponentPokemon.setAttack(Pokemon.scaleStat(opponentPokemon.getAttack(), opponentPokemon.getLevel()));
        opponentPokemon.setDefense(Pokemon.scaleStat(opponentPokemon.getDefense(), opponentPokemon.getLevel()));
        opponentPokemon.setSpecialAttack(Pokemon.scaleStat(opponentPokemon.getSpecialAttack(), opponentPokemon.getLevel()));
        opponentPokemon.setSpecialDefense(Pokemon.scaleStat(opponentPokemon.getSpecialDefense(), opponentPokemon.getLevel()));
        opponentPokemon.setSpeed(Pokemon.scaleStat(opponentPokemon.getSpeed(), opponentPokemon.getLevel()));
        assignRandomMoves(playerPokemon, moves, rand);

        // Initialize the battle
        battle = new Battle(playerPokemon, opponentPokemon);
    }

    private void assignRandomMoves(Pokemon pokemon, List<Move> allMoves, Random rand) {
        int movesToAssign = Math.min(4, allMoves.size());  // Assign up to 4 moves, or less if there aren't enough moves available
        for (int i = 0; i < movesToAssign; i++) {
            Move randomMove = allMoves.get(rand.nextInt(allMoves.size()));
            pokemon.learnMove(randomMove);
        }
    }

    private void initComponents() throws IOException {
        
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();


        jLabel1.setText("Opponent Pokemon");
        jLabel2.setText("Player Pokemon");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton5.setText("Back");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton5ActionPerformed(evt);
                } catch (FontFormatException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        jLabel5.setText("Your Pokemon:");
        jLabel6.setText("Opponent's Pokemon:");

        getContentPane().setLayout(new BorderLayout());

        // Load the background image
        try (InputStream is = getClass().getResourceAsStream("/a56806bce812d4ec85665792d83b809b.png")) {
            if (is == null) {
                throw new FileNotFoundException("Background image file not found in resources");
            }
            backgroundImage = ImageIO.read(is);}

            // Create a custom JPanel to draw the background image
            BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
            getContentPane().add(backgroundPanel, BorderLayout.CENTER);

        // Add components to the background panel
        GroupLayout layout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(179, 179, 179)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>                                              

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) throws FontFormatException, IOException {
        MainMenu mm = new MainMenu(player);
        mm.setVisible(true);
        mm.pack();
        mm.setLocationRelativeTo(null);
        dispose();
    }                      

    private void setMoveButtons() {
        List<Move> playerMoves = playerPokemon.getMoves();
        JButton[] buttons = {jButton1, jButton2, jButton3, jButton4};
        
        for (int i = 0; i < playerMoves.size(); i++) {
            buttons[i].setText(playerMoves.get(i).getName());
            buttons[i].addActionListener(new MoveButtonListener(playerMoves.get(i)));
        }
    }
    
    private void displayInitialBattleState() throws FontFormatException, IOException {
        jTextArea1.append("A wild " + opponentPokemon.getName() + " appeared!\n");
        jTextArea1.append("\n");
        jTextArea1.append("Go " + playerPokemon.getName() + "!\n");
        jTextArea1.append("\n");
        updateBattleState();
    }
    
    private class MoveButtonListener implements ActionListener {
        private Move move;

        public MoveButtonListener(Move move) {
            this.move = move;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Move opponentMove = opponentPokemon.getMoves().get(new Random().nextInt(opponentPokemon.getMoves().size()));
            performMove(playerPokemon, opponentPokemon, move);
            if (opponentPokemon.getHp() > 0) { // Only perform opponent's move if it's still alive
                performMove(opponentPokemon, playerPokemon, opponentMove);
            }
            try {
                updateBattleState();
            } catch (FontFormatException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    private void concludeBattle() throws FontFormatException, IOException {
        playerPokemon.setHp(originalPlayerPokemonHP);  // Reset HP to its original value
        MainMenu mm = new MainMenu(player);
        mm.setVisible(true);
        mm.pack();
        mm.setLocationRelativeTo(null);
        dispose();
    }
    
    private void updateBattleState() throws FontFormatException, IOException {
        jLabel1.setText(opponentPokemon.getName() + " - HP: " + opponentPokemon.getHp());
        jLabel2.setText(playerPokemon.getName() + " - HP: " + playerPokemon.getHp());
    
        if (playerPokemon.getHp() <= 0) {
            JOptionPane.showMessageDialog(this, "You lost the battle...", "Battle Result", JOptionPane.INFORMATION_MESSAGE);
            concludeBattle();
        } else if (opponentPokemon.getHp() <= 0) {
            int expGained = opponentPokemon.getLevel() * 5;
            playerPokemon.gainExperience(expGained);
            opponentPokemon.restoreFullHealth();
            JOptionPane.showMessageDialog(this, "You won the battle!\nYou have gained " + expGained + " exp", "Battle Result", JOptionPane.INFORMATION_MESSAGE);
    
            // Add the opponent's Pokémon to the player's team
            opponentPokemon.setHp(opponentPokemon.getBaseHp());
            addPokemon(opponentPokemon);
            // Display message in JOptionPane
            JOptionPane.showMessageDialog(this, opponentPokemon.getName() + " added to your team!", "Battle Result", JOptionPane.INFORMATION_MESSAGE);
            concludeBattle();
        }
    }
    
    public void addPokemon(Pokemon pokemon) {
        if (player.getPokemonTeam().size() < 6) {
            pokemon.restoreFullHealth();
            player.getPokemonTeam().add(pokemon);
            
        } else {
            // Display JOptionPane with combobox to remove a Pokémon
            JComboBox<String> comboBox = new JComboBox<>();
            for (Pokemon p : player.getPokemonTeam()) {
                comboBox.addItem(p.getName());
            }
    
            int choice = JOptionPane.showConfirmDialog(
                this, 
                comboBox, 
                "Your team is full. Select a Pokémon to remove:", 
                JOptionPane.OK_CANCEL_OPTION, 
                JOptionPane.PLAIN_MESSAGE
            );
            
            // Set the preferred width of the dialog
            JDialog dialog = (JDialog) SwingUtilities.getRoot(comboBox);
            dialog.setPreferredSize(new Dimension(300, dialog.getPreferredSize().height));
            
            if (choice == JOptionPane.OK_OPTION) {
                String selectedPokemonName = (String) comboBox.getSelectedItem();
                for (Pokemon p : player.getPokemonTeam()) {
                    if (p.getName().equals(selectedPokemonName)) {
                        player.removePokemon(p);
                        break;
                    }
                }
                
                player.getPokemonTeam().add(pokemon); // Add the new Pokémon after removing one
                JOptionPane.showMessageDialog(
                    this, 
                    pokemon.getName() + " added to your team!", 
                    "Team Update", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                    this, 
                    "You decided not to add " + pokemon.getName() + " to your team.", 
                    "Team Update", 
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
            
                
    }
}
    
    private void performMove(Pokemon attacker, Pokemon defender, Move move) {
        jTextArea1.append(attacker.getName() + " used " + move.getName() + "!\n");
        jTextArea1.append("\n");
        DamageInfo damageInfo = Battle.calculateDamage(attacker, defender, move);
        defender.setHp(defender.getHp() - damageInfo.getDamage());
        jTextArea1.append("It dealt " + damageInfo.getDamage() + " damage!\n");
        jTextArea1.append("\n");
        if (!damageInfo.getEffectivenessMessage().isEmpty()) {
            jTextArea1.append(damageInfo.getEffectivenessMessage() + "\n");
            jTextArea1.append("\n");
            
        }
        move.setPp(move.getPp() - 1); // Decrement PP after use
    
        if (defender.getHp() <= 0) {
            jTextArea1.append(defender.getName() + " fainted!\n");
        }
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
            // Draw the background image
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new PokemonBattle(player, currentLocation).setVisible(true);
                } catch (FontFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
}
