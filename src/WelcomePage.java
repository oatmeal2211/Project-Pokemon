import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class WelcomePage extends javax.swing.JFrame{

    public static Integer slot = null;

    public WelcomePage() throws FontFormatException{
        initComponents();
        loadCustomFont();
    }

    private void loadCustomFont() throws FontFormatException {
        try (InputStream is = getClass().getResourceAsStream("/PressStart2P-Regular.ttf")) {
            if (is == null) {
                throw new FileNotFoundException("Font file not found in resources");
            }
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            Font font = customFont.deriveFont(Font.PLAIN, 12);
            Font title = customFont.deriveFont(Font.PLAIN, 30);
            // Set the font for the labels and buttons
            jLabel1.setFont(title);
            jLabel2.setFont(title);     
            jButton1.setFont(font);
            jButton2.setFont(font);
            jButton3.setFont(font);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(2147483647, 2147483647));
        setResizable(false);

        jLabel1.setIcon(new javax.swing.ImageIcon("src\\pokemon-logo-image-minecraft-pixel-art-pokemon-logo-pac-man-text-fire-truck-vehicle-transparent-png-2526549-removebg-preview.png")); // NOI18N


        jLabel2.setText("Welcome to Pokemon - Kanto Adventures");
        jLabel2.setForeground(new java.awt.Color(28, 103, 88));

        jButton1.setText("Exit");
        jButton1.setForeground(new java.awt.Color(28, 103, 88));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Load Game");
        jButton2.setForeground(new java.awt.Color(28, 103, 88));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButton2ActionPerformed(evt);
                } catch (FontFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        jButton3.setText("Start New Adventure");
        jButton3.setForeground(new java.awt.Color(28, 103, 88));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(383, 383, 383)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(196, 196, 196)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(448, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(211, 211, 211))
        );

        pack();
    }// </editor-fold>                          
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws FontFormatException {     
                                    
        LoadGameAcc WFrame = new LoadGameAcc();
        WFrame.setVisible(true);
        WFrame.pack();
        WFrame.setLocationRelativeTo(null);
        this.dispose();
    }                                        

    private boolean areSaveSlotsFull() {
    ArrayList<GameProcess> gameProcesses = LoadGameAcc.loadProgress(Login.EMAIL);
    return gameProcesses != null && gameProcesses.size() >= 3;
}

// This method shows the dialog to the user when save slots are full
private void showSaveSlotFullDialog() throws FontFormatException {
    // Define the options for the user
    Object[] options = {"Override Slot 1", "Override Slot 2", "Override Slot 3", "Cancel"};
    
    // Show the JOptionPane dialog with the options
    int selectedOption = JOptionPane.showOptionDialog(
        this,
        "The save slots are full. Do you want to override your current slot?",
        "Save Slot Full",
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[3]
    );

    // Handle the user's choice
    if (selectedOption != 3 && selectedOption != JOptionPane.CLOSED_OPTION) {
        slot = selectedOption + 1;
        // If user selected to override any slot (not "Cancel" or closed the dialog)
        deleteGameProcess(Login.EMAIL,slot);
        deletePokemonTeams(Login.EMAIL,slot);

        FirstDialog NFrame = new FirstDialog();
        NFrame.setVisible(true);
        NFrame.pack();
        NFrame.setLocationRelativeTo(null);
        this.dispose();
    }
}

public boolean deleteGameProcess(String email,Integer slot) {
    DatabaseManager dbManager = new DatabaseManager();
    String sql = "DELETE FROM game_progress WHERE email = ? and save_slot = ?";
    try (Connection conn = dbManager.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, email);
        pstmt.setInt(2, slot);
        return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

public boolean deletePokemonTeams(String email,Integer slot) {
    DatabaseManager dbManager = new DatabaseManager();
    String sql = "DELETE FROM pokemon_team WHERE game_progress_id in (SELECT id FROM game_progress WHERE email = ? and save_slot = ?)";
    try (Connection conn = dbManager.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, email);
        pstmt.setInt(2, slot);
        return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


// This method is called when the user clicks the "Start New Adventure" button
private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) throws FontFormatException {                                         
    // Check if the save slots are full
    if (areSaveSlotsFull()) {
        // Show the dialog if slots are full
        showSaveSlotFullDialog();
    } else {
        // Proceed normally if slots are not full
        FirstDialog NFrame = new FirstDialog();
        NFrame.setVisible(true);
        NFrame.pack();
        NFrame.setLocationRelativeTo(null);
        this.dispose();
    }
}

                                          

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
       dispose();
    }                                        

    

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
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new WelcomePage().setVisible(true);
                } catch (FontFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration                   
}



