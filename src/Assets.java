import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.HashMap;
// import java.util.Map;


public class Assets {
    //This class is where we create our assets(static entities, creature entities, pop ups etc), load them with their
    //textures (from a spritesheet or an image). For the movable characters, like the player and the enemies, we need
    //several such images that will be in an array together.

    
    private static final int STD_TILE_SIZE = 32;
    public static Font font28;

    //Here, we declare all of our assets that need images.

    public static BufferedImage dirt, grass, stone, tree, rock, wood;
    public static BufferedImage[] player_down, player_up, player_left, player_right;
    public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
    public static BufferedImage[] btn_start;
    public static BufferedImage inventoryScreen;

    //The following are hashmaps of each of the possible terrains that will be used
    public static final HashMap<String, BufferedImage> BOSS_TERRAIN = new HashMap<>();
    public static final HashMap<String, BufferedImage> CASTLE_TERRAIN = new HashMap<>();
    public static final HashMap<String, BufferedImage> ASHLANDS_TERRAIN = new HashMap<>();
    public static final HashMap<String, BufferedImage> HOMETOWN_TERRAIN = new HashMap<>();

    public static void init_hashmaps(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("../res/boss.png"), 32, 32);
        BOSS_TERRAIN.put("MAT_TOP_LEFT_CORNER", sheet.crop(3, 2, 1, 1));
        BOSS_TERRAIN.put("MAT_TOP_EDGE", sheet.crop(4, 2, 1, 1));
        BOSS_TERRAIN.put("MAT_TOP_RIGHT_CORNER", sheet.crop(5, 2, 1, 1));
        BOSS_TERRAIN.put("MAT_LEFT_EDGE", sheet.crop(3, 3, 1, 1));
        BOSS_TERRAIN.put("MAT", sheet.crop(4, 3, 1, 1));
        BOSS_TERRAIN.put("MAT_RIGHT_EDGE", sheet.crop(5, 3, 1, 1));
        BOSS_TERRAIN.put("MAT_BOTTOM_LEFT_CORNER", sheet.crop(3, 4, 1, 1));
        BOSS_TERRAIN.put("MAT_BOTTOM_EDGE", sheet.crop(4, 4, 1, 1));
        BOSS_TERRAIN.put("MAT_BOTTOM_RIGHT_CORNER", sheet.crop(5, 4, 1, 1));
        BOSS_TERRAIN.put("TOP_LEFT_CORNER", sheet.crop(0, 0, 1, 1));
        BOSS_TERRAIN.put("TOP_EDGE", sheet.crop(1, 0, 1, 1));
        BOSS_TERRAIN.put("TOP_RIGHT_CORNER", sheet.crop(2, 0, 1, 1));
        BOSS_TERRAIN.put("LEFT_EDGE", sheet.crop(0, 1, 1, 1));
        BOSS_TERRAIN.put("FLOOR", sheet.crop(1, 1, 1, 1));
        BOSS_TERRAIN.put("RIGHT_EDGE", sheet.crop(2, 1, 1, 1));
        BOSS_TERRAIN.put("BOTTOM_LEFT_CORNER", sheet.crop(0, 2, 1, 1));
        BOSS_TERRAIN.put("BOTTOM_EDGE", sheet.crop(1, 2, 1, 1));
        BOSS_TERRAIN.put("BOTTOM_RIGHT_CORNER", sheet.crop(2, 2, 1, 1));
        BOSS_TERRAIN.put("TOP_LEFT_INSIDE", sheet.crop(4, 1, 1, 1));
        BOSS_TERRAIN.put("TOP_RIGHT_INSIDE", sheet.crop(3, 1, 1, 1));
        BOSS_TERRAIN.put("BOTTOM_LEFT_INSIDE", sheet.crop(4, 0, 1, 1));
        BOSS_TERRAIN.put("BOTTOM_RIGHT_INSIDE", sheet.crop(3, 0, 1, 1));
        BOSS_TERRAIN.put("BLACK", sheet.crop(6, 3, 1, 1));

        
        sheet = new SpriteSheet(ImageLoader.loadImage("../res/castle.png"), 16, 16);
        CASTLE_TERRAIN.put("TOP_LEFT_CORNER", sheet.crop(10, 1, 1, 1));
        CASTLE_TERRAIN.put("TOP_EDGE", sheet.crop(11, 1, 1, 1));
        CASTLE_TERRAIN.put("TOP_RIGHT_CORNER", sheet.crop(12, 1, 1, 1));
        CASTLE_TERRAIN.put("LEFT_EDGE", sheet.crop(10, 2, 1, 1));
        CASTLE_TERRAIN.put("FLOOR", sheet.crop(11, 2, 1, 1)); 
        CASTLE_TERRAIN.put("RIGHT_EDGE", sheet.crop(12, 2, 1, 1));
        CASTLE_TERRAIN.put("BOTTOM_LEFT_CORNER", sheet.crop(10, 3, 1, 1));
        CASTLE_TERRAIN.put("BOTTOM_EDGE", sheet.crop(11, 3, 1, 1));
        CASTLE_TERRAIN.put("BOTTOM_RIGHT_CORNER", sheet.crop(12, 3, 1, 1));
        CASTLE_TERRAIN.put("TOP_LEFT_INSIDE", sheet.crop(15, 2, 1, 1));
        CASTLE_TERRAIN.put("TOP_RIGHT_INSIDE", sheet.crop(14, 2, 1, 1));
        CASTLE_TERRAIN.put("BOTTOM_LEFT_INSIDE", sheet.crop(15, 1, 1, 1));
        CASTLE_TERRAIN.put("BOTTOM_RIGHT_INSIDE", sheet.crop(14, 1, 1, 1));
        CASTLE_TERRAIN.put("WALL", sheet.crop(10, 4, 1, 1));

