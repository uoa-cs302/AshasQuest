import java.awt.Graphics;


public class World {


    //widht and height will be used in terms of times of the world.

    private Handler handler;

    private int width, height;

    private int spawnX, spawnY;

    //this will hold the ids of tiles and what position they will be created in. There will be rows of tiles
    //and columns of tiles
    private int[][] tiles;

    //Entities

    private EntityManager entityManager;

    // Item

    private ItemManager itemManager;



    public World(Handler handler, String path){

        this.handler = handler;

        entityManager = new EntityManager(handler, new Player(handler, 100, 100));

        itemManager = new ItemManager(handler);

        // Temporary entity code!

        entityManager.addEntity(new Tree(handler, 132, 250));

        entityManager.addEntity(new Rock(handler, 132, 450));

        entityManager.addEntity(new Rock(handler, 350, 300));

        entityManager.addEntity(new Rock(handler, 400, 345));

        entityManager.addEntity(new Tree(handler, 625, 325));



        loadWorld(path);


        //This sets where on the map the player character will show up.

        entityManager.getPlayer().setX(spawnX);

        entityManager.getPlayer().setY(spawnY);

    }



    public void tick(){

        itemManager.tick();

        entityManager.tick();

    }



    public void render(Graphics g){

        //Below is ensuring the boundaries of what is rendered on the map, keeping them within the tile limits
        //and the camera limits.

        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);

        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);

        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);

        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);




        //Loop extends from beginning of map to end of map
        for(int y = yStart;y < yEnd;y++){

            for(int x = xStart;x < xEnd;x++){

                //Here, we are converting the tile co-ordinates to pixels. x by width, y by height.
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),

                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));

            }

        }

        // Items

        itemManager.render(g);

        //Entities

        entityManager.render(g);

    }



    public Tile getTile(int x, int y){

        //gets x and y positions for the tile. It is going to look for the id in the tiles array at x,y.

        //if
        if(x < 0 || y < 0 || x >= width || y >= height)

            return Tile.grassTile;


        //accesses tiles array in Tile class.

        Tile t = Tile.tiles[tiles[x][y]];

        //if th tile that we got is equal to nothing, we run a default of a dirtTile.
        if(t == null)

            return Tile.dirtTile;

        return t;

    }



    private void loadWorld(String path){

        //This loads the world file(s) that we have in our res, and grabs tokens from it to use for indexing.
        String file = Utils.loadFileAsString(path);

        //We must split up each individual number in the file around every amount of white space.
        String[] tokens = file.split("\\s+");

        //The first two numbers at the very top of the world file tell what the world's width and height is.


        width = Utils.parseInt(tokens[0]);

        height = Utils.parseInt(tokens[1]);

        //the second and third will decide where on the world the player will spawn at. What tile will the player spawn at.

        spawnX = Utils.parseInt(tokens[2]);

        spawnY = Utils.parseInt(tokens[3]);

        //After that comes the world data. The tile data will be represented by an ID number on that position on that
        //file.Ensure that all of the ids are relative to the height and width you have set.



        tiles = new int[width][height];

        //This loops through every element of our tiles array and set the tiles in it equal to something
        for(int y = 0;y < height;y++){

            for(int x = 0;x < width;x++){

                //We are converting the x and y of the 4 loops into the 1 dimensional array index.
                //We are adding 4 because the first 4 numbers are not actual world data.

                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);

            }

        }

    }



    public int getWidth(){

        return width;

    }



    public int getHeight(){

        return height;

    }



    public EntityManager getEntityManager() {

        return entityManager;

    }



    public Handler getHandler() {

        return handler;

    }



    public void setHandler(Handler handler) {

        this.handler = handler;

    }



    public ItemManager getItemManager() {

        return itemManager;

    }



    public void setItemManager(ItemManager itemManager) {

        this.itemManager = itemManager;

    }



}