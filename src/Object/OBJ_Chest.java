package Object;

import javax.imageio.ImageIO;

//import main.GamePanel;

import java.io.IOException;

public class OBJ_Chest extends SuperObject{
   // GamePanel gp;
    public OBJ_Chest(){
        //this.gp = gp;
        name = "Chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/chest.png"));
          //  uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
