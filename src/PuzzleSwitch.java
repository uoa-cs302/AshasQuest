import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class PuzzleSwitch extends StaticEntity {

    private boolean ACTIVE = false;
    protected Rectangle image_size;
    private BufferedImage texture = Assets.button[0];

    public PuzzleSwitch(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 0;
        bounds.height = 0;
        image_size = new Rectangle((int)x, (int)y, width, height);

        setHealth(1000);//object can't be destroyed
    }

    @Override
    public void tick() {
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(image_size)){
            setActive(true);;
        }
    }

    @Override
    public void die(){
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    public void setTexture(BufferedImage texture){
        this.texture = texture;
    }

    public void setActive(boolean active){
        ACTIVE = active;
        
        // 0 is off, 1 is on
        if (ACTIVE){
            setTexture(Assets.button[1]);
        }
        else{
            setTexture(Assets.button[0]);
        }
    }

    public void change() {
        setActive(!ACTIVE);
    }
}