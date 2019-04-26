import java.awt.Graphics;
import java.awt.Rectangle;

public class Door extends StaticEntity {

    protected Rectangle image_size;
    private int index = 0;
    private boolean count = false;
    private int wait = 0;

    public Door(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH * 3, Tile.TILEHEIGHT * 5 / 2);

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
        
        setOntop(false);
        setHealth(1000);//object can't be destroyed
    }

    public void open(){
        //if door not already open
        if (index == 0){
            bounds.width = 0;
            bounds.height = 0;
            count = true;
        }
    }

    @Override
    public void hurt(int amt){
        //object can't be destroyed
    }

    @Override
    public void tick() {
        // System.out.println(handler.getWorld().getNumOffSwitches());
        if (handler.getWorld().getNumOffSwitches() == 0){
            open();
            // System.out.println("Yay :)");
        }
        if (count){
            wait ++;
            if (wait == 25){
                wait = 0;
                index++;
                if (index == 4){
                    count = false;
                }
            }
        }
    }

    @Override
    public void die(){
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.door[index], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

}