import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    public static String EMAIL = "";

    public Login() throws FontFormatException {
        initComponents();
        loadCustomFont();
    }

    private void loadCustomFont() throws FontFormatException {
        try (InputStream is = getClass().getResourceAsStream("src\\PressStart2P-Regular.ttf")) {
            if (is == null) {
                throw new FileNotFoundException("Font file not found in resources");
            }
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            Font font = customFont.deriveFont(Font.PLAIN, 11);
            Font title = customFont.deriveFont(Font.PLAIN, 40);
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

    
    private void loadImage() {
        try {
            InputStream is = getClass().getResourceAsStream("/loginpage image resize3.jpg");
            if (is == null) {
                throw new FileNotFoundException("Image file not found in resources");
            }
            Image image = ImageIO.read(is);
            if (image != null) {
                ImageIcon imageIcon = new ImageIcon(image);
                jLabel8.setIcon(imageIcon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        jLabel8 = new javax.swing.JLabel();
        Left = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(2147483647, 2147483647));


        loadImage();

        Left.setBackground(new java.awt.Color(255, 255, 255));
        Left.setMinimumSize(new java.awt.Dimension(400, 500));

        jLabel1.setForeground(new java.awt.Color(28, 103, 88));
        jLabel1.setText("LOGIN");

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Email");

        jTextField1.setForeground(new java.awt.Color(102, 102, 102));

        jLabel3.setBackground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Password");

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Login");
        jButton1.addActionListener(evt -> {
            try {
                jButton1ActionPerformed(evt);
            } catch (FontFormatException e) {
                e.printStackTrace();
            }
        });

        jLabel4.setText("I don't have an account");

        jButton2.setForeground(new java.awt.Color(255, 51, 51));
        jButton2.setText("Sign Up");
        jButton2.addActionListener(evt -> {
            try {
                jButton2ActionPerformed(evt);
            } catch (FontFormatException e) {
                e.printStackTrace();
            }
        });

        javax.swing.GroupLayout LeftLayout = new javax.swing.GroupLayout(Left);
        Left.setLayout(LeftLayout);
        LeftLayout.setHorizontalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(LeftLayout.createSequentialGroup()
                    .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(LeftLayout.createSequentialGroup()
                            .addGap(138, 138, 138)
                            .addComponent(jLabel1))
                        .addGroup(LeftLayout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                                .addComponent(jTextField1)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(LeftLayout.createSequentialGroup()
                            .addGap(77, 77, 77)
                            .addComponent(jButton2)))
                    .addContainerGap(406, Short.MAX_VALUE))
        );
        LeftLayout.setVerticalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(LeftLayout.createSequentialGroup()
                    .addGap(51, 51, 51)
                    .addComponent(jLabel1)
                    .addGap(40, 40, 40)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(29, 29, 29)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton2)
                    .addContainerGap(3332, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(Left, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 2588, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Left, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws FontFormatException {
        SignUp SignUpFrame = new SignUp();
        SignUpFrame.setVisible(true);
        SignUpFrame.pack();
        SignUpFrame.setLocationRelativeTo(null);
        this.dispose();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws FontFormatException {//GEN-FIRST:event_jButton1ActionPerformed
        // 获取用户输入的邮箱和密码
        String email = jTextField1.getText();
        String password = new String(jPasswordField1.getPassword());
    
        DatabaseManager dbManager = new DatabaseManager();
        if (dbManager.validateUser(email, password)) {
            EMAIL = email;
            JOptionPane.showMessageDialog(this, "Login successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
            WelcomePage WelcomeFrame = new WelcomePage();
            WelcomeFrame.setVisible(true);
            WelcomeFrame.pack();
            WelcomeFrame.setLocationRelativeTo(null);
            this.dispose(); // 登录成功，关闭登录窗口，打开主页面
        } else {
            JOptionPane.showMessageDialog(this, "Invalid email or password.", "Error", JOptionPane.ERROR_MESSAGE);
            // 登录失败，只显示错误消息，不关闭登录窗口
        }
    }
    


    // Variables declaration - do not modify
    private javax.swing.JPanel Left;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration
}