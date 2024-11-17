package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener( keyH);
        this.setFocusable(true);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
/*     public void run(){
        
        double drawInterval = 1000000000/FPS; // 0.01666 seconds
        double nexDrawTime = System.nanoTime() + drawInterval;

        while(gameThread !=null) {

            long currentTime = System.nanoTime();
            System.out.println("current Time:"+currentTime);

    //1 UPDATE: update information such as character positions
                update();
    //2 DRAW: draw the screen with the updated information
                repaint();

                
                try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingDrawTime = remaningTime/1000000;
                if (remainingTime<0){
                        remainingTime=0;
                }

                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;
                
                } catch (InteruptedException e) {

                    e.printStackTrace();
                }
        }
    }
*/
    public void run () {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/ drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta>=1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer>=1000000000){
                System.out.println("FPS:" + drawCount);
                timer = 0;
            }

        }
    }
    public void update(){
            if (keyH.upPressed == true){
                playerY -= playerSpeed;
            }
            else if (keyH.downPressed == true){
                playerY += playerSpeed;
            }
            else if (keyH.leftPressed == true){
                playerX -= playerSpeed;
            }
            else if (keyH.rightPressed == true){
                playerX += playerSpeed;
            }

    }
    public void paintComponent(Graphics g){
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(Color.white);
            g2.fillRect(playerX, playerY, tileSize, tileSize);
            g2.dispose();
    }
}