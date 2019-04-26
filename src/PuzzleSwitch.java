import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class PuzzleSwitch{

    private boolean ACTIVE = false;
    protected Rectangle image_size;
    private BufferedImage texture = Assets.button[0];
    protected Handler handler;
    protected float x, y;
    protected int width, height;

    public PuzzleSwitch(Handler handler, float x, float y) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = Tile.TILEWIDTH;
        this.height = Tile.TILEHEIGHT;
        image_size = new Rectangle((int)x, (int)y, width, height);
    }

    public void tick() {
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(image_size)){
            setActive(true);
        }
    }

    public void render(Graphics g) {
        g.drawImage(texture, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    public void setTexture(BufferedImage texture){
        this.texture = texture;
    }

    public void setActive(boolean active){
        if (active != ACTIVE){
            ACTIVE = active;
            
            // 0 is off, 1 is on
            if (ACTIVE){
                setTexture(Assets.button[1]);
                handler.getWorld().decNumOffSwitches();
            }
            else{
                setTexture(Assets.button[0]);
                handler.getWorld().incNumOffSwitches();
            }
        }
    }

    public void change() {
        setActive(!ACTIVE);
    }
}