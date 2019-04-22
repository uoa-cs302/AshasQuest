import java.awt.image.BufferedImage;

import java.io.IOException;



import javax.imageio.ImageIO;



public class ImageLoader {



    public static BufferedImage loadImage(String path){

        try {

            return ImageIO.read(ImageLoader.class.getResourceAsStream(path)); //this should be as stream right um yes


        } catch (IOException e) {

            e.printStackTrace();

            System.exit(1);

        }

        return null;

    }



}