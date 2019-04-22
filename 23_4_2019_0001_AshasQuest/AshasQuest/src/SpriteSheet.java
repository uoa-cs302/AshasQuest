
import java.awt.image.BufferedImage;



public class SpriteSheet {



    private BufferedImage sheet;


    //Because a SpriteSheet is one image that contains several smaller images, we load the image

    public SpriteSheet(BufferedImage sheet){

        this.sheet = sheet;

    }



    //And then take out the specific smaller image we want at the time.
    public BufferedImage crop(int x, int y, int width, int height){

        return sheet.getSubimage(x, y, width, height);

    }



}