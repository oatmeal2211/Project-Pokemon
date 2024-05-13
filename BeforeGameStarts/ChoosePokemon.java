import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.event.*;

public class ChoosePokemon extends JFrame {
    private static String playerName;

    public ChoosePokemon(String playerName) throws FontFormatException {
        ChoosePokemon.playerName = playerName;
        initComponents();
        loadCustomFont();
        setBackgroundImage();
        scaleAndSetImage("charmander.jpg", jLabel2);
        scaleAndSetImage("squirtle.jpg", jLabel3);
        scaleAndSetImage("bulbasaur.jpg", jLabel1);
        startTypewriterEffect();
    }

    private void loadCustomFont() throws FontFormatException {
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("PressStart2P-Regular.ttf"));
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
            Image backgroundImage = ImageIO.read(new File("pinwheel-forest-pokemon-pixel-thumb.jpg"));
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
        //jTextArea1.setText("Right! So your name is " + playerName + "!\n\nWelcome to the world of Pokemon.\n\n" +
                //"It's time to choose your starting pokemon.");
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
        String text = "Right! So your name is " + playerName + "!\n\nWelcome to the world of Pokemon.\n\n" +
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
        JOptionPane.showMessageDialog(this, "You chose Bulbasaur, an amazing choice. Best of luck!");
        MainMenu mm = new MainMenu();
        mm.setVisible(true);
        mm.pack();
        mm.setLocationRelativeTo(null);
        this.dispose();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws FontFormatException {
        JOptionPane.showMessageDialog(this, "You chose Charmander, an amazing choice. Best of luck!");
        MainMenu mm = new MainMenu();
        mm.setVisible(true);
        mm.pack();
        mm.setLocationRelativeTo(null);
        this.dispose();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) throws FontFormatException {
        JOptionPane.showMessageDialog(this, "You chose Squirtle, an amazing choice. Best of luck!");
        MainMenu mm = new MainMenu();
        mm.setVisible(true);
        mm.pack();
        mm.setLocationRelativeTo(null);
        this.dispose();
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

    public static void main(String args[]) {
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

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ChoosePokemon(playerName).setVisible(true);
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
