import java.io.File;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

public class Login extends javax.swing.JFrame {

    public Login() throws FontFormatException {
        initComponents();
        loadCustomFont();
    }

    private void loadCustomFont() throws FontFormatException {
        try {
            // Load the font file
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("PressStart2P-Regular.ttf"));
            // Set the font size (optional)
            Font font = customFont.deriveFont(Font.PLAIN, 11);
            Font title = customFont.deriveFont(Font.PLAIN, 40);
            // Set the font for the labels and buttons
            jLabel1.setFont(title);
            jLabel2.setFont(font);
            jLabel3.setFont(font);
            jLabel8.setFont(font);
            jLabel4.setFont(font);
            jTextField1.setFont(font);
            jPasswordField1.setFont(font);
            jButton1.setFont(font);
            jButton2.setFont(font);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Left = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 400));
        jPanel1.setLayout(null);

        Left.setBackground(new java.awt.Color(255, 255, 255));
        Left.setMinimumSize(new java.awt.Dimension(400, 500));
        Left.setLayout(null); // Use null layout for manual positioning

        jLabel1.setFont(new java.awt.Font("Press Start 2P", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(28, 103, 88));
        jLabel1.setText("LOGIN");
        jLabel1.setBounds(100, 30, 200, 40);
        Left.add(jLabel1);

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Press Start 2P", 0, 14)); // NOI18N
        jLabel2.setText("Email");
        jLabel2.setBounds(30, 90, 100, 20);
        Left.add(jLabel2);

        jTextField1.setFont(new java.awt.Font("Press Start 2P", 0, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(28, 103, 88));
        jTextField1.setBounds(30, 110, 240, 30);
        Left.add(jTextField1);

        jLabel3.setBackground(new java.awt.Color(102, 102, 102));
        jLabel3.setFont(new java.awt.Font("Press Start 2P", 0, 14)); // NOI18N
        jLabel3.setText("Password");
        jLabel3.setBounds(30, 160, 100, 20);
        Left.add(jLabel3);

        jPasswordField1.setFont(new java.awt.Font("Press Start 2P", 0, 14)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(28, 103, 88));
        jPasswordField1.setBounds(30, 180, 240, 30);
        Left.add(jPasswordField1);

        jButton1.setBackground(new java.awt.Color(28, 103, 88));
        jButton1.setFont(new java.awt.Font("Press Start 2P", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Login");
        jButton1.setBounds(30, 230, 93, 36);
        Left.add(jButton1);

      // Adjusting jLabel4 position and size
jLabel4.setText("I don't have an account");
jLabel4.setBounds(30, 300, 300, 20); // Increase width to 250 pixels to display full text
Left.add(jLabel4);

// Adjusting jButton2 position and size
jButton2.setFont(new java.awt.Font("Press Start 2P", 0, 14)); // NOI18N
jButton2.setForeground(new java.awt.Color(255, 51, 51));
jButton2.setText("Sign Up");
jButton2.setBounds(100, 330, 150, 20); // Increase width to 100 pixels to fit the text
jButton2.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        try {
            jButton2ActionPerformed(evt);
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }
});
Left.add(jButton2);


        Left.setBounds(400, 0, 400, 500); // Adjusted panel size
        jPanel1.add(Left);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("loginpage image.jpg"))); // NOI18N
        jLabel8.setText("jLabel8");
        jLabel8.setBounds(0,0, 400, 600); // Adjusted image size
        jPanel1.add(jLabel8);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws FontFormatException {//GEN-FIRST:event_jButton2ActionPerformed

        SignUp SignUpFrame = new SignUp();
        SignUpFrame.setVisible(true);
        SignUpFrame.pack();
        SignUpFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    //private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Handle sign up button click event
        //String email = jTextField1.getText();
        //String password = new String(jPasswordField1.getPassword());

    //GEN-LAST:event_jButton1ActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Left;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}