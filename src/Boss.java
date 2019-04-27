import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Boss extends Enemy {
    private BufferedImage texture;
    public Animation zomDown, zomUp, zomLeft, zomRight;
    public boolean paused = false;
    public int pursuitTimer;
    private boolean isMoving;
    public Player player;
//    int [][] Path = {  {  1,  0, 12, -1 },
//        {  7, -3,  2,  5 },
//        { -5, -2,  2, -9 }
//    };


    public Boss(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, 128, 128);

        setHealth(3);

        this.pursuitTimer= 0;
        this.handler = handler;
        this.isMoving = false;

        //Below decides the dimensions for the creature's collision box.
        bounds.x = 22;
        bounds.y = 44;
        bounds.width = 19;
        bounds.height = 19;

        //Movement animations
        zomDown = new Animation(300, Assets.boss_down);
        zomUp = new Animation(300, Assets.boss_up);
        zomLeft = new Animation(300, Assets.boss_left);
        zomRight = new Animation(300, Assets.boss_right);
//        this.player = this.handler.getWorld().getEntityManager().getPlayer();
    }

    @Override
    public void tick() {
        //  System.out.println("Wrok");
        if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_C))||
                (handler.getKeyManager().keyJustPressed(KeyEvent.VK_P))||
                (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE))||
                handler.getKeyManager().keyJustPressed(KeyEvent.VK_M)||
                handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)||
                handler.getKeyManager().keyJustPressed(KeyEvent.VK_I)||
                handler.getKeyManager().keyJustPressed(KeyEvent.VK_PAGE_DOWN)){
            paused = !paused;
        }

        this.x += this.xMove;
        this.y += this.yMove;

        if (!isMoving) {
            this.pursuitTimer++;
        }

        for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
            //make sure entity isn't ourselves
            if (e.equals(this))
                continue;
            //below means we have hit that entity. We are hurting them with a value of 1.
            // if(e.getCollisionBounds(0, 0).intersects(ar)){
            //    e.hurt(1);
            //    return;
            if (e.equals(this.handler.getWorld().getEntityManager().getPlayer())) {
                isMoving = true;
                if (this.isMoving) {
                    this.pursuePlayer(this.handler.getWorld().getEntityManager().getPlayer());

                }
            }
        }
        //Animations
        zomDown.tick();
        zomUp.tick();
        zomRight.tick();
        zomLeft.tick();

        // Path();
        move();
    }

    private void turnBack() {
        this.xMove *= -1;
        this.yMove *= -1;
    }

    private void pursuePlayer(Player player) {
        // System.out.println("Work");
        if (paused){
            return;
        }
        if(this.x - player.x  < 400 && this.y - player.y < 400) {
            if (this.x > player.getX()) {
                this.xMove = -1;

            }
            if (this.x < player.getX()) {
                this.xMove= 1;

            }
            if (this.y > player.getY()) {
                this.yMove = -1;

            }
            if (this.y < player.getY()) {
                this.yMove = 1;

            }
        } else {
            this.xMove = 0;
            this.yMove = 0;
        }

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

//    private void Path(){
//        xMove = 0;
//        yMove = 0;
//
//        for (int y = 500; y < 550; y++){
//            for (int x = 700; x <750; x++){
//                xMove = x;
//                yMove = y;
//            }
//        }
//
////        for (int y = 550; y > 500; y--){
////            for (int x = 750; x >700; x--){
////                xMove = x;
////                yMove = y;
////            }
////        }
//
//
//    }

}
