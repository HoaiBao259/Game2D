package Object;

import javax.imageio.ImageIO;

//import main.GamePanel;

import java.io.IOException;

public class OBJ_Key extends SuperObject{
    //GamePanel gp;
    public OBJ_Key(){
       // this.gp = gp;
        name = "Key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/key.png"));
            //uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
