import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import java.awt.event.*;

public class TalkWithMom extends JFrame {

    private Timer timer;
    private int index = 0;
    private static String text;
    private JTextArea jTextArea1;
    private JLabel jLabel2;
    private JButton jButton1;

    public TalkWithMom() throws FontFormatException {
        initComponents();
        loadCustomFont();
        setBackgroundImage();
        startTypewriterEffect();
    }

    private void loadCustomFont() throws FontFormatException {
        try {
            // Load the font file
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("PressStart2P-Regular.ttf"));
            // Set the font size (optional)
            Font font = customFont.deriveFont(Font.PLAIN, 11);
            Font title = customFont.deriveFont(Font.PLAIN, 30);
            Font heading = customFont.deriveFont(Font.PLAIN, 15);
            jTextArea1.setFont(heading);
            jLabel2.setFont(title);
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

            // Add components to the layered pane
            jLabel2.setBounds(100, 500, 500, 30);
            jLabel2.setForeground(Color.WHITE);
            layeredPane.add(jLabel2, JLayeredPane.PALETTE_LAYER);

            jTextArea1.setBounds(100, 550, 1200, 200);
            jTextArea1.setLineWrap(true); // Enable text wrapping
        jTextArea1.setWrapStyleWord(true);
            layeredPane.add(jTextArea1, JLayeredPane.PALETTE_LAYER);

            jButton1.setBounds(1350, 650, 100, 30);
            layeredPane.add(jButton1, JLayeredPane.PALETTE_LAYER);

            // Set layered pane as the content pane
            setContentPane(layeredPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    


    private void initComponents() {
        jTextArea1 = new JTextArea();
        jLabel2 = new JLabel();
        jButton1 = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Set to full screen

        jTextArea1.setColumns(50);
        jTextArea1.setRows(10);

        jLabel2.setText("Mom");

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
        text = "Oh, " + FirstDialog.getPlayerName() + "! You're leaving on your adventure with Pokémon? How exciting! I know you've always dreamed of this day. \n\nRemember, the bond you share with your Pokémon is the most important thing. Take care of them, and they'll take care of you.Don't worry about me; I'll be just fine here. \n\nI can't wait to hear all about your adventures and the newfriends you're going to make.Remember, no matter how far you go, I'm always here for you. \n\nBe brave, be kind, and everything will turn out just fine.I'm so proud of you already! Now, go on, your adventure awaits!\n\nOh, and don’t forget to change your underwear every day! Safe travels, my dear!";
        timer = new Timer(1000, new ActionListener() {
            boolean firstTime = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (firstTime) {
                    firstTime = false;
                    timer.setDelay(30);
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
        MainMenu mm = new MainMenu();
        mm.setVisible(true);
        mm.pack();
        mm.setLocationRelativeTo(null);
        this.dispose();
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
                    new TalkWithMom().setVisible(true);
                } catch (FontFormatException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
