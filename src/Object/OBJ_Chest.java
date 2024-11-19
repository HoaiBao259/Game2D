package Object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject{
    public OBJ_Chest(){
        name = "Chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/Objects/chest.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
