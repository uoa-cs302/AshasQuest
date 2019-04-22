import java.awt.Graphics;

import java.awt.image.BufferedImage;



public class Tile {


    //Elements that the game's world is made up of.

    //The grasstile, dirttile and rocktile below are going to be example ones, change if necessary.



    //STATIC STUFF HERE

    //Below will just hold 256 tiles. This will hold one instance of every tile in our game.
    public static Tile[] tiles = new Tile[256];

    public static Tile grassTile = new GrassTile(0);

    public static Tile dirtTile = new DirtTile(1);

    public static Tile rockTile = new RockTile(2);



    //CLASS



    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;


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

        //This is false on default, and can be changed if the particular tile can be collided to.

        return false;

    }



    public int getId(){

        return id;

    }

}