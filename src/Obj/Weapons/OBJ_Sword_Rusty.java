package Obj.Weapons;

import Entityy.Entity;
import Mainn.GamePanel;

public class OBJ_Sword_Rusty extends Entity {
    public static final String objName = "Rusty Sword";
    public OBJ_Sword_Rusty(GamePanel gp) {
        super(gp);

        type = type_sword;
        name = objName;
        down1 = setup("/Resources/Objects/rusty_sword", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nAn rusty sword.";
        price = 25;
        knockBackPower = 1;
        motionDuration = 5;
        motion2Duration = 25;
    }
}