import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

    //Elements that the game's world is made up of.

    //STATIC STUFF HERE
    //Below will just hold 256 tiles. This will hold one instance of every tile in our game.
    public static Tile[] tiles = new Tile[256];
    public static Tile floor = new Tile(Assets.BOSS_TERRAIN.get("FLOOR"), 0);
    public static Tile top_left_corner = new WallTile(Assets.BOSS_TERRAIN.get("TOP_LEFT_CORNER"), 1);
    public static Tile top_edge = new WallTile(Assets.BOSS_TERRAIN.get("TOP_EDGE"), 2);//tmp
    public static Tile top_right_corner = new WallTile(Assets.BOSS_TERRAIN.get("TOP_RIGHT_CORNER"), 3);
    public static Tile left_edge = new WallTile(Assets.BOSS_TERRAIN.get("LEFT_EDGE"), 4);
    public static Tile right_edge = new WallTile(Assets.BOSS_TERRAIN.get("RIGHT_EDGE"), 5);
    public static Tile bottom_left_corner = new WallTile(Assets.BOSS_TERRAIN.get("BOTTOM_LEFT_CORNER"), 6);
    public static Tile bottom_edge = new WallTile(Assets.BOSS_TERRAIN.get("BOTTOM_EDGE"), 7);
    public static Tile bottom_right_corner = new WallTile(Assets.BOSS_TERRAIN.get("BOTTOM_RIGHT_CORNER"), 8);
    public static Tile black = new WallTile(Assets.BOSS_TERRAIN.get("BLACK"), 9);
    
    public static Tile TOP_RIGHT_INSIDE = new WallTile(Assets.BOSS_TERRAIN.get("TOP_RIGHT_INSIDE"), 11);
    public static Tile TOP_LEFT_INSIDE = new WallTile(Assets.BOSS_TERRAIN.get("TOP_LEFT_INSIDE"), 12);
    public static Tile BOTTOM_RIGHT_INSIDE = new WallTile(Assets.BOSS_TERRAIN.get("BOTTOM_RIGHT_INSIDE"), 13);
    public static Tile BOTTOM_LEFT_INSIDE = new WallTile(Assets.BOSS_TERRAIN.get("BOTTOM_LEFT_INSIDE"), 14);
    public static Tile MAT_TOP_LEFT_CORNER = new Tile(Assets.BOSS_TERRAIN.get("MAT_TOP_LEFT_CORNER"), 15);
    public static Tile MAT_TOP_EDGE = new Tile(Assets.BOSS_TERRAIN.get("MAT_TOP_EDGE"), 16);
    public static Tile MAT_TOP_RIGHT_CORNER = new Tile(Assets.BOSS_TERRAIN.get("MAT_TOP_RIGHT_CORNER"), 17);
    public static Tile MAT_LEFT_EDGE = new Tile(Assets.BOSS_TERRAIN.get("MAT_LEFT_EDGE"), 18);
    public static Tile MAT = new Tile(Assets.BOSS_TERRAIN.get("MAT"), 19);
    public static Tile MAT_RIGHT_EDGE = new Tile(Assets.BOSS_TERRAIN.get("MAT_RIGHT_EDGE"), 20);
    public static Tile MAT_BOTTOM_LEFT_CORNER = new Tile(Assets.BOSS_TERRAIN.get("MAT_BOTTOM_LEFT_CORNER"), 21);
    public static Tile MAT_BOTTOM_EDGE = new Tile(Assets.BOSS_TERRAIN.get("MAT_BOTTOM_EDGE"), 22);
    public static Tile MAT_BOTTOM_RIGHT_CORNER = new Tile(Assets.BOSS_TERRAIN.get("MAT_BOTTOM_RIGHT_CORNER"), 23);

    //CLASS

    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
    private static boolean solid;

    //All tiles need textures/images to represent them.
    //The ID integer will be a way for us to identify the tile, each tile will have one.
    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;

        //The below step means that the element at whatever this tile's id is (when the constructor is called)
        //is equal to this tile that we are creating. eg tiles[0]=this would be saying that this is a grass tile.
        tiles[id] = this;
    }

    public void tick(){

    }

    public void render(Graphics g, int x, int y){
        //Draws the texture of the tile.
        //X or y decide the tile's position on the screen.
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public boolean isSolid(){
        //Default is false, and can be overriden if the particular tile can be collided to.
        return false;
    }

    public int getId(){
        return id;
    }
}