        sheet = new SpriteSheet(ImageLoader.loadImage("../res/ashlands.png"), 16, 16);
        ASHLANDS_TERRAIN.put("ASH", sheet.crop(2, 1, 1, 1));//can be randomly generated between 1&7
        ASHLANDS_TERRAIN.put("DIRT", sheet.crop(1, 2, 1, 1));//can be randomly generated between 1&6
        ASHLANDS_TERRAIN.put("LEFT_EDGE", sheet.crop(4, 8, 1, 1));
        ASHLANDS_TERRAIN.put("RIGHT_EDGE", sheet.crop(6, 8, 1, 1));
        ASHLANDS_TERRAIN.put("TOP_EDGE", sheet.crop(5, 7, 1, 1));
        ASHLANDS_TERRAIN.put("BOTTOM_EDGE", sheet.crop(5, 9, 1, 1));
        ASHLANDS_TERRAIN.put("TOP_LEFT_CORNER", sheet.crop(4, 7, 1, 1));
        ASHLANDS_TERRAIN.put("TOP_RIGHT_CORNER", sheet.crop(6, 7, 1, 1));
        ASHLANDS_TERRAIN.put("BOTTOM_LEFT_CORNER", sheet.crop(4, 9, 1, 1));
        ASHLANDS_TERRAIN.put("BOTTOM_RIGHT_CORNER", sheet.crop(6, 9, 1, 1));
        ASHLANDS_TERRAIN.put("TOP_LEFT_INSIDE", sheet.crop(7, 7, 1, 1));
        ASHLANDS_TERRAIN.put("TOP_RIGHT_INSIDE", sheet.crop(8, 7, 1, 1));
        ASHLANDS_TERRAIN.put("BOTTOM_LEFT_INSIDE", sheet.crop(7, 8, 1, 1));
        ASHLANDS_TERRAIN.put("BOTTOM_RIGHT_INSIDE", sheet.crop(8, 8, 1, 1));

