import java.awt.Graphics;
import java.io.File;

public class GameState extends State {

    //At the moment, the game state runs the world and everything that is loaded inside of it.



    private World world;



    public GameState(Handler handler){

        super(handler);
        //Note: What we need to do is to have it so that we are capable of loading multiple worlds, as currently
        //there is only one in place.

        world = new World(handler, "res" + File.separator + "world1.txt");

        handler.setWorld(world);

    }



    @Override

    public void tick() {

        world.tick();

    }



    @Override

    public void render(Graphics g) {

        world.render(g);

    }



}