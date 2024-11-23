package Object;

import javax.imageio.ImageIO;

//import main.GamePanel;

import java.io.IOException;

public class OBJ_Door extends SuperObject{
    //GamePanel gp;
    public OBJ_Door(){
       // this.gp = gp;
        name = "Door";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/door.png"));
          //  uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
