import java.awt.Font;

import java.awt.image.BufferedImage;



public class Assets {
    //This class is where we create our assets(static entities, creature entities, pop ups etc), load them with their
    //textures (from a spritesheet or an image). For the movable characters, like the player and the enemies, we need
    //several such images that will be in an array together.



    private static final int width = 32, height = 32;



    public static Font font28;

    //Here, we declare all of our assets that need images.


    public static BufferedImage dirt, grass, stone, tree, rock;

    public static BufferedImage wood;

    public static BufferedImage[] player_down, player_up, player_left, player_right;

    public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;

    public static BufferedImage[] btn_start;

    public static BufferedImage inventoryScreen;



    public static void init(){

        //We initialise all those previously declared assets, and begin to load them with their images

        font28 = new Font("arial",Font.BOLD,28);



        //The spritesheet contains almost all of the images of the assets, so it makes sense to just load sub images
        //from the main image.
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("../res/sheet.png"));



        inventoryScreen = ImageLoader.loadImage("../res/inventoryScreen.png");



        wood = sheet.crop(width, height, width, height);



        btn_start = new BufferedImage[2];

        btn_start[0] = sheet.crop(width * 6, height * 4, width * 2, height);

        btn_start[1] = sheet.crop(width * 6, height * 5, width * 2, height);


        //Each direction of movement requires an array to describe it,
        //because each motion requires a "left and a right" motion

        player_down = new BufferedImage[2];

        player_up = new BufferedImage[2];

        player_left = new BufferedImage[2];

        player_right = new BufferedImage[2];


        //the width*4 or width*3 etc are relative to what grid space in the sprite sheet that particular image is.
        //eg if something is in the first column of the sheet, it would be at x=0,y=0, while if it was in the second grid
        //space, next to the first, with the same y value, it would be at x=width, y=0. If it was in the grid space next
        //to that, it would be width*2, y=0.

        player_down[0] = sheet.crop(width * 4, 0, width, height);

        player_down[1] = sheet.crop(width * 5, 0, width, height);

        player_up[0] = sheet.crop(width * 6, 0, width, height);

        player_up[1] = sheet.crop(width * 7, 0, width, height);

        player_right[0] = sheet.crop(width * 4, height, width, height);

        player_right[1] = sheet.crop(width * 5, height, width, height);

        player_left[0] = sheet.crop(width * 6, height, width, height);

        player_left[1] = sheet.crop(width * 7, height, width, height);



        zombie_down = new BufferedImage[2];

        zombie_up = new BufferedImage[2];

        zombie_left = new BufferedImage[2];

        zombie_right = new BufferedImage[2];



        zombie_down[0] = sheet.crop(width * 4, height * 2, width, height);

        zombie_down[1] = sheet.crop(width * 5, height * 2, width, height);

        zombie_up[0] = sheet.crop(width * 6, height * 2, width, height);

        zombie_up[1] = sheet.crop(width * 7, height * 2, width, height);

        zombie_right[0] = sheet.crop(width * 4, height * 3, width, height);

        zombie_right[1] = sheet.crop(width * 5, height * 3, width, height);

        zombie_left[0] = sheet.crop(width * 6, height * 3, width, height);

        zombie_left[1] = sheet.crop(width * 7, height * 3, width, height);



        dirt = sheet.crop(width, 0, width, height);

        grass = sheet.crop(width * 2, 0, width, height);

        stone = sheet.crop(width * 3, 0, width, height);

        tree = sheet.crop(0, 0, width, height * 2);

        rock = sheet.crop(0, height * 2, width, height);

    }



}