package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import Object.OBJ_Heart;
import Object.SuperObject;

public class UI {   // display all message
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica, purisaB;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; // 0: the first screen, 1:the second screen

    public UI(GamePanel gp) {
        this.gp = gp;


        try {
            InputStream is = getClass().getResourceAsStream("/res/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/res/font/x12y16pxMaruMonica.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    
        // CREATE HUD OBJECT
        SuperObject heart = new OBJ_Heart( /*gp */);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        
    }
    // display message method:
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(maruMonica);
        g2.setFont(purisaB);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);


        //TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        // PLAY STATE
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }
        //PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        //DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
    }
    public void drawPlayerLife() {

            gp.player.life = 3;
            int x = gp.tileSize/2;
            int y = gp.tileSize/2;
            int i = 0;

            // DRAW BLANK HEART
            while(i < gp.player.maxLife/2){
                g2.drawImage(heart_blank, x, y, null);
                i++;
                x += gp.tileSize;
            }
            // RESET
            x = gp.tileSize/2;
            y = gp.tileSize/2;
            i = 0;

            // DRAW CURRENT LIFE
            while(i < gp.player.life) {
                g2.drawImage(heart_half, x, y, null);
                i++;
                if(i < gp.player.life){
                    g2.drawImage(heart_full, x, y, null);
                }
                i++;
                x += gp.tileSize;
            }
    }
    public void drawTitleScreen() {

        if (titleScreenState == 0) {
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            //TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Blue Boy Adventure";
            int x = getXForCenteredText(text);
            int y = gp.tileSize * 3;

            //SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text, x + 5, y + 5);
            //MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            //BLUE BOY IMAGE
            x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
            y += gp.tileSize * 2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

            text = "NEW GAME";
            x = getXForCenteredText(text);
            y += gp.tileSize * 3.5;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "QUIT";
            x = getXForCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            }
        }

    public void drawPauseScreen(){
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }
    public void drawDialogueScreen(){

        //WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;

        drawSubWindow(x, y, width, height);


        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }
    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(0,0,0,205);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height,35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-12,25, 25);
    }
    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    
    // public void draw(Graphics2D g2){
    //     if (gameFinished == true) {
    //         g2.setFont(arial_40);
    //         g2.setColor(Color.white); // set font color

    //         String text;
    //         int textLength;
    //         int x;
    //         int y;

    //         // display the player found treasure
    //         text = "You found the TREASURE!";
    //         textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    //         x = gp.screenWidth/2 - textLength/2;
    //         y = gp.screenHeight/2 - (gp.tileSize*3);
    //         g2.drawString(text, x, y);

    //         // display time played
    //         text = "Your time is: " + decimalFormat.format(playtime) + " seconds ";
    //         textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    //         x = gp.screenWidth/2 - textLength/2;
    //         y = gp.screenHeight/2 + (gp.tileSize*4);
    //         g2.drawString(text, x, y);

    //         // congratulation message for winning the game
    //         g2.setFont(arial_80BFont);
    //         g2.setColor(Color.YELLOW);
    //         text = "Congratulation!!!";
    //         textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    //         x = gp.screenWidth/2 - (textLength/2);
    //         y = gp.screenHeight/2 + (gp.tileSize*2);
    //         g2.drawString(text, x, y);

    //         // stop thread game
    //         gp.gameThread = null;
    //     }
    //     else{    // message is displayed
    //         g2.setFont(arial_40);
    //         g2.setColor(Color.WHITE); // set font color
    //         // draw the key on the top left on the screen
    //       //  g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
    //      //   g2.drawString("x " + gp.player.hasKey, 74, 65);

    //         // Time
    //         playtime +=(double)1/60;
    //      //   g2.drawString("Time: " + decimalFormat.format(playtime), gp.tileSize*12, 65);
        
    //         //MESSAGE
    //         if (messageOn == true) {
    //              g2.setFont(g2.getFont().deriveFont(30F));
    //              g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
    //              messageCounter++;
            
    //             if (messageCounter > 120) {  // display in 2 seconds, then remove
    //                 messageCounter = 0;
    //                 messageOn = false;
    //             }
    //         }
    //     }
    // }
}
