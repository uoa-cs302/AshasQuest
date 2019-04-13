import java.awt.*;

public class Bullet extends GameObject{
    //edit GameObject class to automatically have Handler in it later
    private Handler handler;
    public Bullet(int x, int y, ID id, Handler handler, int mx, int my, SpriteSheet ss){
        super(x,y,id, ss);
        this.handler = handler;

        //speed of bullet
        velX = (mx-x)/10;
        velY = (my-y)/10;
    }

    public void tick(){
        x+= velX;
        x+= velY;

        for (int i = 0; i<handler.object.size();i++){
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID()== ID.Block){
                if (getBounds().intersects(tempObject.getBounds())){
                    handler.removeObject(this);
                }
            }
        }

    }

    public void render(Graphics g){
    g.setColor(Color.green);
    g.fillOval(x,y,8,8);
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,8,8);
    }
}
