import java.awt.image.BufferedImage;

import java.io.IOException;



import javax.imageio.ImageIO;
import java.io.File;



public class ImageLoader {



    public static BufferedImage loadImage(String path){

        try {
            System.out.println(path);
            return ImageIO.read(new File(path)); //this should be as stream right um yes
                                                                              // umm, apparently no? maybe?


        } catch (IOException e) {

            e.printStackTrace();

            System.exit(1);

        }

        return null;

    }



}