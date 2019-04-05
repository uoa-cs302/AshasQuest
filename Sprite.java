import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

    //private static final int tile_size = 16;//change based on tile size

    public static BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;

        try {
            sprite = ImageIO.read(new File("sprites/" + file + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sprite;
    }

    public static BufferedImage getSprite(int row, int col, String filename, int tile_size) {
        BufferedImage spriteSheet = loadSprite(filename);
        return spriteSheet.getSubimage(col * tile_size, row * tile_size, tile_size, tile_size);
    }

}