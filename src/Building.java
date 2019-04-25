import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Building extends StaticEntity {

    private BufferedImage texture;

    public Building(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH * 11, Tile.TILEHEIGHT * 9);

        texture = Assets.building;
        setHealth(5);

        bounds.x = 10;
        bounds.y = (int) (height * 2 / 5f);
        bounds.width = width - 20;
        bounds.height = (int) (height * 3 / 5f);
    }

    @Override
    public void tick() {
    }

    @Override
    public void die(){
        //The below line indicates that if this static entity dies, to drop the item that the player can pick up
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x + width / 2, (int) y + height / 2));
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x + width / 2 - 50, (int) y + height / 2));
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x + width / 2 + 50, (int) y + height / 2));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}