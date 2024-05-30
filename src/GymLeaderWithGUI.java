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

public class GymLeaderWithGUI extends javax.swing.JFrame {
    private Battle battle;
    private GymLeaders gymLeader;
    private static  Player player;
    private Player opponentGymLeader;
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
    private Pokemon playerPokemon;
    private Pokemon opponentPokemon;

    public GymLeaderWithGUI(Player player, String currentLocation, GymLeaders gymLeaders) throws FontFormatException, IOException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(2147483647, 2147483647));
        this.player = player;
        this.opponentGymLeader = getOpponentGymLeader(currentLocation);
        this.gymLeader = gymLeaders;
        
        List<Pokemon> pokemonTeam = player.getPokemonTeam();
        if (!pokemonTeam.isEmpty()) {
            // Assuming the player's active Pokemon is the first one in the team
            this.playerPokemon = pokemonTeam.get(0);
        } else {
            // Handle the case when the player has no Pokemon in their team
            // You can throw an exception, display an error message, etc.
            throw new IllegalStateException("Player has no Pokemon in their team.");
        }
        // Initialize Pokemon and battle
        initBattle(player, gymLeader);
        
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

     private void initBattle(Player player, GymLeaders gymLeaders) {
        // Initialize the battle logic here
        Pokemon playerPokemon = null;
        
        // Get the current location using the method from the Player class
        String currentLocation = player.getLocation();
        
        // Retrieve the opponent gym leader based on the current location
        Player opponentGymLeader = null;
        switch (currentLocation) {
            case "Pewter City":
            opponentGymLeader = gymLeaders.getPewterCityLeader();
            break;
            case "Cerulean City":
            opponentGymLeader = gymLeaders.getCeruleanLeader();
            break;
            case "Vermilion City":
            opponentGymLeader = gymLeaders.getVermilionLeader();
            break;
            case "Celadon City":
            opponentGymLeader = gymLeaders.getCeladonCityLeader();
            break;
            case "Fuchsia City":
            opponentGymLeader = gymLeaders.getFuchsiaCityLeader();
            break;
            case "Saffron City":
            opponentGymLeader = gymLeaders.getSaffronCityLeader();
            break;
            case "Cinnabar Island":
            opponentGymLeader = gymLeaders.getCinnabarIslandPlayer();
            break;
            case "Viridian City":
            opponentGymLeader = gymLeaders.getViridianCityLeader();
            break;
            default:
                System.out.println("No gym leader available for the current location.");
                return; // Exit the method if no gym leader is found for the current location
        }
        
        // Get the Pokémon for the player and opponent gym leader
        if (!player.getPokemonTeam().isEmpty() && !opponentGymLeader.getPokemonTeam().isEmpty()) {
            playerPokemon = player.getPokemonTeam().get(0);  // Assuming player always has at least one Pokémon
            opponentPokemon = opponentGymLeader.getPokemonTeam().get(0);
        } else {
            System.out.println("Error initializing battle: Missing Pokémon.");
            return; // Exit the method if either player or opponent has no Pokémon
        }
        
        // Start the battle if both player and opponent have Pokémon
        battle = new Battle(playerPokemon, opponentPokemon);
    }
    
    
    
    
    private Player getOpponentGymLeader(String currentLocation) {
        GymLeaders gymLeaders = new GymLeaders();
        
        switch (currentLocation) {
            case "Pewter City":
                return gymLeaders.getPewterCityLeader();
            case "Cerulean City":
                return gymLeaders.getCeruleanLeader();
            case "Vermilion City":
                return gymLeaders.getVermilionLeader();
            case "Celadon City":
                return gymLeaders.getCeladonCityLeader();
            case "Fuchsia City":
                return gymLeaders.getFuchsiaCityLeader();
            case "Saffron City":
                return gymLeaders.getSaffronCityLeader();
            case "Cinnabar Island":
                return gymLeaders.getCinnabarIslandPlayer();
            case "Viridian City":
                return gymLeaders.getViridianCityLeader();
            default:
                // Return a default opponent (if location doesn't match any gym leader)
                return new Player("Default Opponent", "Default");
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

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) throws FontFormatException {
        MainMenu mm = new MainMenu(player);
        mm.setVisible(true);
        mm.pack();
        mm.setLocationRelativeTo(null);
        dispose();
    }                      

    private void setMoveButtons() {
        List<Move> playerMoves = playerPokemon.getMoves();
        JButton[] buttons = {jButton1, jButton2, jButton3, jButton4};
        jButton1.setEnabled(true);
        jButton2.setEnabled(true);
        jButton3.setEnabled(true);
        jButton4.setEnabled(true);
        
        for (int i = 0; i < playerMoves.size() && i < buttons.length; i++) {
            buttons[i].setText(playerMoves.get(i).getName());
            Move move = playerMoves.get(i); // Get the move for this button
            buttons[i].addActionListener(new MoveButtonListener(move)); // Attach listener
        }
    }
    
    
    
    private void displayInitialBattleState() throws FontFormatException {
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
            System.out.println("Button clicked: " + move.getName());
            Move opponentMove = opponentPokemon.getMoves().get(new Random().nextInt(opponentPokemon.getMoves().size()));
            performMove(playerPokemon, opponentPokemon, move);
            if (opponentPokemon.getHp() > 0) { // Only perform opponent's move if it's still alive
                performMove(opponentPokemon, playerPokemon, opponentMove);
            }
            try {
                updateBattleState();
            } catch (FontFormatException e1) {
                e1.printStackTrace();
            }
        }
    }
    
    
    private void updateBattleState() throws FontFormatException {
        jLabel1.setText(opponentPokemon.getName() + " - HP: " + opponentPokemon.getHp());
        jLabel2.setText(playerPokemon.getName() + " - HP: " + playerPokemon.getHp());
    
        if (playerPokemon.getHp() <= 0) {
            JOptionPane.showMessageDialog(this, "You lost the battle...", "Battle Result", JOptionPane.INFORMATION_MESSAGE);
            MainMenu mm = new MainMenu(player);
            mm.setVisible(true);
            mm.pack();
            mm.setLocationRelativeTo(null);
            dispose();
        } else if (opponentPokemon.getHp() <= 0) {
            int expGained = opponentPokemon.getLevel() * 5;
            playerPokemon.gainExperience(expGained);
            JOptionPane.showMessageDialog(this, "You won the battle!\nYou have gained " + expGained + " exp", "Battle Result", JOptionPane.INFORMATION_MESSAGE);
    
            // Award the badge to the player
            String badge = getBadgeForCurrentGymLeader();
            player.earnBadge(badge);
            JOptionPane.showMessageDialog(this, "You earned the " + badge + " badge!", "Badge Earned", JOptionPane.INFORMATION_MESSAGE);
    
            MainMenu mm = new MainMenu(player);
            mm.setVisible(true);
            mm.pack();
            mm.setLocationRelativeTo(null);
            dispose();
        }
    }
    
    private String getBadgeForCurrentGymLeader() {
        switch (player.getLocation()) {
            case "Pewter City":
                return "Boulder Badge";
            case "Cerulean City":
                return "Cascade Badge";
            case "Vermilion City":
                return "Thunder Badge";
            case "Celadon City":
                return "Rainbow Badge";
            case "Fuschia City":
                return "Soul Badge";
            case "Saffron City":
                return "Marsh Badge";
            case "Cinnabar Island":
                return "Volcano Badge";
            case "Viridian City":
                return "Earth Badge";
            default:
                return "";
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
                    GymLeaders gymLeader = new GymLeaders(); // Instantiate GymLeaders object
                    new GymLeaderWithGUI(player,player.getLocation(), gymLeader).setVisible(true);
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

