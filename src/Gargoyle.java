import java.awt.Graphics;

public class Gargoyle extends StaticEntity {

    public Gargoyle(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH * 2, Tile.TILEHEIGHT * 2);

        bounds.x = 0;
        bounds.y = (int) (height * 2 / 5f);
        bounds.width = width;
        bounds.height = (int) (height - height / 1.5f);
    }

    @Override
    public void tick() {

    }

    @Override
    public void die(){
        //The below line indicates that if this static entity dies, to drop the item that the player can pick up
        handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int) x, (int) y));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.CASTLE_TERRAIN.get("GARGOYLE"), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

}