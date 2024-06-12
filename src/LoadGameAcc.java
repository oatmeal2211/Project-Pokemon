import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class LoadGameAcc extends javax.swing.JFrame {

    public LoadGameAcc() {
        initComponents();
        try {
            loadCustomFont();
            setBackgroundImage();
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomFont() throws FontFormatException, IOException {
        try (InputStream is = getClass().getResourceAsStream("/PressStart2P-Regular.ttf")) {
            if (is == null) {
                throw new FileNotFoundException("Font file not found in resources");
            }
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            Font font = customFont.deriveFont(Font.PLAIN, 11);
            Font title = customFont.deriveFont(Font.PLAIN, 40);
            jLabel10.setFont(font);
            jLabel12.setFont(font);
            jLabel11.setFont(font);
            jButton1.setFont(font);
            jButton2.setFont(font);
            jButton3.setFont(font);
            jButton4.setFont(font);
        }
    }

    private void setBackgroundImage() throws IOException {
        try (InputStream is = getClass().getResourceAsStream("498-4988462_pokemon-sprite-trainer-png-transparent-pokemon-trainer-sprite.png resize1.png")) {
            if (is == null) {
                throw new FileNotFoundException("Image file not found in resources");
            }
            byte[] imageBytes = is.readAllBytes();
            ImageIcon imageIcon = new ImageIcon(imageBytes);
            jLabel10.setIcon(imageIcon);
            jLabel11.setIcon(imageIcon);
            jLabel12.setIcon(imageIcon);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
    jButton3 = new javax.swing.JButton();
    jButton4 = new javax.swing.JButton();
    jLabel10 = new javax.swing.JLabel();
    jLabel11 = new javax.swing.JLabel();
    jLabel12 = new javax.swing.JLabel();
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
        });
    
        
        GameProcess gameProcess1 = loadProgress(Login.EMAIL, 1);
        if(gameProcess1 != null){
            final GameProcess gameProcess11 = gameProcess1;
            jButton2.setText(gameProcess1.getPlayerName());
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                
                        jButton2ExistActionPerformed(evt,gameProcess11);
                    } catch (FontFormatException | IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }); 
            //
        }else{
            jButton2.setText("Player Name");     
            /*jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                        jButton2ActionPerformed(evt);
                    } catch (FontFormatException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }); */
        }
        
        
        GameProcess gameProcess2 = loadProgress(Login.EMAIL, 2);
        if(gameProcess2 != null){
            final GameProcess gameProcess22 = gameProcess2;
            jButton3.setText(gameProcess2.getPlayerName());
            jButton3.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                
                        jButton3ExistActionPerformed(evt,gameProcess22);
                    } catch (FontFormatException | IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }); 
            //
        }else{
            jButton3.setText("Player Name");     
        }
        

        
        GameProcess gameProcess3 = loadProgress(Login.EMAIL, 3);
        if(gameProcess3 != null){
            final GameProcess gameProcess33 = gameProcess3;
            jButton4.setText(gameProcess3.getPlayerName());
            jButton4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    try {
                
                        jButton4ExistActionPerformed(evt,gameProcess33);
                    } catch (FontFormatException | IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }); 
            //
        }else{
            jButton4.setText("Player Name");     
        }
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(83, 83, 83)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(71, 71, 71)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(337, 337, 337))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(322, 322, 322)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(171, 171, 171)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(167, 167, 167)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(82, 82, 82)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(70, 70, 70)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(232, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws FontFormatException {//GEN-FIRST:event_jButton1ActionPerformed
        WelcomePage WFrame = new WelcomePage();
        WFrame.setVisible(true);
        WFrame.pack();
        WFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws FontFormatException {//GEN-FIRST:event_jButton1ActionPerformed
        FirstDialog fd = new FirstDialog();
        fd.setVisible(true);
        fd.pack();
        fd.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ExistActionPerformed(java.awt.event.ActionEvent evt,GameProcess gameProcess) throws FontFormatException, IOException {

        Player player = new Player(gameProcess.getPlayerName(), gameProcess.getLocation(), MapPokemon.getMapData(), Move.loadMovesFromCSV("src\\Move.csv"),
        gameProcess.getSaveSlot(),gameProcess.getId());
        ArrayList<PokemonTeam> pokemonTeams = loadPokemonTeam(gameProcess.getId());

        ArrayList<Pokemon> pokemonList = new ArrayList<>();
        for (PokemonTeam pokemonTeam : pokemonTeams) {
            if(pokemonTeam.getName().equals("Bulbasaur")){
                pokemonList.add(new Pokemon("Bulbasaur",pokemonTeam.getExperiencePoints(), "Grass", "Poison", 45, 49, 49, 65, 65, 45, 5));
            }else if(pokemonTeam.getName().equals("Charmander")){
                pokemonList.add(new Pokemon("Charmander",pokemonTeam.getExperiencePoints(), "Fire", "None", 39, 52, 43, 50, 50, 65, 5));
            }else if(pokemonTeam.getName().equals("Charmander")){
                pokemonList.add(new Pokemon("Squirtle",pokemonTeam.getExperiencePoints(), "Water", "None", 44, 48, 65, 50, 50, 43,5));
            }else{
                pokemonList.add(new Pokemon(pokemonTeam.getName(),pokemonTeam.getExperiencePoints(),"Ground","None",50,50,95,40,40,35,50));
            }
        }
        player.setPokemonTeam(pokemonList);
        if(gameProcess.getBadges() != null && !gameProcess.getBadges().isEmpty()){
           String[] badgeArray = gameProcess.getBadges().split(",");
            ArrayList<String> badges = new ArrayList<>(Arrays.asList(badgeArray));
            ArrayList<badge> badgesList = new ArrayList();
            for(String badge : badges){
                badgesList.add(new badge(badge));
            }
            player.setBadges(badgesList);
        }

        // Create and add Bulbasaur to the player's team
        // Pokemon bulbasaur = new Pokemon("Bulbasaur", "Grass", "Poison", 45, 49, 49, 65, 65, 45, 5);
        // player.addPokemon(bulbasaur);
    
        // Proceed to the MainMenu only if the player has at least one Pokémon in their team
        if (pokemonTeams != null && !pokemonTeams.isEmpty()) {
            JOptionPane.showMessageDialog(this, "You chose Bulbasaur, an amazing choice. Best of luck!");
            MainMenu mm = new MainMenu(player);
            mm.setVisible(true);
            mm.pack();
            mm.setLocationRelativeTo(null);
            this.dispose();
        } else {
            // Display an error message if the player has no Pokémon in their team
            JOptionPane.showMessageDialog(this, "Error: You must choose a Pokémon to start your journey!");
        }
    }

    private void jButton3ExistActionPerformed(java.awt.event.ActionEvent evt,GameProcess gameProcess) throws FontFormatException, IOException {

        Player player = new Player(gameProcess.getPlayerName(), gameProcess.getLocation(), MapPokemon.getMapData(), Move.loadMovesFromCSV("src\\Move.csv"),
        gameProcess.getSaveSlot(),gameProcess.getId());
        ArrayList<PokemonTeam> pokemonTeams = loadPokemonTeam(gameProcess.getId());

        ArrayList<Pokemon> pokemonList = new ArrayList<>();
        for (PokemonTeam pokemonTeam : pokemonTeams) {
            if(pokemonTeam.getName().equals("Bulbasaur")){
                pokemonList.add(new Pokemon("Bulbasaur",pokemonTeam.getExperiencePoints(), "Grass", "Poison", 45, 49, 49, 65, 65, 45, 5));
            }else if(pokemonTeam.getName().equals("Charmander")){
                pokemonList.add(new Pokemon("Charmander",pokemonTeam.getExperiencePoints(), "Fire", "None", 39, 52, 43, 50, 50, 65, 5));
            }else if(pokemonTeam.getName().equals("Charmander")){
                pokemonList.add(new Pokemon("Squirtle",pokemonTeam.getExperiencePoints(), "Water", "None", 44, 48, 65, 50, 50, 43,5));
            }else{
                pokemonList.add(new Pokemon(pokemonTeam.getName(),pokemonTeam.getExperiencePoints(),"Ground","None",50,50,95,40,40,35,50));
            }
        }
        player.setPokemonTeam(pokemonList);
        if(gameProcess.getBadges() != null && !gameProcess.getBadges().isEmpty()){
           String[] badgeArray = gameProcess.getBadges().split(",");
            ArrayList<String> badges = new ArrayList<>(Arrays.asList(badgeArray));
            ArrayList<badge> badgesList = new ArrayList();
            for(String badge : badges){
                badgesList.add(new badge(badge));
            }
            player.setBadges(badgesList);
        }

        // Create and add Bulbasaur to the player's team
        // Pokemon bulbasaur = new Pokemon("Bulbasaur", "Grass", "Poison", 45, 49, 49, 65, 65, 45, 5);
        // player.addPokemon(bulbasaur);
    
        // Proceed to the MainMenu only if the player has at least one Pokémon in their team
        if (pokemonTeams != null && !pokemonTeams.isEmpty()) {
            JOptionPane.showMessageDialog(this, "You chose Bulbasaur, an amazing choice. Best of luck!");
            MainMenu mm = new MainMenu(player);
            mm.setVisible(true);
            mm.pack();
            mm.setLocationRelativeTo(null);
            this.dispose();
        } else {
            // Display an error message if the player has no Pokémon in their team
            JOptionPane.showMessageDialog(this, "Error: You must choose a Pokémon to start your journey!");
        }
    }

    private void jButton4ExistActionPerformed(java.awt.event.ActionEvent evt,GameProcess gameProcess) throws FontFormatException, IOException {

        Player player = new Player(gameProcess.getPlayerName(), gameProcess.getLocation(), MapPokemon.getMapData(), Move.loadMovesFromCSV("src\\Move.csv"),
        gameProcess.getSaveSlot(),gameProcess.getId());
        ArrayList<PokemonTeam> pokemonTeams = loadPokemonTeam(gameProcess.getId());

        ArrayList<Pokemon> pokemonList = new ArrayList<>();
        for (PokemonTeam pokemonTeam : pokemonTeams) {
            if(pokemonTeam.getName().equals("Bulbasaur")){
                pokemonList.add(new Pokemon("Bulbasaur",pokemonTeam.getExperiencePoints(), "Grass", "Poison", 45, 49, 49, 65, 65, 45, 5));
            }else if(pokemonTeam.getName().equals("Charmander")){
                pokemonList.add(new Pokemon("Charmander",pokemonTeam.getExperiencePoints(), "Fire", "None", 39, 52, 43, 50, 50, 65, 5));
            }else if(pokemonTeam.getName().equals("Charmander")){
                pokemonList.add(new Pokemon("Squirtle",pokemonTeam.getExperiencePoints(), "Water", "None", 44, 48, 65, 50, 50, 43,5));
            }else{
                pokemonList.add(new Pokemon(pokemonTeam.getName(),pokemonTeam.getExperiencePoints(),"Ground","None",50,50,95,40,40,35,50));
            }
        }
        player.setPokemonTeam(pokemonList);
        if(gameProcess.getBadges() != null && !gameProcess.getBadges().isEmpty()){
           String[] badgeArray = gameProcess.getBadges().split(",");
            ArrayList<String> badges = new ArrayList<>(Arrays.asList(badgeArray));
            ArrayList<badge> badgesList = new ArrayList();
            for(String badge : badges){
                badgesList.add(new badge(badge));
            }
            player.setBadges(badgesList);
        }

        // Create and add Bulbasaur to the player's team
        // Pokemon bulbasaur = new Pokemon("Bulbasaur", "Grass", "Poison", 45, 49, 49, 65, 65, 45, 5);
        // player.addPokemon(bulbasaur);
    
        // Proceed to the MainMenu only if the player has at least one Pokémon in their team
        if (pokemonTeams != null && !pokemonTeams.isEmpty()) {
            JOptionPane.showMessageDialog(this, "You chose Bulbasaur, an amazing choice. Best of luck!");
            MainMenu mm = new MainMenu(player);
            mm.setVisible(true);
            mm.pack();
            mm.setLocationRelativeTo(null);
            this.dispose();
        } else {
            // Display an error message if the player has no Pokémon in their team
            JOptionPane.showMessageDialog(this, "Error: You must choose a Pokémon to start your journey!");
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            
                new LoadGameAcc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    // End of variables declaration//GEN-END:variables

    
    // Load the player's progress using file I/O
    public static ArrayList<GameProcess> loadProgress(String email) { //接数据库
        // Implement file I/O to load player data
        System.out.println("Progress loaded.");
          String query = "SELECT * FROM game_progress WHERE email = ? order by save_slot asc";
          DatabaseManager dbManager = new DatabaseManager();
          GameProcess gameProcess;
          ArrayList<GameProcess> list = new ArrayList<>();
        try (Connection conn = dbManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                gameProcess = new GameProcess();
                gameProcess.setId(rs.getString("id"));
                gameProcess.setEmail(rs.getString("email"));
                gameProcess.setSaveSlot(rs.getInt("save_slot"));
                gameProcess.setPlayerName(rs.getString("player_name"));
                gameProcess.setLocation(rs.getString("location"));
                gameProcess.setBadges(rs.getString("badges"));
                list.add(gameProcess);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static GameProcess loadProgress(String email,Integer saveSlot) { //接数据库
        // Implement file I/O to load player data
        System.out.println("Progress loaded.");
          String query = "SELECT * FROM game_progress WHERE email = ? and save_slot = ? limit 1";
          DatabaseManager dbManager = new DatabaseManager();
          GameProcess gameProcess = null;
        
        try (Connection conn = dbManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setInt(2, saveSlot);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                gameProcess = new GameProcess();
                gameProcess.setId(rs.getString("id"));
                gameProcess.setEmail(rs.getString("email"));
                gameProcess.setSaveSlot(rs.getInt("save_slot"));
                gameProcess.setPlayerName(rs.getString("player_name"));
                gameProcess.setLocation(rs.getString("location"));
                gameProcess.setBadges(rs.getString("badges"));
            
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameProcess;
    }
    public GameProcess loadProgressSimple(String id) { //接数据库
        // Implement file I/O to load player data
        System.out.println("Progress loaded.");
          String query = "SELECT * FROM game_progress WHERE id = ? ";
          DatabaseManager dbManager = new DatabaseManager();
          GameProcess gameProcess = null;

        try (Connection conn = dbManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                gameProcess = new GameProcess();
                gameProcess.setId(rs.getString("id"));
                gameProcess.setEmail(rs.getString("email"));
                gameProcess.setSaveSlot(rs.getInt("save_slot"));
                gameProcess.setPlayerName(rs.getString("player_name"));
                gameProcess.setLocation(rs.getString("location"));
                gameProcess.setBadges(rs.getString("badges"));
            
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameProcess;
    }

    public ArrayList<PokemonTeam> loadPokemonTeam(String gameProgressId) { //接数据库
        // Implement file I/O to load player data
        System.out.println("Progress loaded.");
          String query = "SELECT * FROM pokemon_team WHERE game_progress_id = ? ";
          DatabaseManager dbManager = new DatabaseManager();
          PokemonTeam pokemonTeam;
          ArrayList<PokemonTeam> list = new ArrayList<>();
        try (Connection conn = dbManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, gameProgressId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pokemonTeam = new PokemonTeam();
                pokemonTeam.setId(rs.getInt ("id"));
                pokemonTeam.setGameProgressId(rs.getString("game_progress_id"));
                pokemonTeam.setName(rs.getString("name"));
                pokemonTeam.setLevel(rs.getInt("level"));
                pokemonTeam.setExperiencePoints(rs.getInt("experience_points"));
                list.add(pokemonTeam);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
