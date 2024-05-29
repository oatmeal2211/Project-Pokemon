import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class PokemazeWithGUI extends JFrame implements KeyListener {
    private static final char WALL = '#';
    private static final char PATH = '.';
    private static final char START = 'S';
    private static final char END = 'E';
    private static final char GHASTLY = 'G';
    private static final char PLAYER = 'Y';

    private static final int CELL_SIZE = 60; // Increased cell size

    private char[][] maze = {
        {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
        {'#','S','.', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
        {'#', '#', '#', '#', '#', '.', '#', '.', '#', '#', '#', '#','#', '#', '#', '.', '#'},
        {'#', '.', '.', '.', '#', '.','#', '.', '#','.','.', '.','.', '.','#', '.', '#',},
        {'#','#', '#', '.', '#', '.', '#','#', '#', 'G', '#', '.', '#', '.', '#', '#', '#'},
        {'#','.','.', '.', '#', '.', '.', '.', '#', '.', '.', '.', '#', '.', '.', '.', '#'},
        {'#', '#', '#', '#', '#', 'G', '#', '.', '#', '.','#', '.','#', '#', '#', '.', '#'},
        {'#','.','.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '.', '.', '#'},
        {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','E','#'}
    };

    private int playerX, playerY;
    private JPanel mazePanel;
    private JLabel instructionLabel;
    private Image backgroundImage; // Background image variable

    public PokemazeWithGUI() throws FontFormatException {
        setTitle("Pokémon Maze Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(2147483647, 2147483647));

        // Load the background image
        try (InputStream is = getClass().getResourceAsStream("/a56806bce812d4ec85665792d83b809b.png")) {
            if (is == null) {
                throw new FileNotFoundException("Background image file not found in resources");
            }
            backgroundImage = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        instructionLabel = new JLabel("Use arrow keys to control the character");
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loadCustomFont();

        mazePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
                drawMaze(g);
            }
        };

        mazePanel.setPreferredSize(new Dimension(CELL_SIZE * maze[0].length, CELL_SIZE * maze.length)); // Adjust panel size
        mazePanel.setFocusable(true);
        mazePanel.addKeyListener(this);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(instructionLabel, BorderLayout.NORTH);
        contentPane.add(mazePanel, BorderLayout.CENTER);

        setContentPane(contentPane);
        pack();

        locatePlayer();
    }

    private void loadCustomFont() throws FontFormatException {
       try (InputStream is = getClass().getResourceAsStream("/PressStart2P-Regular.ttf")) {
            if (is == null) {
                throw new FileNotFoundException("Font file not found in resources");
            }
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is);
            Font font = customFont.deriveFont(Font.PLAIN, 11);
            Font title = customFont.deriveFont(Font.PLAIN, 30);
            instructionLabel.setFont(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawMaze(Graphics g) {
        int mazeWidth = maze[0].length * CELL_SIZE;
        int mazeHeight = maze.length * CELL_SIZE;
        int offsetX = (mazePanel.getWidth() - mazeWidth) / 2;
        int offsetY = (mazePanel.getHeight() - mazeHeight) / 2;
    
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                int x = offsetX + j * CELL_SIZE;
                int y = offsetY + i * CELL_SIZE;
    
                switch (maze[i][j]) {
                    case WALL:
                        g.setColor(Color.BLACK);
                        g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                        break;
                    case PATH:
                        g.setColor(Color.WHITE);
                        g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                        break;
                    case START:
                        g.setColor(Color.GREEN);
                        g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                        break;
                    case END:
                        g.setColor(Color.RED);
                        g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                        break;
                    case GHASTLY:
                        g.setColor(Color.BLUE);
                        g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                        break;
                    case PLAYER:
                        g.setColor(Color.YELLOW);
                        g.fillOval(x, y, CELL_SIZE, CELL_SIZE);
                        break;
                }
            }
        }
    }

    private void locatePlayer() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == START) {
                    playerX = i;
                    playerY = j;
                    maze[i][j] = PLAYER;
                    System.out.println("Start position found at: " + i + ", " + j);
                    return;
                }
            }
        }
        System.out.println("Start position not found!");
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] != WALL;
    }

    private void movePlayer(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;

        if (isValidMove(newX, newY)) {
            switch (maze[newX][newY]) {
                case WALL:
                    JOptionPane.showMessageDialog(this, "Invalid move. You hit a wall! Try again.");
                    break;
                case GHASTLY:
                    JOptionPane.showMessageDialog(this, "Oh no! You encountered a Ghastly and got caught. Game Over!");
                    resetGame();
                    break;
                case END:
                    JOptionPane.showMessageDialog(this, "Congratulations! You've reached the end of the maze!");
                    dispose();
                    break;
                default:
                    maze[playerX][playerY] = PATH; // Clear current position
                    playerX = newX;
                    playerY = newY;
                    maze[playerX][playerY] = PLAYER; // Move player to new position
                    mazePanel.repaint();
                    break;
            }
        }
    }

    private void resetGame() {
        // Clear previous player position
        maze[playerX][playerY] = PATH;

        // Set player's position to (1, 1)
        playerX = 1;
        playerY = 1;
        maze[playerX][playerY] = PLAYER;

        // Repaint the maze panel
        mazePanel.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                movePlayer(-1, 0);
                break;
            case KeyEvent.VK_DOWN:
                movePlayer(1, 0);
                break;
            case KeyEvent.VK_LEFT:
                movePlayer(0, -1);
                break;
            case KeyEvent.VK_RIGHT:
                movePlayer(0, 1);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        PokemazeWithGUI pm = null;
        try {
            pm = new PokemazeWithGUI();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
        final PokemazeWithGUI finalPm = pm; // Final variable for lambda expression
        SwingUtilities.invokeLater(() -> {
            if (finalPm != null) {
                finalPm.setVisible(true);
            }
        });
    }
}
