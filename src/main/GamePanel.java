package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;
import Object.SuperObject;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // public for using in the Player class
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // WORLD SETTING
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;


    //FPS
    int FPS = 60;

    //SYSTEM
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound sound = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread;

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];


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

    public void setupGame(){
        aSetter.setObject();
        playMusic(0);
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
            // if (keyH.upPressed == true){
            //     playerY -= playerSpeed;
            // }
            // else if (keyH.downPressed == true){
            //     playerY += playerSpeed;
            // }
            // else if (keyH.leftPressed == true){
            //     playerX -= playerSpeed;
            // }
            // else if (keyH.rightPressed == true){
            //     playerX += playerSpeed;
            // }
        player.update();

    }
    public void paintComponent(Graphics g){
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D)g;
            // g2.setColor(Color.white);
            // g2.fillRect(playerX, playerY, tileSize, tileSize);
            //TILE
            tileM.draw(g2);
            //OBJECT
            for(int i = 0 ; i < obj.length ; i++){
                if(obj[i] != null){
                    obj[i].draw(g2, this);
                }
            }
            //PLAYER
            player.draw(g2);
            g2.dispose();
    }
    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic(){
        sound.stop();
    }
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}