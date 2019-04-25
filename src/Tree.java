import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

public class Tree extends StaticEntity {

    private BufferedImage texture;

    public Tree(Handler handler, float x, float y, int id) {
        super(handler, x, y, Tile.TILEWIDTH * 2, Tile.TILEHEIGHT * 2);

        if (id == -1) {
            int n = ThreadLocalRandom.current().nextInt(Assets.tree.length);
            texture = Assets.tree[n];
        }
        else if (id == 14) {
            texture = Assets.ASHLANDS_TERRAIN.get("TREE");
        }
        else if (id == 15) {
            texture = Assets.ASHLANDS_TERRAIN.get("TREE2");
        }
        else {
            texture = Assets.tree[id];
        }

        bounds.x = 10;
        bounds.y = (int) (height / 1.5f);
        bounds.width = width - 20;
        bounds.height = (int) (height - height / 1.5f);
    }

    @Override
    public void tick() {

    }

    @Override
    public void die(){
        //The below line indicates that if this static entity dies, to drop the item that the player can pick up
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x, (int) y));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(texture, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

}