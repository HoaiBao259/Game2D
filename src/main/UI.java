package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import Object.OBJ_Key;

public class UI {   // display all message
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80BFont;
   // BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false; 

    double playtime;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;
        this.arial_40 = new Font("Arial", Font.ITALIC, 30);
        this.arial_80BFont = new Font("Arial", Font.BOLD, 80);
       // OBJ_Key key = new OBJ_Key();
      //  keyImage = key.image;
    }
    // display message method:
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_80BFont);
        g2.setColor(Color.white);

        // check game State
        if (gp.gameState == gp.playState) {
            // do play state stuff later
        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }


    }
    public void drawPauseScreen(){
        String text = "PAUSE";
        int x = getXForCenteredText(text);
        
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);


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
