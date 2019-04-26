import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    //Animations
    private Animation animDown, animUp, animLeft, animRight;
    public Animation anim_attack_down, anim_attack_up, anim_attack_left,anim_attack_right;
    public boolean exiting = false;
    public boolean return_to_menu = false;
    public boolean boss_ready = false;

    // Attack timer, limits speed at which player can attack
    //wait 800 milliseconds to attack again
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

    // Inventory
    private Inventory inventory;

    //private Game game;
    private CommandList commandList;

    public boolean paused = false;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        setHealth(10);

        //Below decides the dimensions for the player's collision box.
        bounds.x = 22;
        bounds.y = 44;
        bounds.width = 19;
        bounds.height = 19;

        //Animatons
        animDown = new Animation(300, Assets.player_down);
        animUp = new Animation(300, Assets.player_up);
        animLeft = new Animation(300, Assets.player_left);
        animRight = new Animation(300, Assets.player_right);
        //Animations for Asha when she's attacking
        anim_attack_up = new Animation(200, Assets.attack_up);
        anim_attack_down = new Animation(200, Assets.attack_down);
        anim_attack_left = new Animation(200, Assets.attack_left);
        anim_attack_right = new Animation(200, Assets.attack_right);

        inventory = new Inventory(handler);
     //  game = new Game("Ashas Quest", 1024, 768);
        commandList = new CommandList(handler);
    }

    public boolean reachedRight(){
        if (this.getX() + bounds.width >= World.getWidth() * Tile.TILEWIDTH - Tile.TILEWIDTH / 2){
            return true;
        }
        return false;
    }
    public boolean reachedLeft(){
        if (this.getX() <= 1){
            return true;
        }
        return false;
    }
    public boolean reachedTop(){
        if (this.getY() <= 1){
            return true;
        }
        return false;
    }
    public boolean reachedBottom(){
        if (this.getY() + bounds.height >= World.getHeight() * Tile.TILEHEIGHT - Tile.TILEHEIGHT){
            return true;
        }
        return false;
    }

    @Override
    public void tick() {
        //Animations
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();

        //Attack Animations
        anim_attack_down.tick();
        anim_attack_up.tick();
        anim_attack_right.tick();
        anim_attack_left.tick();
        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        // Attack
        checkAttacks();
        // Inventory
        inventory.tick();
        //Command List
        commandList.tick();
        //Exit Menu
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)){
            //System.exit(1);
            exiting = !exiting;
        }
        if (exiting&&handler.getKeyManager().keyJustPressed(KeyEvent.VK_Y)){
            System.exit(1);
        }
        if (exiting&&handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)){
            exiting=false;
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_M)){
            return_to_menu = !return_to_menu;
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)){
            paused= !paused;
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_PAGE_DOWN)){
            boss_ready = !boss_ready;
        }
        if (boss_ready &&((handler.getKeyManager().keyJustPressed(KeyEvent.VK_Y))||(handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)))){
            boss_ready = false;
        }
        if (return_to_menu&&handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)){
            return_to_menu=false;
        }
        if (return_to_menu&&handler.getKeyManager().keyJustPressed(KeyEvent.VK_Y)){
            Game.States  = Game.STATE.MENU;
        }


    }

    private void checkAttacks(){
        //time elapsed between now and the previous attack
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        //checking if we are able to attack
        if(attackTimer < attackCooldown)
            return;

        if(inventory.isActive() || commandList.isExit_menu_active()||exiting||return_to_menu||paused||boss_ready)
            return;

        Rectangle cb = getCollisionBounds(0, 0);
        //ar = attack rectangle
        Rectangle ar = new Rectangle();
        //if player is within 20 pixels of an entity, they will hit them
        int arSize = 50;
        ar.width = arSize;
        ar.height = arSize;

        //handler.getKeyManager() checks which direction the player wants to attack in
        //So this will check where to draw the attack rectangle
        if(handler.getKeyManager().aUp){
            //the x of the attack rectangle gets us the centre point of the collision rectangle.
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            //right above the collision bound.
            ar.y = cb.y - arSize;
            xAttacking = 1;

        }else if(handler.getKeyManager().aDown){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            //now it will be just below the collision bound.
            ar.y = cb.y + cb.height;
            yAttacking= 2;
        }else if(handler.getKeyManager().aLeft){
            ar.x = cb.x - arSize;
            //must change y to centre it.
            ar.y = cb.y + cb.height / 2 - arSize / 2;
            xAttacking =1;
        }else if(handler.getKeyManager().aRight){
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
            xAttacking = 2;
        }else{
            //if none of the attack buttons are pressed and not attacking, don't run the rest of the code.
            return;
        }

        attackTimer = 0;

        //for every entity in the world.
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            //make sure entity isn't ourselves
            if(e.equals(this))
                continue;
            //below means we have hit that entity. We are hurting them with a value of 1.
            if(e.getCollisionBounds(0, 0).intersects(ar)){
                e.hurt(1);
                return;
            }
        }
    }

    @Override
    public void die(){
        System.out.println("You lose");
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;
        yAttacking = 0;
        xAttacking = 0;

        if(inventory.isActive()||commandList.isExit_menu_active()||exiting||return_to_menu||paused||boss_ready)
            return;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;

        if(handler.getKeyManager().aUp)
            yAttacking = 1;
        if(handler.getKeyManager().aDown)
            yAttacking = 2;
        if(handler.getKeyManager().aLeft)
            xAttacking = 1;
        if(handler.getKeyManager().aRight)
            xAttacking = 2;



    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

    }

    public void postRender(Graphics g){
        inventory.render(g);
        commandList.render(g);
        if (exiting){
            g.setColor(Color.black);
            g.fillRect(305, 307,350, 70);
            g.setColor(Color.LIGHT_GRAY);
            g.drawRect(305, 307, 350, 70);
            Font fnt3 = new Font("helvetica",Font.BOLD,20);
            g.setFont(fnt3);
            g.setColor(Color.white);
            g.drawString("Are you sure you want to exit? " , 330, 327);
            g.drawString("Y:Yes", 410, 357);
            g.drawString("N:No", 490, 357);
        }
        if (return_to_menu){
            g.setColor(Color.black);
            g.fillRect(305, 307,400, 100);
            g.setColor(Color.LIGHT_GRAY);
            g.drawRect(305, 307, 400, 100);
            Font fnt3 = new Font("helvetica",Font.BOLD,20);
            g.setFont(fnt3);
            g.setColor(Color.white);
            g.drawString("Are you sure you want to return to " , 340, 327);
            g.drawString("the title screen?", 420,357);
            g.drawString("Y:Yes", 440, 387);
            g.drawString("N:No", 520, 387);
        }
    }

    private BufferedImage getCurrentAnimationFrame(){
        if(xMove < 0){
            return animLeft.getCurrentFrame();
        }else if(xMove > 0){
            return animRight.getCurrentFrame();
        }else if(yMove < 0){
            return animUp.getCurrentFrame();
      //  }else if (yMove > 0){
       //     return animDown.getCurrentFrame();
        } else if (xAttacking == 1){
            return anim_attack_left.getCurrentFrame();
        } else if (xAttacking==2){
            return anim_attack_right.getCurrentFrame();
        } else if (yAttacking == 1){
            return anim_attack_up.getCurrentFrame();
        } else if (yAttacking == 2){
            return anim_attack_down.getCurrentFrame();
        } else {
            return animDown.getCurrentFrame();
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
    public CommandList getCommandList(){
        return commandList;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    public void setCommandList(CommandList commandList){
        this.commandList = commandList;
    }

}