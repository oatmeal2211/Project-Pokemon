import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

// Assuming RegionExplorer is a generic class with a method getNeighbours
public class MainMenu extends javax.swing.JFrame {
    RegionExplorer<String, Integer> regionExplorer = MapPokemon.getMapData();
    ArrayList<String> list = new ArrayList<>();
    static String currentLocation;
    static Player player;
    private GymLeaders gymLeaders;
    JButton pokemonSortButton;
    JButton rivalRaceButton;

    /*private void updateCurrentLocation() {
        currentLocation = player.getLocation();
        jLabel2.setText(currentLocation); // Update jLabel2 with the current location
    }*/

    public MainMenu(Player player) throws FontFormatException, IOException {
       this.player = player;
        currentLocation = player.getLocation(); 
        initComponents();
        loadCustomFont();
        setBackgroundImage();
        MapPokemon map = new MapPokemon(); // Instantiate your MapPokemon class
        gymLeaders = new GymLeaders();
        // Set up jComboBox2
        
        updateGymLeaderOptions(player.getLocation());
        // Call setCurrentLocation with the initial current location
        setCurrentLocation(player.getLocation()); // Set the current location after initializing components
        
        updateCurrentLocation();
    }
    

    private void setBackgroundImage() {
            try (InputStream is = getClass().getResourceAsStream("a56806bce812d4ec85665792d83b809b.png")) {
                if (is == null) {
                    throw new FileNotFoundException("Background image file not found in resources");
                }
                Image backgroundImage = ImageIO.read(is);
                ImageIcon imageIcon = new ImageIcon(backgroundImage);
            // Create a layered pane
            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));

            // Add a label to display the background image
            JLabel backgroundLabel = new JLabel(imageIcon);
            backgroundLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
            layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

            

            // Adjust the size and position of jPanel1 to match the background image
            jPanel1.setOpaque(true); // Make jPanel1 transparent
            jPanel1.setBounds(0, 0, 300, imageIcon.getIconHeight()); // Adjust position and size
            jPanel1.revalidate(); // Revalidate the panel

            // Add the side menu bar components
            jButton2.setBounds(10, 50, 194, 40);
            jButton3.setBounds(10, 110, 194, 40);
            jButton5.setBounds(10, 170, 194, 40);
            jButton4.setBounds(10, 230, 194, 40);
            layeredPane.add(jPanel1, JLayeredPane.PALETTE_LAYER);

            // Add other components
            jLabel1.setBounds(520, 50, 800, 30);
            jLabel2.setBounds(620, 120, 800, 30);
            jLabel3.setBounds(800, 300, 800, 30);
            jLabel4.setBounds(400, 300, 800, 30);
            jLabel5.setBounds(1200, 300, 800, 30);
            jComboBox1.setBounds(400, 350, 200, 30);
            jComboBox2.setBounds(800, 350, 200, 30);
            jComboBox3.setBounds(1200, 350, 200, 30);
            layeredPane.add(jLabel1, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jLabel2, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jLabel3, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jLabel4, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jLabel5, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jComboBox1, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jComboBox2, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jComboBox3, JLayeredPane.PALETTE_LAYER);

