import java.awt.*;

public class Door  extends GameObject {

    public Door(int x, int y, ID id, SpriteSheet ss) {
        super(x, y, id, ss);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 32);
    }

}
