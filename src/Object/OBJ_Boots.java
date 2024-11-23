package Object;

import java.io.IOException;

import javax.imageio.ImageIO;

//import main.GamePanel;

public class OBJ_Boots extends SuperObject{
    //GamePanel gp;
    public OBJ_Boots(){
        //this.gp = gp;
        name = "Boots";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/boots.png"));
           // uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}