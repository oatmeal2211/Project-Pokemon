import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowMap extends javax.swing.JFrame {

    private RegionExplorer<String, Integer> map;

    public ShowMap(RegionExplorer<String, Integer> map) throws FontFormatException {
        this.map = map;
        initComponents();
        setBackgroundImage();
        loadCustomFont();
        
    }


    private void loadCustomFont() throws FontFormatException {
        try {
            // Load the font file
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("PressStart2P-Regular.ttf"));
            // Set the font size (optional)
            Font font = customFont.deriveFont(Font.PLAIN, 11);
            jButton1.setFont(font);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setBackgroundImage() {
        try {
            // Load the background image
            Image backgroundImage = ImageIO.read(new File("pinwheel-forest-pokemon-pixel-thumb.jpg"));
            ImageIcon imageIcon = new ImageIcon(backgroundImage);

            // Create a layered pane
            JLayeredPane layeredPane = new JLayeredPane();
            layeredPane.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));

            // Add a label to display the background image
            JLabel backgroundLabel = new JLabel(imageIcon);
            backgroundLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
            layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

            // Set the button's position
            jButton1.setBounds(38, 27, 123, 53);
            layeredPane.add(jButton1, JLayeredPane.PALETTE_LAYER);

            // Add the panel to the layered pane
            jPanel1.setBounds(161, 180, 1200, 530); // Adjust position and size of jPanel1
            layeredPane.add(jPanel1, JLayeredPane.MODAL_LAYER);

            // Set layered pane as the content pane
            setContentPane(layeredPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawMap(g);
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(2147483647, 2147483647));

        jButton1.setText("Back");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    jButton1ActionPerformed(evt);
                } catch (FontFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            private void jButton1ActionPerformed(ActionEvent evt) throws FontFormatException {
                MainMenu mm = new MainMenu();
                mm.setVisible(true);
                mm.pack();
                mm.setLocationRelativeTo(null);
                dispose();
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1200, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 600, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(456, Short.MAX_VALUE))
        );

        pack();
    }

    private void drawMap(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4)); // Thicker lines
    
        // Define city positions
        Map<String, Point> cityPositions = new HashMap<>();
        cityPositions.put("Pewter City", new Point(250, 100));
        cityPositions.put("Viridian City", new Point(250, 200));
        cityPositions.put("Pallet Town", new Point(250, 300));
        cityPositions.put("Cinnabar Island", new Point(250, 400));
        cityPositions.put("Fuschia City", new Point(500, 300));
        cityPositions.put("Celadon City", new Point(600, 200));
        cityPositions.put("Saffron City", new Point(700, 150));
        cityPositions.put("Cerulean City", new Point(850, 100));
        cityPositions.put("Lavender Town", new Point(850, 250));
        cityPositions.put("Vermillion City", new Point(700, 450));
    
        String[] cities = {"Pewter City", "Viridian City", "Pallet Town", "Cinnabar Island", "Fuschia City", "Celadon City", "Saffron City", "Cerulean City", "Lavender Town", "Vermillion City"};
    
        // Draw edges
        g.setColor(Color.BLACK);
        for (String city : cities) {
            ArrayList<String> neighbors = map.getNeighbours(city);
            Point cityPosition = cityPositions.get(city);
            for (String neighbor : neighbors) {
                Point neighborPosition = cityPositions.get(neighbor);
                g2.drawLine(cityPosition.x, cityPosition.y, neighborPosition.x, neighborPosition.y);
            }
        }
    
        // Draw cities
        for (String city : cities) {
            Point cityPosition = cityPositions.get(city);
            g.setColor(Color.RED);
            g.fillOval(cityPosition.x - 5, cityPosition.y - 5, 10, 10);
            g.setColor(Color.BLACK);
            Font font = new Font("Arial", Font.BOLD, 15); 
            g.setFont(font);
            g.drawString(city, cityPosition.x + 15, cityPosition.y + 10);
        }
    }
    

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Call the getMapData() method from the Map class to retrieve the map data
                    RegionExplorer<String, Integer> map = new MapPokemon().getMapData();
                    new ShowMap(map).setVisible(true);
                } catch (FontFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    
    

    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
}
