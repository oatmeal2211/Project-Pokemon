import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

// Assuming RegionExplorer is a generic class with a method getNeighbours
public class MainMenu extends javax.swing.JFrame {
    RegionExplorer<String, Integer> regionExplorer = MapPokemon.getMapData();
    ArrayList<String> list = new ArrayList<>();
    static String currentLocation = "Pallet Town";

    public MainMenu() throws FontFormatException {
        initComponents();
        loadCustomFont();
        setBackgroundImage();
        MapPokemon map = new MapPokemon(); // Instantiate your MapPokemon class
    
        // Set up jComboBox2
        updateGymLeaderOptions(currentLocation);
    
        // Call setCurrentLocation with the initial current location
        setCurrentLocation(currentLocation); // Set the current location after initializing components
    }
    

    private void setBackgroundImage() {
        try {
            // Load the background image
            Image backgroundImage = ImageIO.read(new File("a56806bce812d4ec85665792d83b809b.png"));
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
        try {
            // Load the font file
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("PressStart2P-Regular.ttf"));
            // Set the font size (optional)
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

    private void setCurrentLocation(String newLocation) {
        currentLocation = newLocation;
        jLabel1.setText("You are currently at "); // Update jLabel1 with the current location
        jLabel2.setText(currentLocation); // Update jLabel2 with the current location
    
        // Update jLabel3 text based on the new currentLocation
        jLabel3.setText(jLabel3Text(currentLocation));
    
        populateAdjacentCities(currentLocation); // Run populateAdjacentCities again with the new currentLocation
    }
    
    private void populateAdjacentCities(String currentCity) {
        ArrayList<String> adjacentCities = regionExplorer.getNeighbours(currentCity);
        //System.out.println("Adjacent cities: " + adjacentCities); // Debugging line
        if (adjacentCities != null && !adjacentCities.isEmpty()) {
            jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(adjacentCities.toArray(new String[0])));
        } else {
            jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{""}));
        }
    
        // Clear any existing ActionListeners to avoid duplication
        for (ActionListener al : jComboBox1.getActionListeners()) {
            jComboBox1.removeActionListener(al);
        }
    
        // Add ActionListener to jComboBox1
        jComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCity = (String) jComboBox1.getSelectedItem();
                if (selectedCity != null) {
                    updateGymLeaderOptions(selectedCity);
                    setCurrentLocation(selectedCity); // Update currentLocation variable and run populateAdjacentCities
                }
            }
        });
    }

    private void jButton2ActionPerformed(ActionEvent evt) throws FontFormatException {
        // Assuming you have already retrieved the map data and stored it in a variable named 'map'
        RegionExplorer<String, Integer> map = MapPokemon.getMapData();

        // Pass the map data to the ShowMap constructor
        ShowMap sm = new ShowMap(map);
        sm.setVisible(true);
        sm.pack();
        sm.setLocationRelativeTo(null);
        dispose();
    }

    private void jButton3ActionPerformed(ActionEvent evt) throws FontFormatException {
        ShowPokemon sp = new ShowPokemon();
        sp.setVisible(true);
        sp.pack();
        sp.setLocationRelativeTo(null);
        dispose();
    }

    private void jButton4ActionPerformed(ActionEvent evt) {
        dispose(); // Corrected disposal of the current frame
    }

    private void jButton5ActionPerformed(ActionEvent evt) throws FontFormatException {
        ShowBadges sb = new ShowBadges();
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

    
    private void updateGymLeaderOptions(String currentCity) { //gym leader
        switch (currentCity) {
            case "Saffron City":
                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Sabrina - Psychic Type"}));
                break;
            case "Fuchsia City":
                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Koga - Poison type"}));
                break;
            case "Pewter City":
                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Brock - Rock type"}));
                break;
            case "Viridian City":
                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Giovanni"}));
                break;
            case "Cinnabar Island":
                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Blaine - Fire type"}));
                break;
            case "Celadon City":
                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Erica - Grass type"}));
                break;
            case "Cerulean City":
                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Misty - Water type"}));
                break;
            case "Vermilion City":
                jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Lt. Surge - Electric type"}));
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
    private void openPokemazeWindow() throws FontFormatException {
        // Create an instance of the PokemazeWindow class and show it
        PokemazeWithGUI pokemazeWindow = new PokemazeWithGUI();
        pokemazeWindow.setVisible(true);
    }

    private void openTalkWithMomWindow() throws FontFormatException {
        // Create an instance of the PokemazeWindow class and show it
        TalkWithMom twm = new TalkWithMom();
        twm.setVisible(true);
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
                ShowMap sm = new ShowMap(map);
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
                dispose(); // Corrected disposal of the current frame
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
                ShowBadges sb = new ShowBadges();
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

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                    new MainMenu().setVisible(true);
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


