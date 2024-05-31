import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ShowBadges extends javax.swing.JFrame {
    static Player player;

    public ShowBadges(Player player) throws FontFormatException {
        this.player = player;
        initComponents();
        setBackgroundImage();
        loadCustomFont();
        displayBadges(); // Display the badges on the panel
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
            jButton1.setFont(font);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomFontSpecial(JLabel label) throws FontFormatException {
        try (InputStream is = getClass().getResourceAsStream("/PressStart2P-Regular.ttf")) {
            if (is == null) {
                throw new FileNotFoundException("Font file not found in resources");
            }
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            Font font = customFont.deriveFont(Font.PLAIN, 11);
            Font title = customFont.deriveFont(Font.PLAIN, 37);
            // Set the font for the labels and buttons
            label.setFont(font);
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

    private void displayBadges() {
        List<badge> badges = player.getBadges();
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        for (badge badge : badges) {
            JLabel badgeLabel = new JLabel(badge.getName());
            badgeLabel.setFont(new Font("Press Start 2P", Font.PLAIN, 18));
            badgeLabel.setForeground(Color.WHITE); // Set text color to white for visibility
            jPanel1.add(badgeLabel);

            try {
                loadCustomFontSpecial(badgeLabel);
            } catch (FontFormatException e) {
                e.printStackTrace();
            }
        }
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    private void initComponents() {
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(2147483647, 2147483647));

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

            private void jButton1ActionPerformed(ActionEvent evt) throws FontFormatException {
                MainMenu mm = new MainMenu(player);
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
                        .addGap(0, 742, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 427, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(527, Short.MAX_VALUE))
        );

        pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ShowBadges(player).setVisible(true);
                } catch (FontFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
}
