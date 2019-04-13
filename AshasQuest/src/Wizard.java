

//import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Wizard extends GameObject {

    private Handler handler;
    private BufferedImage wizard_image;
    private Game game;
    private int speed = 3;
    private int width = 32;
    private int height = 48;

    public Wizard(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        this.game = game;
        this.wizard_image = ss.grabImage(1, 1, 32, 48);

    }

    @Override
    public void tick() {
        this.x += velX;
        this.y += velY;

        collision();
        movement();

        if(game.hp <= 0) {
            JOptionPane.showMessageDialog(null, "Game Over");
            System.exit(0);
        }
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Block) {
                if (this.getBounds().intersects(tempObject.getBounds())) {
                    this.x += this.velX * -1;
                    this.y += this.velY * -1;
                }
            }
            if (tempObject.getID() == ID.Crate) {
                if (this.getBounds().intersects(tempObject.getBounds())) {
                    game.ammo += 30;
                    handler.removeObject(tempObject);
                }
            }
            if (tempObject.getID() == ID.Enemy) {
                if (this.getBounds().intersects(tempObject.getBounds())) {
                    game.hp--;
                }
            }
            if (tempObject.getID() == ID.Door) {
                if (this.getBounds().intersects(tempObject.getBounds())) {
                    JOptionPane.showMessageDialog(null, "Congragulations, you won!");
                    System.exit(0);
                }
            }
        }
    }

    private void movement() {
        if (handler.isUp()) {
            this.velY = -speed;
        } else if (!handler.isDown()) {
            this.velY = 0;
        }
        if (handler.isDown()) {
            this.velY = speed;
        } else if (!handler.isUp()) {
            this.velY = 0;
        }
        if (handler.isLeft()) {
            this.velX = -speed;
        } else if (!handler.isRight()) {
            this.velX = 0;
        }
        if (handler.isRight()) {
            this.velX = speed;
        } else if (!handler.isLeft()) {
            this.velX = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(this.wizard_image, x, y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

}