import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class ChoosePokemon extends JFrame {

    public ChoosePokemon() throws FontFormatException {
        initComponents();
        loadCustomFont();
        setBackgroundImage();
        scaleAndSetImage("charmander.jpg", jLabel2);
        scaleAndSetImage("squirtle.jpg", jLabel3);
        scaleAndSetImage("bulbasaur.jpg", jLabel1);
    }

    private void loadCustomFont() throws FontFormatException {
        try {
            // Load the font file
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("PressStart2P-Regular.ttf"));
            // Set the font size (optional)
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

            // Add components from ChoosePokemon class
            jLabel1.setBounds(500, 60, 131, 121);
            jButton1.setBounds(750, 100, 356, 71);
            jLabel2.setBounds(500, 210, 131, 121);
            jButton2.setBounds(750, 240, 356, 71);
            jLabel3.setBounds(500, 360, 131, 121);
            jButton3.setBounds(750, 380, 356, 71);

            jScrollPane1.setBounds(100, 550, 1200, 200);
            jLabel4.setBounds(100, 500, 500, 30);

            // Add components to the layered pane
            layeredPane.add(jLabel1, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jButton1, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jLabel2, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jButton2, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jLabel3, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jButton3, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jScrollPane1, JLayeredPane.PALETTE_LAYER);
            layeredPane.add(jLabel4, JLayeredPane.PALETTE_LAYER);

            // Set layered pane as the content pane
            setContentPane(layeredPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jLabel1 = new JLabel(); // Add jLabel1
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
        jTextArea1.setText("Right! So your name is Amaan!\n\nWelcome to the world of Pokemon.\n\n" + //
                        "It's time to choose your starting pokemon.");
        jScrollPane1.setViewportView(jTextArea1);

        jLabel4.setText("Oak"); // Set text for jLabel4
        jLabel4.setForeground(Color.WHITE); // Set color for jLabel4

        jButton1.setText("Bulbasaur [Grass - Level 5]");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Charmander [Fire - Level 5]");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Squirtle [Water - Level 5]");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void scaleAndSetImage(String imagePath, JLabel label) {
        try {
            Image img = ImageIO.read(new File(imagePath));
            ImageIcon scaledIcon = new ImageIcon(img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
            label.setIcon(scaledIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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
            java.util.logging.Logger.getLogger(ChoosePokemon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChoosePokemon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChoosePokemon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChoosePokemon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ChoosePokemon().setVisible(true);
                } catch (FontFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    // End of variables declaration
}
