import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Race extends javax.swing.JFrame {

    private RegionExplorer<String, Integer> map;
    private String currentLocation;
    private String destination;
    private ArrayList<String> nonAdjacentCities;
    private Image backgroundImage;

    /**
     * Creates new form Race
     * @throws FontFormatException 
     */
    public Race(RegionExplorer<String, Integer> map, String currentLocation) throws FontFormatException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(2147483647, 2147483647));
        this.map = map;
        this.currentLocation = currentLocation;
        this.nonAdjacentCities = new ArrayList<>();
        setBackgroundImage();
        initComponents();
        loadCustomFont();
        startRace();
    }

    private void setBackgroundImage() {
        try (InputStream is = getClass().getResourceAsStream("wp8797760.jpg")) {
            if (is == null) {
                throw new FileNotFoundException("Background image file not found in resources");
            }
            backgroundImage = ImageIO.read(is);
            
            // Create a new BufferedImage with the same dimensions as the background image
            BufferedImage darkerImage = new BufferedImage(backgroundImage.getWidth(null), backgroundImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = darkerImage.createGraphics();
            
            // Draw the original image onto the new BufferedImage
            g.drawImage(backgroundImage, 0, 0, null);
            
            // Create a semi-transparent black overlay
            g.setColor(new Color(0, 0, 0, 50)); 
            g.fillRect(0, 0, backgroundImage.getWidth(null), backgroundImage.getHeight(null));
            
            g.dispose();
            
            backgroundImage = darkerImage; // Assign the darker image to backgroundImage
            
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
             Font title = customFont.deriveFont(Font.PLAIN, 20);
             //Font title = customFont.deriveFont(Font.PLAIN, 30);
             jLabel1.setFont(title);
             jLabel2.setFont(title);
             jTextArea1.setFont(title);
             jLabel4.setFont(title);
             jButton1.setFont(font);

             Color whiteColor = Color.WHITE;
         jLabel1.setForeground(whiteColor);
         jLabel2.setForeground(whiteColor);
         jLabel4.setForeground(whiteColor);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

    /**
     * This method initializes the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        JPanel jPanel1 = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("The battle has begun! ");
        jLabel2.setText("Good luck on your race!");
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton1ActionPerformed(evt);
                } catch (FontFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setLineWrap(true); // Enable line wrapping
        jTextArea1.setWrapStyleWord(true); // Wrap at word boundaries
        jScrollPane1.setViewportView(jTextArea1);

        jLabel4.setText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 61, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(333, 333, 333))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(83, 83, 83))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>               

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws FontFormatException {
        MainMenu mm = new MainMenu();
        mm.setVisible(true);
        mm.pack();
        mm.setLocationRelativeTo(null);
        dispose();
    }

    /**
     * Starts the race and updates the GUI with the race details.
     */
    private void startRace() {
        // Select a random destination that is not adjacent to the current location
        findNonAdjacentCities();
        selectRandomDestination();

        if (destination == null) {
            jLabel1.setText("Race cannot start, no valid destination.");
            return;
        }

        jLabel1.setText("The battle has begun!");
        jLabel4.setText("Your rival Gary has challenged you to a race to " + destination + ".");

        // Find the shortest path
        ArrayList<String> path = findShortestPath(currentLocation, destination);

        // Display the shortest path in jTextArea1
        StringBuilder pathString = new StringBuilder("Shortest Path:\n\n");
        for (int i = 0; i < path.size(); i++) {
            pathString.append(path.get(i));
            if (i < path.size() - 1) {
                pathString.append(" -> ");
            }
        }
        jTextArea1.setText(pathString.toString());
    }

    private void findNonAdjacentCities() {
        ArrayList<String> allCities = map.getAllVertexObjects();
        Set<String> adjacentCities = new HashSet<>(map.getNeighbours(currentLocation));
        for (String city : allCities) {
            if (!city.equals(currentLocation) && !adjacentCities.contains(city)) {
                nonAdjacentCities.add(city);
            }
        }
    }

    private void selectRandomDestination() {
        if (!nonAdjacentCities.isEmpty()) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(nonAdjacentCities.size());
            this.destination = nonAdjacentCities.get(randomIndex);
        } else {
            System.out.println("No non-adjacent cities available.");
        }
    }

    private ArrayList<String> findShortestPath(String start, String end) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v.distance));
        Map<String, Vertex> vertices = new HashMap<>();

        for (String city : map.getAllVertexObjects()) {
            vertices.put(city, new Vertex(city, Integer.MAX_VALUE));
        }

        vertices.get(start).distance = 0;
        pq.add(vertices.get(start));

        while (!pq.isEmpty()) {
            Vertex current = pq.poll();
            if (current.name.equals(end)) {
                break;
            }

            for (String neighbor : map.getNeighbours(current.name)) {
                int weight = map.getEdgeWeight(current.name, neighbor);
                Vertex neighborVertex = vertices.get(neighbor);

                int newDist = current.distance + weight;
                if (newDist < neighborVertex.distance) {
                    neighborVertex.distance = newDist;
                    neighborVertex.previous = current;
                    pq.add(neighborVertex);
                }
            }
        }

        ArrayList<String> path = new ArrayList<>();
        for (Vertex at = vertices.get(end); at != null; at = at.previous) {
            path.add(at.name);
        }
        Collections.reverse(path);
        return path;
    }

    private static class Vertex {
        String name;
        int distance;
        Vertex previous;

        Vertex(String name, int distance) {
            this.name = name;
            this.distance = distance;
            this.previous = null;
        }
    }

    public static void main(String args[]) {
        RegionExplorer<String, Integer> map = MapPokemon.getMapData();
        String currentLocation = "Saffron City";

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Race(map, currentLocation).setVisible(true);
                } catch (FontFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration
}