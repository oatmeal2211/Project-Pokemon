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
        private Pokemon playerPokemon;
        private Pokemon gymLeaderPokemon;
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
        private Player player;
private Player gymLeader;
private String currentLocation;

            
        public GymLeaderWithGUI(Player player, String currentLocation, Player gymLeader) throws FontFormatException, IOException {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setPreferredSize(new java.awt.Dimension(2147483647, 2147483647));
            this.player = player;
            this.currentLocation = currentLocation;
            this.gymLeader = gymLeader;
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
    
            // Generate random Pokémon for player and a fixed gym leader's Pokémon
            Random rand = new Random();
            playerPokemon = pokemonList.get(rand.nextInt(pokemonList.size()));
            gymLeaderPokemon = getGymLeaderPokemon(pokemonList, moves);
    
            // Initialize the battle
            battle = new Battle(playerPokemon, gymLeaderPokemon);
        }
    
        private Pokemon getGymLeaderPokemon(List<Pokemon> pokemonList, List<Move> moves) {
            // Logic to get gym leader's Pokémon based on player's location
            String gymLeaderPokemonName = getGymLeaderPokemonName(player.getLocation());
            
            for (Pokemon pokemon : pokemonList) {
                if (pokemon.getName().equals(gymLeaderPokemonName)) {
                    // Assign fixed moves to the gym leader's Pokémon
                    pokemon.learnMove(moves.get(0));
                    pokemon.learnMove(moves.get(1));
                    pokemon.learnMove(moves.get(2));
                    pokemon.learnMove(moves.get(3));
                    return pokemon;
                }
            }
            
            // Handle the case when gym leader's Pokémon is not found
            return null; // or handle error
        }

        private String getGymLeaderPokemonName(String location) {
    switch (location) {
        case "Pewter City":
            return "Geodude";
        case "Cerulean City":
            return "Staryu";
        case "Vermilion City":
            return "Pikachu";
        case "Celadon City":
            return "Tangela";
        case "Fuschia City": // wrong name change
            return "Koffing";
        case "Saffron City":
            return "Abra";
        case "Cinnabar Island":
            return "Ponyta";
        case "Viridian City":
            return "Rhyhorn";
        default:
            return null; // Handle unknown location
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

    for (int i = 0; i < playerMoves.size(); i++) {
        buttons[i].setText(playerMoves.get(i).getName());
        buttons[i].addActionListener(new MoveButtonListener(playerMoves.get(i)));
    }
}

private void displayInitialBattleState() throws FontFormatException {
    if (gymLeaderPokemon == null) {
        System.out.println("DEBUG: gymLeaderPokemon is null.");
    } else {
        System.out.println("DEBUG: gymLeaderPokemon is not null. Name: " + gymLeaderPokemon.getName());
    }
    
    if (playerPokemon == null) {
        System.out.println("DEBUG: playerPokemon is null.");
    } else {
        System.out.println("DEBUG: playerPokemon is not null. Name: " + playerPokemon.getName());
    }

    jTextArea1.append("You're challenging the Gym Leader with their " + gymLeaderPokemon.getName() + "!\n");
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
        Move gymLeaderMove = getRandomGymLeaderMove(); // Retrieve gym leader's move
        performMove(playerPokemon, gymLeaderPokemon, move);
        if (gymLeaderPokemon.getHp() > 0) { // Only perform gym leader's move if it's still alive
            performMove(gymLeaderPokemon, playerPokemon, gymLeaderMove);
        }
        try {
            updateBattleState();
        } catch (FontFormatException e1) {
            e1.printStackTrace();
        }
    }
}

private Move getRandomGymLeaderMove() {
    // Logic to retrieve a random move from the gym leader's moveset
    // Modify this based on your gym leader's moves
    return gymLeaderPokemon.getMoves().get(new Random().nextInt(gymLeaderPokemon.getMoves().size()));
}

private void updateBattleState() throws FontFormatException {
    jLabel1.setText(gymLeaderPokemon.getName() + " - HP: " + gymLeaderPokemon.getHp());
    jLabel2.setText(playerPokemon.getName() + " - HP: " + playerPokemon.getHp());

    if (playerPokemon.getHp() <= 0) {
        JOptionPane.showMessageDialog(this, "You lost the battle...", "Battle Result", JOptionPane.INFORMATION_MESSAGE);
        MainMenu mm = new MainMenu(player);
        mm.setVisible(true);
        mm.pack();
        mm.setLocationRelativeTo(null);
        dispose();
    } else if (gymLeaderPokemon.getHp() <= 0) {
        // Award badge to player
        player.earnBadge(new badge(getBadgeForCurrentGymLeader()));
        JOptionPane.showMessageDialog(this, "Congratulations! You defeated the Gym Leader and earned a badge!", "Battle Result", JOptionPane.INFORMATION_MESSAGE);
        MainMenu mm = new MainMenu(player);
        mm.setVisible(true);
        mm.pack();
        mm.setLocationRelativeTo(null);
        dispose();
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


// Method to get badge for current gym leader
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


    public void main(String args[]) throws FontFormatException, IOException {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new GymLeaderWithGUI(player, currentLocation, gymLeader).setVisible(true);
            } catch (FontFormatException | IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