            // Set layered pane as the content pane
            setContentPane(layeredPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomFont() throws FontFormatException {
        try (InputStream is = getClass().getResourceAsStream("/PressStart2P-Regular.ttf")) {
            if (is == null) {
                throw new FileNotFoundException("Font file not found in resources");
            }
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            Font font = customFont.deriveFont(Font.PLAIN, 11);
            Font title = customFont.deriveFont(Font.PLAIN, 37);
            // Set the font for the labels and buttons
            jLabel1.setFont(title);
            jLabel2.setFont(title);
            jLabel3.setFont(font);
            jLabel5.setFont(font);
            jLabel4.setFont(font);
            jComboBox1.setFont(font);
            jComboBox2.setFont(font);
            jComboBox3.setFont(font);
            jButton2.setFont(font);
            jButton3.setFont(font);
            jButton4.setFont(font);
            jButton5.setFont(font);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomFontSpecial(JButton button) throws FontFormatException {
        try (InputStream is = getClass().getResourceAsStream("/PressStart2P-Regular.ttf")) {
            if (is == null) {
                throw new FileNotFoundException("Font file not found in resources");
            }
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            Font font = customFont.deriveFont(Font.PLAIN, 11);
            Font title = customFont.deriveFont(Font.PLAIN, 37);
            // Set the font for the labels and buttons
            button.setFont(font);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setCurrentLocation(String newLocation) {
        currentLocation = newLocation;
        player.setLocation(newLocation); // Update the player's location
        jLabel2.setText(currentLocation);
        jLabel3.setText(jLabel3Text(currentLocation));
        updateCurrentLocation();
        populateAdjacentCities(currentLocation);
        if (!regionExplorer.hasVertex(currentLocation)) {
            System.out.println("Invalid location: " + currentLocation);
        }
    }

    private void updateCurrentLocation() {
        jLabel2.setText(currentLocation);
        System.out.println("DEBUG: Current Location updated to: " + currentLocation);
    }

    private void populateAdjacentCities(String currentCity) {
        ArrayList<String> adjacentCities = regionExplorer.getNeighbours(currentCity);
        if (adjacentCities != null && !adjacentCities.isEmpty()) {
            jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(adjacentCities.toArray(new String[0])));
        } else {
            jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));
        }
        for (ActionListener al : jComboBox1.getActionListeners()) {
            jComboBox1.removeActionListener(al);
        }
        jComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCity = (String) jComboBox1.getSelectedItem();
                if (selectedCity != null && !selectedCity.equals(currentLocation)) {
                    System.out.println("DEBUG: Moving from " + currentLocation + " to " + selectedCity);
                    setCurrentLocation(selectedCity);
                    try {
                        updateGymLeaderOptions(selectedCity);
                    } catch (FontFormatException | IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        System.out.println("DEBUG: Adjacent cities for " + currentCity + ": " + adjacentCities);
    }

    
    private void jButton2ActionPerformed(ActionEvent evt) throws FontFormatException {
        // Assuming you have already retrieved the map data and stored it in a variable named 'map'
        RegionExplorer<String, Integer> map = MapPokemon.getMapData();
    
        // Pass the map data to the ShowMap constructor
        ShowMap sm = new ShowMap(player, map);
        sm.setVisible(true);
        sm.pack();
        sm.setLocationRelativeTo(null);
        dispose();
    }
    
    // Add similar setCurrentLocation calls in other relevant places where location changes occur
    
    /*private void setCurrentLocation(String newLocation) {
        currentLocation = newLocation;
        jLabel2.setText(currentLocation);
        jLabel3.setText(jLabel3Text(currentLocation));
        populateAdjacentCities(currentLocation);
        if (!regionExplorer.hasVertex(currentLocation)) {
            System.out.println("Invalid location: " + currentLocation);
        }
    }
    

    /*private void jButton2ActionPerformed(ActionEvent evt) throws FontFormatException {
        // Assuming you have already retrieved the map data and stored it in a variable named 'map'
        RegionExplorer<String, Integer> map = MapPokemon.getMapData();

        // Pass the map data to the ShowMap constructor
        ShowMap sm = new ShowMap(player, map);
        sm.setVisible(true);
        sm.pack();
        sm.setLocationRelativeTo(null);
        dispose();
    }*/

    private void jButton3ActionPerformed(ActionEvent evt) throws FontFormatException {
        ShowPokemon sp = new ShowPokemon(player);
        sp.setVisible(true);
        sp.pack();
        sp.setLocationRelativeTo(null);
        dispose();
    }

    private void jButton4ActionPerformed(ActionEvent evt) {
        dispose(); // Corrected disposal of the current frame
    }

    private void jButton5ActionPerformed(ActionEvent evt) throws FontFormatException {
        ShowBadges sb = new ShowBadges(player);
        sb.setVisible(true);
        sb.pack();
        sb.setLocationRelativeTo(null);
        dispose();
    }

    private String jLabel3Text(String currentCity) {
        String textLabel3;
    
        switch (currentCity) {
            case "Pallet Town":
                textLabel3 = "Talk to Mom";
                break;
            case "Lavender Town":
                textLabel3 = "Pokemaze";
                break;
            default:
                textLabel3 = "Challenge Gym Leader";
                break;
        }
        return textLabel3;
    }

    
    private void updateGymLeaderOptions(String currentCity) throws FontFormatException, IOException { //gym leader
        System.out.println("DEBUG: Current City: " + currentCity);
        if (rivalRaceButton != null) {
            jPanel1.remove(rivalRaceButton);
            rivalRaceButton = null;
        }
        if (pokemonSortButton != null) {
            jPanel1.remove(pokemonSortButton);
            pokemonSortButton = null;
        }
        jPanel1.revalidate();
        jPanel1.repaint();
        
        jComboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedGymLeader = (String) jComboBox2.getSelectedItem();
                if (selectedGymLeader != null) {
                    Player gymLeader = null;
                    switch (selectedGymLeader) {
                        case "Pewter City Leader":
                            gymLeader = gymLeaders.getPewterCityLeader();
                            break;
                        case "Cerulean Leader":
                            gymLeader = gymLeaders.getCeruleanLeader();
                            break;
                        case "Vermilion Leader":
                            gymLeader = gymLeaders.getVermilionLeader();
                            break;
                        case "Celadon City Leader":
                            gymLeader = gymLeaders.getCeladonCityLeader();
                            break;
                        case "Fuchsia City Leader":
                            gymLeader = gymLeaders.getFuchsiaCityLeader();
                            break;
                        case "Saffron City Leader":
                            gymLeader = gymLeaders.getSaffronCityLeader();
                            break;
                        case "Cinnabar Island Player":
                            gymLeader = gymLeaders.getCinnabarIslandPlayer();
                            break;
                        case "Viridian City Leader":
                            gymLeader = gymLeaders.getViridianCityLeader();
                            break;
                        default:
                            System.out.println("Unknown gym leader: " + selectedGymLeader);
                    }
                    if (gymLeader != null) {
                        // Open GymLeaderWithGUI window based on the selected gym leader
                        try {
                            openGymLeaderWindow(currentCity, player, gymLeader);
                        } catch (FontFormatException | IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });
        
    
        switch (currentCity) {
            case "Saffron City":
                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Saffron City Leader"}));
                
                rivalRaceButton = new JButton("Rival's Race");
                rivalRaceButton.setBackground(new Color(0, 0, 0));
                rivalRaceButton.setForeground(new Color(255, 255, 255));
                rivalRaceButton.setBorderPainted(false);
                rivalRaceButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        try {
                            Race race = new Race(regionExplorer, currentCity);
                            race.setVisible(true);
                            race.pack();
                            race.setLocationRelativeTo(null);
                            dispose();
                        } catch (FontFormatException e) {
                            e.printStackTrace();
                        }
                    }
                });
                // Add the button to the jPanel1
                jPanel1.add(rivalRaceButton);
                loadCustomFontSpecial(rivalRaceButton);
                rivalRaceButton.setBounds(0, 525, 300, 100);
                jPanel1.revalidate();
                jPanel1.repaint();
                break;
    
            case "Fuschia City":
    jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Fuchsia City Leader"}));
    pokemonSortButton = new JButton("Safari Zone");
    pokemonSortButton.setBackground(new Color(0, 0, 0));
    pokemonSortButton.setForeground(new Color(255, 255, 255));
    pokemonSortButton.setBorderPainted(false);
    pokemonSortButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            try {
                // Prompt user for the number of Pokemon to sort
                int numberOfPokemon = Integer.parseInt(JOptionPane.showInputDialog(null, "How many Pokemon do you want to sort?"));

                // Create and show the PokemonSort frame
                PokemonSort ps = new PokemonSort(numberOfPokemon, null);
                ps.setVisible(true);
                ps.setLocationRelativeTo(null);
                
                // Load the Pokemon with the specified number of Pokemon
                ps.loadPokemon(numberOfPokemon);
                
                // Dispose of the current frame (main menu)
                dispose();
            } catch (NumberFormatException e) {
                // Handle invalid input from JOptionPane
                JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
        }
    });
    // Add the button to the jPanel1
    jPanel1.add(pokemonSortButton);
    loadCustomFontSpecial(pokemonSortButton);
    pokemonSortButton.setBounds(0, 525, 300, 100);
    jPanel1.revalidate();
    jPanel1.repaint();
    break;


            case "Pewter City":
                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Pewter City Leader"}));
                 break;
            case "Viridian City":
                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Viridian City Leader"}));
                break;
            case "Cinnabar Island":
                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Cinnabar Island Player"}));
                break;
            case "Celadon City":
                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Celadon City Leader"}));
                break;
            case "Cerulean City":
                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Cerulean Leader"}));
                break;
            case "Vermillion City":
                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Vermilion Leader"}));
                break;
            case "Pallet Town":
                // Set the message for Pallet Town indicating no gym leader
                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Your hometown has no gym"}));
                   // Clear existing ActionListeners
                   for (ActionListener al : jComboBox2.getActionListeners()) {
                       jComboBox2.removeActionListener(al);
                   }
                   // Add ActionListener to jComboBox2 for the "Pokemaze" option
                   jComboBox2.addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           String selectedOption = (String) jComboBox2.getSelectedItem();
                           if (selectedOption != null && selectedOption.equals("Your hometown has no gym")) {
                               try {
                                   openTalkWithMomWindow();
                               } catch (FontFormatException e1) {
                                   e1.printStackTrace();
                               }
                           }
                       }
                   });
                break;
            case "Lavender Town":
                // Run Pokemaze when the option is clicked
                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Start Challenge"}));
                // Clear existing ActionListeners
                for (ActionListener al : jComboBox2.getActionListeners()) {
                    jComboBox2.removeActionListener(al);
                }
                // Add ActionListener to jComboBox2 for the "Pokemaze" option
                jComboBox2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedOption = (String) jComboBox2.getSelectedItem();
                        if (selectedOption != null && selectedOption.equals("Start Challenge")) {
                            try {
                                openPokemazeWindow();
                            } catch (FontFormatException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
                break;
            default:
                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));
                break;
        }
        
    }

    private void openGymLeaderWindow(String currentCity, Player player, Player player2) throws FontFormatException, IOException {
        // Set the player's current location
        if (!player.getPokemonTeam().isEmpty()) {
            System.out.println("DEBUG: Gym Leader's City: " + currentCity);
            GymLeaderWithGUI gym = new GymLeaderWithGUI(player, currentCity, player2);
            gym.setVisible(true);
            gym.pack();
            gym.setLocationRelativeTo(null);
            dispose();
        } else {
            // Handle the case when the player has no Pokémon in their team
            JOptionPane.showMessageDialog(this, "You don't have any Pokémon in your team!");
        }
    }
    
    

    private void openPokemazeWindow() throws FontFormatException {
        // Create an instance of the PokemazeWindow class and show it
        PokemazeWithGUI pokemazeWindow = new PokemazeWithGUI();
        pokemazeWindow.setVisible(true);
    }

    private void openTalkWithMomWindow() throws FontFormatException {
        // Create an instance of the PokemazeWindow class and show it
        TalkWithMom twm = new TalkWithMom(player);
        twm.setVisible(true);
        dispose();
    }
    
    public static String getCurrentLocation(){
        return currentLocation;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
     // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
      // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(2147483647, 2147483647));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Show Map");
        jButton2.setBorderPainted(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton2ActionPerformed(evt);
                } catch (FontFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            private void jButton2ActionPerformed(ActionEvent evt) throws FontFormatException {
                // Assuming you have already retrieved the map data and stored it in a variable named 'map'
                RegionExplorer<String, Integer> map = MapPokemon.getMapData();
            
                // Pass the map data to the ShowMap constructor
                ShowMap sm = new ShowMap(player,map);
                sm.setVisible(true);
                sm.pack();
                sm.setLocationRelativeTo(null);
                dispose();
            }
            
            

           
        });




        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Show My Pokemon");
        jButton3.setBorderPainted(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton3ActionPerformed(evt);
                } catch (FontFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Save and Exit");
        jButton4.setBorderPainted(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }

            private void jButton4ActionPerformed(ActionEvent evt) {
                dispose(); // Correct disposal of the current frame
            
                // Printing player details
                System.out.println("Player Name: " + player.getName());
                System.out.println("Player Location: " + player.getLocation());
            
                // Check if player has any badges
                if (player.getBadges() != null && !player.getBadges().isEmpty()) {
                    System.out.println("Player Badges: " + player.getBadges());
                } else {
                    System.out.println("Player has no badges.");
                }
            
                // Printing details of each Pokémon in the team
                if (player.getPokemonTeam() != null && !player.getPokemonTeam().isEmpty()) {
                    for (Pokemon pokemon : player.getPokemonTeam()) {
                        System.out.println("Pokemon Name: " + pokemon.getName());
                        System.out.println("Level: " + pokemon.getLevel());
                        System.out.println("Experience Points: " + pokemon.getExperiencePoints());
                    }
                } else {
                    System.out.println("No Pokémon in team.");
                }
            }
            
        }
    );

      
        jButton5.setBackground(new java.awt.Color(0, 0, 0));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Show My Badges");
        jButton5.setBorderPainted(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton5ActionPerformed(evt);
                } catch (FontFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            private void jButton5ActionPerformed(ActionEvent evt) throws FontFormatException {
                ShowBadges sb = new ShowBadges(player);
        sb.setVisible(true);
        sb.pack();
        sb.setLocationRelativeTo(null);
        dispose();
            }
        });
        

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
                .addGap(9, 9, 9))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("You are currently at");
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        
        jLabel2.setText(currentLocation);
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Start Battle!" }));
jComboBox3.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();
        String selectedItem = (String) comboBox.getSelectedItem();
        if (selectedItem.equals("Start Battle!")) {
            try {
                startBattle();
            } catch (FontFormatException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
    private void startBattle() throws FontFormatException, IOException {
        PokemonBattle pokemonBattle = new PokemonBattle(player, player.getLocation());
        pokemonBattle.setVisible(true);
        pokemonBattle.setLocationRelativeTo(null);
        dispose(); // Close the current window
    }
});






        jLabel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("Move To:");
        
        
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Fight Wild Pokemon");
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 984, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 49, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(169, 169, 169)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(116, 116, 116))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(277, 277, 277))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(433, 433, 433)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(224, 224, 224)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(256, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>      



    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    try {
                        new MainMenu(player).setVisible(true);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } catch (FontFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}