        sheet = new SpriteSheet(ImageLoader.loadImage("../res/hometown.png"), 16, 16);
        HOMETOWN_TERRAIN.put("GRASS", sheet.crop(1, 1, 1, 1)); //can be randomly generated between 1&8
        HOMETOWN_TERRAIN.put("DIRT", sheet.crop(1, 2, 1, 1)); //can be randomly generated between 1&5
        HOMETOWN_TERRAIN.put("LEFT_EDGE", sheet.crop(18, 2, 1, 1));
        HOMETOWN_TERRAIN.put("RIGHT_EDGE", sheet.crop(16, 2, 1, 1));
        HOMETOWN_TERRAIN.put("TOP_EDGE", sheet.crop(17, 3, 1, 1));
        HOMETOWN_TERRAIN.put("BOTTOM_EDGE", sheet.crop(17, 1, 1, 1));
        HOMETOWN_TERRAIN.put("TOP_LEFT_CORNER", sheet.crop(19, 1, 1, 1));
        HOMETOWN_TERRAIN.put("TOP_RIGHT_CORNER", sheet.crop(20, 1, 1, 1));
        HOMETOWN_TERRAIN.put("BOTTOM_LEFT_CORNER", sheet.crop(19, 2, 1, 1));
        HOMETOWN_TERRAIN.put("BOTTOM_RIGHT_CORNER", sheet.crop(20, 2, 1, 1));
        HOMETOWN_TERRAIN.put("TOP_LEFT_INSIDE", sheet.crop(18, 3, 1, 1));
        HOMETOWN_TERRAIN.put("TOP_RIGHT_INSIDE", sheet.crop(16, 3, 1, 1));
        HOMETOWN_TERRAIN.put("BOTTOM_LEFT_INSIDE", sheet.crop(18, 1, 1, 1));
        HOMETOWN_TERRAIN.put("BOTTOM_RIGHT_INSIDE", sheet.crop(16, 1, 1, 1));
    }

    public static void init(){

        //We initialise all those previously declared assets, and begin to load them with their images

        font28 = new Font("arial",Font.BOLD,28);
        inventoryScreen = ImageLoader.loadImage("../res/inventoryScreen.png");

        //The spritesheet contains almost all of the images of the assets, so it makes sense to just load sub images
        //from the main image.
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("../res/sheet.png"), STD_TILE_SIZE, STD_TILE_SIZE);

        wood = sheet.crop(1, 1, 1, 1);

        btn_start = new BufferedImage[2];

        btn_start[0] = sheet.crop(6, 4, 2, 1);
        btn_start[1] = sheet.crop(6, 5, 2, 1);


        init_hashmaps();
        //Each direction of movement requires an array to describe it,
        //because each motion requires a "left and a right" motion

        player_down = new BufferedImage[2];
        player_up = new BufferedImage[2];
        player_left = new BufferedImage[2];
        player_right = new BufferedImage[2];


        //the STD_TILE_SIZE*4 or STD_TILE_SIZE*3 etc are relative to what grid space in the sprite sheet that particular image is.
        //eg if something is in the first column of the sheet, it would be at x=0,y=0, while if it was in the second grid
        //space, next to the first, with the same y value, it would be at x=STD_TILE_SIZE, y=0. If it was in the grid space next
        //to that, it would be STD_TILE_SIZE*2, y=0.

        player_down[0] = sheet.crop(4, 0, 1, 1);
        player_down[1] = sheet.crop(5, 0, 1, 1);
        player_up[0] = sheet.crop(6, 0, 1, 1);
        player_up[1] = sheet.crop(7, 0, 1, 1);
        player_right[0] = sheet.crop(4, 1, 1, 1);
        player_right[1] = sheet.crop(5, 1, 1, 1);
        player_left[0] = sheet.crop(6, 1, 1, 1);
        player_left[1] = sheet.crop(7, 1, 1, 1);

        zombie_down = new BufferedImage[2];
        zombie_up = new BufferedImage[2];
        zombie_left = new BufferedImage[2];
        zombie_right = new BufferedImage[2];

        zombie_down[0] = sheet.crop(4, 2, 1, 1);
        zombie_down[1] = sheet.crop(5, 2, 1, 1);
        zombie_up[0] = sheet.crop(6, 2, 1, 1);
        zombie_up[1] = sheet.crop(7, 2, 1, 1);
        zombie_right[0] = sheet.crop(4, 3, 1, 1);
        zombie_right[1] = sheet.crop(5, 3, 1, 1);
        zombie_left[0] = sheet.crop(6, 3, 1, 1);
        zombie_left[1] = sheet.crop(7, 3, 1, 1);

        dirt = sheet.crop(1, 0, 1, 1);
        grass = sheet.crop(2, 0, 1, 1);
        stone = sheet.crop(3, 0, 1, 1);
        tree = sheet.crop(0, 0, 1, 2);
        rock = sheet.crop(0, 2, 1, 1);
    }
}