package Main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
    public BufferedImage scaleImage(BufferedImage original, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
         // Enable rendering hints for better scaling quality
 
        g2.drawImage(original, width, height, null);
        g2.dispose();
        return scaledImage;
    }
}