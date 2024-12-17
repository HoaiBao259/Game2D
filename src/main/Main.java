package Main;

// PLEASE RENAME THE PACKAGES: main, entity and tile to Main, Entity and Tile BEFORE RUNNING.
// THE FIRST LETTER SHOULD BE UPPERCASE

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static JFrame window;
    // PLEASE RENAME THE PACKAGES: main, entity and tile to Main, Entity and Tile BEFORE RUNNING.
    // THE FIRST LETTER SHOULD BE UPPERCASE
    public static void main(String[] args) throws IOException, FontFormatException {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Java Game");
        new Main().setIcon();

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        gamePanel.config.loadConfig();

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }

    public void setIcon(){
        ImageIcon icon = new ImageIcon("/Resources/Player/p_down_1.png");
        window.setIconImage(icon.getImage());
    }
}