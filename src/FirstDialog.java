import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.*;

public class FirstDialog extends JFrame {
    public static String playerName;
    private Timer timer;
    private int index = 0;
    private String text = "Hello there! Welcome to the world of Pokémon!\n\nMy name is Oak! People call me the Pokémon Prof!\n\n" + //
            "This world is inhabited by creatures called Pokémon! \n\nFor some people, Pokémon are pets. Others use them for fights. \n\nMyself... I study Pokémon as a profession.\r\n" + //
            "\n\nFirst, what is your name?";
    private JTextArea jTextArea1;
    private JLabel jLabel2;
    private JButton jButton1;
    private static Player player;

    public FirstDialog() throws FontFormatException {
        initComponents();
        loadCustomFont();
        setBackgroundImage();
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
            Font heading = customFont.deriveFont(Font.PLAIN, 15);
            jTextArea1.setFont(heading);
            jLabel2.setFont(title);
            jButton1.setFont(font);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Unable to load custom font.", "Font Load Error", JOptionPane.ERROR_MESSAGE);
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

            jLabel2.setBounds(100, 500, 500, 30);
            jLabel2.setForeground(Color.WHITE);
            layeredPane.add(jLabel2, JLayeredPane.PALETTE_LAYER);

            jTextArea1.setBounds(100, 550, 1200, 200);
            jTextArea1.setLineWrap(true); // Enable text wrapping
            jTextArea1.setWrapStyleWord(true);
            layeredPane.add(jTextArea1, JLayeredPane.PALETTE_LAYER);

            jButton1.setBounds(1350, 650, 100, 30);
            layeredPane.add(jButton1, JLayeredPane.PALETTE_LAYER);

            setContentPane(layeredPane);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Unable to load background image.", "Image Load Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() {
        jTextArea1 = new JTextArea();
        jLabel2 = new JLabel();
        jButton1 = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen

        jTextArea1.setColumns(50);
        jTextArea1.setRows(6);

        jLabel2.setText("Oak");

        jButton1.setText("Next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton1ActionPerformed(evt);
                } catch (FontFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        pack();
    }

    private void startTypewriterEffect() {
        timer = new Timer(1000, new ActionListener() {
            boolean firstTime = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (firstTime) {
                    firstTime = false;
                    timer.setDelay(50);
                }
                if (index < text.length()) {
                    jTextArea1.append(String.valueOf(text.charAt(index)));
                    index++;
                } else {
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws FontFormatException {
        playerName = JOptionPane.showInputDialog(this, "Please enter your name:");
    
        // If the user entered a name and clicked OK
        if (playerName != null && !playerName.isEmpty()) {
            player = new Player(playerName, "Pallet Town", MapPokemon.getMapData());
            // Pass the Player object to the ChoosePokemon constructor
            ChoosePokemon cp = new ChoosePokemon(player);
            cp.setVisible(true);
            cp.pack();
            cp.setLocationRelativeTo(null);
            this.dispose(); // Close the FirstDialog frame
        } else {
            // If the user clicked Cancel or did not enter a name
            JOptionPane.showMessageDialog(this, "Please enter a valid name.");
            playerName = JOptionPane.showInputDialog(this, "Please enter your name:");
        }
    }
    

    public static String getPlayerName(){
        return playerName;
    }

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
            java.util.logging.Logger.getLogger(FirstDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FirstDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FirstDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FirstDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FirstDialog().setVisible(true);
                } catch (FontFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
