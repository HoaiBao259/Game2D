package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class NPC_OldMan  extends Entity {


    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }

    public void getImage() {
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/oldman_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setDialogue() {

        dialogue[0] = "Hello, lad.";
        dialogue[1] = "So you've come to this island to \nfind the treasure?";
        dialogue[2] = "I used to be a great wizard but now... \nI'm a bit too old for taking an adventure.";
        dialogue[3] = "Well, good luck on you.";

    }
    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick up a number from 1 to 100

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
    public void speak() {

        //Do this character specific stuff

        super.speak();
    }
}