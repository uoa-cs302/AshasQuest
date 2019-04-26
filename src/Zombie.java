import java.awt.*;
import java.awt.image.BufferedImage;

public class Zombie extends Enemy {
    private BufferedImage texture;
    public Animation zomDown, zomUp, zomLeft, zomRight;

    public Zombie(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

        setHealth(3);

        //Below decides the dimensions for the player's collision box.
        bounds.x = 22;
        bounds.y = 44;
        bounds.width = 19;
        bounds.height = 19;

        //Movement animations
        zomDown = new Animation(300, Assets.zombie_down);
        zomUp = new Animation(300, Assets.zombie_up);
        zomLeft = new Animation(300, Assets.zombie_left);
        zomRight = new Animation(300, Assets.zombie_right);
    }

    @Override
    public void tick() {
        //Animations
        zomDown.tick();
        zomUp.tick();
        zomRight.tick();
        zomLeft.tick();

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x, (int) y));

    }
    private BufferedImage getCurrentAnimationFrame(){
        if(xMove < 0){
            return zomLeft.getCurrentFrame();
        }else if(xMove > 0){
            return zomRight.getCurrentFrame();
        }else if(yMove < 0){
            return zomUp.getCurrentFrame();
            //  }else if (yMove > 0){
            //     return animDown.getCurrentFrame();
        } else {
            return zomDown.getCurrentFrame();
        }
    }
}
