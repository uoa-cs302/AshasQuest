import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Zombie extends Enemy {
    public Animation zomDown, zomUp, zomLeft, zomRight;
    public boolean paused = false;
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
    public int attack_counter = 0;
    public int pursuitTimer;
    private boolean isMoving;
    public Player player;

    public Zombie(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

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
        zomDown = new Animation(300, Assets.zombie_down);
        zomUp = new Animation(300, Assets.zombie_up);
        zomLeft = new Animation(300, Assets.zombie_left);
        zomRight = new Animation(300, Assets.zombie_right);
    }

    @Override
    public void tick() {
        if ((handler.getKeyManager().keyJustPressed(KeyEvent.VK_C))||
                (handler.getKeyManager().keyJustPressed(KeyEvent.VK_P))||
                (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE))||
                handler.getKeyManager().keyJustPressed(KeyEvent.VK_M)||
                handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)||
                handler.getKeyManager().keyJustPressed(KeyEvent.VK_I)||
        handler.getKeyManager().keyJustPressed(KeyEvent.VK_PAGE_DOWN)){
            paused = !paused;
        }

        // Path();
        move();
        checkAttacks();

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
            if (e.equals(this.handler.getWorld().getEntityManager().getPlayer())) {
                this.turnBack();
                if (this.isMoving) {
                    this.pursuePlayer(this.handler.getWorld().getEntityManager().getPlayer());
                    attack_counter = 0;
                    count = false;
                    System.out.println("Follow");
                }
            }
            if (this.pursuitTimer >= 20) {
                this.isMoving = true;
                this.pursuitTimer = 0;
            }
        }


        //Animations
        zomDown.tick();
        zomUp.tick();
        zomRight.tick();
        zomLeft.tick();
    }

    public void checkAttacks(){
        System.out.println("Kill");
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();

        if(attackTimer < attackCooldown)
            return;

        Rectangle cb = getCollisionBounds(0, 0);
        //ar = attack rectangle
        Rectangle ar = new Rectangle();
        //if player is within 20 pixels of an entity, they will hit them
        int arSize = 50;
        ar.width = arSize*2;
        ar.height = arSize*2;

        if(yMove<0){
            //the x of the attack rectangle gets us the centre point of the collision rectangle.
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            System.out.println(ar.x);
            //right above the collision bound.
            ar.y = cb.y - arSize;
            System.out.println(ar.y);

        }else if(yMove>0){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            System.out.println(ar.x);
            //now it will be just below the collision bound.
            ar.y = cb.y + cb.height;
            System.out.println(ar.y);
            
        }else if(xMove<0){
            ar.x = cb.x - arSize;
            System.out.println(ar.x);
            //must change y to centre it.
            ar.y = cb.y + cb.height / 2 - arSize / 2;
            System.out.println(ar.y);
            
        }else if(xMove>0){
            ar.x = cb.x + cb.width;
            System.out.println(ar.x);
            ar.y = cb.y + cb.height / 2 - arSize / 2;
            System.out.println(ar.y);
        }else{
            //if none of the attack buttons are pressed and not attacking, don't run the rest of the code.
            return;
        }

        attackTimer = 0;

        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            //make sure entity isn't ourselves
            if(e.equals(this))
                continue;
            //below means we have hit that entity. We are hurting them with a value of 3.
            if(e.getCollisionBounds(0, 0).intersects(ar)){
                e.hurt(3);
                return;
            }
        }

    }

    private void turnBack() {
        count = true;
        this.xMove *= -1;
        this.yMove *= -1;
    }

    private void pursuePlayer(Player player) {
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
        //boss has a 75% chance of dropping an item
        int n = ThreadLocalRandom.current().nextInt(4);
        if (n != 0){
            handler.getWorld().getItemManager().addItem(Item.healthdrop.createNew((int) x, (int) y));
        }
        handler.getGame().incScore(50);
    }

    private BufferedImage getCurrentAnimationFrame(){
        if(xMove < 0){
            xAttacking = 1;
            return zomLeft.getCurrentFrame();
        }else if(xMove > 0){
            xAttacking = 2;
            return zomRight.getCurrentFrame();
        }else if(yMove < 0){
            yAttacking = 1;
            return zomUp.getCurrentFrame();
        } else {
            yAttacking= 2;
            return zomDown.getCurrentFrame();
        }
    }
}
