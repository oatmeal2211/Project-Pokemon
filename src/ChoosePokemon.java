import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;

public class ChoosePokemon extends JFrame {
    //private static String playerName;
    private Player player;

    public ChoosePokemon(Player player) throws FontFormatException {
        //ChoosePokemon.playerName = playerName;
        this.player = player;
        initComponents();
        loadCustomFont();
        setBackgroundImage();
        scaleAndSetImage("/charmander.jpg", jLabel2);
        scaleAndSetImage("/squirtle.jpg", jLabel3);
        scaleAndSetImage("/bulbasaur.jpg", jLabel1);
        startTypewriterEffect();
    }

    private void loadCustomFont() throws FontFormatException {
        try (InputStream is = getClass().getResourceAsStream("/PressStart2P-Regular.ttf")) {
            if (is == null) {
                throw new FileNotFoundException("Font file not found in resources");
            }
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            Font font = customFont.deriveFont(Font.PLAIN, 11);
            Font title = customFont.deriveFont(Font.PLAIN, 30);
            Font heading = customFont.deriveFont(Font.PLAIN, 25);
            jLabel2.setFont(title);
            jLabel4.setFont(title);
            jButton1.setFont(font);
            jButton2.setFont(font);
            jButton3.setFont(font);
            jTextArea1.setFont(heading);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setBackgroundImage() {
        try (InputStream is = getClass().getResourceAsStream("/pinwheel-forest-pokemon-pixel-thumb.jpg")) {
            if (is == null) {
                throw new FileNotFoundException("Background image file not found in resources");
            }
            Image backgroundImage = ImageIO.read(is);
            ImageIcon imageIcon = new ImageIcon(backgroundImage);
            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
            JLabel backgroundLabel = new JLabel(imageIcon);
            backgroundLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
            layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
            jLabel1.setBounds(500, 60, 131, 121);
            jButton1.setBounds(750, 100, 356, 71);
            jLabel2.setBounds(500, 210, 131, 121);
            jButton2.setBounds(750, 240, 356, 71);
            jLabel3.setBounds(500, 360, 131, 121);
            jButton3.setBounds(750, 380, 356, 71);
            jScrollPane1.setBounds(100, 550, 1200, 200);
            jLabel4.setBounds(100, 500, 500, 30);
            layeredPane.add(jLabel1, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jButton1, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jLabel2, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jButton2, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jLabel3, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jButton3, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jScrollPane1, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jLabel4, JLayeredPane.PALETTE_LAYER);
            setContentPane(layeredPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(2147483647, 2147483647));
        jTextArea1.setColumns(90);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        jLabel4.setText("Oak");
        jLabel4.setForeground(Color.WHITE);
        jButton1.setText("Bulbasaur [Grass - Level 5]");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton1ActionPerformed(evt);
                } catch (FontFormatException e) {
                    e.printStackTrace();
                }
            }
        });
        jButton2.setText("Charmander [Fire - Level 5]");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton2ActionPerformed(evt);
                } catch (FontFormatException e) {
                    e.printStackTrace();
                }
            }
        });
        jButton3.setText("Squirtle [Water - Level 5]");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton3ActionPerformed(evt);
                } catch (FontFormatException e) {
                    e.printStackTrace();
                }
            }
        });
        pack();
    }

    private void startTypewriterEffect() {
        String text = "Right! So your name is " + player.getName() + "!\n\nWelcome to the world of Pokemon.\n\n" +
                "It's time to choose your starting pokemon.";

        Timer timer = new Timer(100, new ActionListener() {
            int index = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < text.length()) {
                    jTextArea1.append(String.valueOf(text.charAt(index)));
                    index++;
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws FontFormatException {
        // Create and add Bulbasaur to the player's team
        Pokemon bulbasaur = new Pokemon("Bulbasaur", "Grass", "Poison", 318, 45, 49, 49, 65, 65, 45);
        player.addPokemon(bulbasaur);
    
        // Proceed to the MainMenu only if the player has at least one Pokémon in their team
        if (!player.getPokemonTeam().isEmpty()) {
            JOptionPane.showMessageDialog(this, "You chose Bulbasaur, an amazing choice. Best of luck!");
            MainMenu mm = new MainMenu(player);
            mm.setVisible(true);
            mm.pack();
            mm.setLocationRelativeTo(null);
            this.dispose();
        } else {
            // Display an error message if the player has no Pokémon in their team
            JOptionPane.showMessageDialog(this, "Error: You must choose a Pokémon to start your journey!");
        }
    }
    
    // Similar modifications for jButton2ActionPerformed and jButton3ActionPerformed
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws FontFormatException {
        // Create and add Charmander to the player's team
        Pokemon charmander = new Pokemon("Charmander", "Fire", "None", 309, 39, 52, 43, 50, 50, 65);
        player.addPokemon(charmander);
    
        // Proceed to the MainMenu only if the player has at least one Pokémon in their team
        if (!player.getPokemonTeam().isEmpty()) {
            JOptionPane.showMessageDialog(this, "You chose Charmander, an amazing choice. Best of luck!");
            MainMenu mm = new MainMenu(player);
            mm.setVisible(true);
            mm.pack();
            mm.setLocationRelativeTo(null);
            this.dispose();
        } else {
            // Display an error message if the player has no Pokémon in their team
            JOptionPane.showMessageDialog(this, "Error: You must choose a Pokémon to start your journey!");
        }
    }
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) throws FontFormatException {
        // Create and add Squirtle to the player's team
        Pokemon squirtle = new Pokemon("Squirtle", "Water", "None", 314, 44, 48, 65, 50, 50, 43);
        player.addPokemon(squirtle);
    
        // Proceed to the MainMenu only if the player has at least one Pokémon in their team
        if (!player.getPokemonTeam().isEmpty()) {
            JOptionPane.showMessageDialog(this, "You chose Squirtle, an amazing choice. Best of luck!");
            MainMenu mm = new MainMenu(player);
            mm.setVisible(true);
            mm.pack();
            mm.setLocationRelativeTo(null);
            this.dispose();
        } else {
            // Display an error message if the player has no Pokémon in their team
            JOptionPane.showMessageDialog(this, "Error: You must choose a Pokémon to start your journey!");
        }
    }
    
    private void scaleAndSetImage(String imagePath, JLabel label) {
        try (InputStream is = getClass().getResourceAsStream(imagePath)) {
            if (is == null) {
                throw new FileNotFoundException("Image file not found in resources");
            }
            Image img = ImageIO.read(is);
            ImageIcon scaledIcon = new ImageIcon(img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
            label.setIcon(scaledIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Player player = new Player("PlayerName", "Pallet Town", MapPokemon.getMapData(), Move.loadMovesFromCSV("src\\Move.csv"));
                    new ChoosePokemon(player).setVisible(true); // Pass a valid player name
                } catch (FontFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
}
