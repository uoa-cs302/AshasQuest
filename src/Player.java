import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class Player extends Creature {



    //Animations

    private Animation animDown, animUp, animLeft, animRight;

    private boolean exiting = false;

    // Attack timer, limits speed at which player can attack
    //wait 800 milliseconds to attack again

    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

    // Inventory

    private Inventory inventory;
    //private Game game;
    private CommandList commandList;



    public Player(Handler handler, float x, float y) {

        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        setHealth(10);


        //Below decides the dimensions for the player's collision box.

        bounds.x = 22;

        bounds.y = 44;

        bounds.width = 19;

        bounds.height = 19;



        //Animatons

        animDown = new Animation(500, Assets.player_down);

        animUp = new Animation(500, Assets.player_up);

        animLeft = new Animation(500, Assets.player_left);

        animRight = new Animation(500, Assets.player_right);

        //Animations for Asha when she's attacking



        inventory = new Inventory(handler);
     //  game = new Game("Ashas Quest", 1024, 768);
        commandList = new CommandList(handler);

    }



    @Override

    public void tick() {

        //Animations

        animDown.tick();

        animUp.tick();

        animRight.tick();

        animLeft.tick();

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

        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q)){
            //System.exit(1);
            exiting = !exiting;
        }

        if (exiting&&handler.getKeyManager().keyJustPressed(KeyEvent.VK_Y)){
            System.exit(1);
        }

        if (exiting&&handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)){
            exiting=false;
        }

    }



    private void checkAttacks(){

        //time elapsed between now and the previous attack

        attackTimer += System.currentTimeMillis() - lastAttackTimer;

        lastAttackTimer = System.currentTimeMillis();

        //checking if we are able to attack

        if(attackTimer < attackCooldown)

            return;



        if(inventory.isActive() || commandList.isExit_menu_active()||exiting)

            return;



        Rectangle cb = getCollisionBounds(0, 0);

        //ar = attack rectangle

        Rectangle ar = new Rectangle();

        //if player is within 20 pixels of an entity, they will hit them

        int arSize = 20;

        ar.width = arSize;

        ar.height = arSize;



        //handler.getKeyManager() checks which direction the player wants to attack in
        //So this will check where to draw the attack rectangle
        if(handler.getKeyManager().aUp){

            //the x of the attack rectangle gets us the centre point of the collision rectangle.

            ar.x = cb.x + cb.width / 2 - arSize / 2;

            //right above the collision bound.
            ar.y = cb.y - arSize;


        }else if(handler.getKeyManager().aDown){

            ar.x = cb.x + cb.width / 2 - arSize / 2;

            //now it will be just below the collision bound.
            ar.y = cb.y + cb.height;

        }else if(handler.getKeyManager().aLeft){

            ar.x = cb.x - arSize;

            //must change y to centre it.
            ar.y = cb.y + cb.height / 2 - arSize / 2;

        }else if(handler.getKeyManager().aRight){

            ar.x = cb.x + cb.width;

            ar.y = cb.y + cb.height / 2 - arSize / 2;

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



        if(inventory.isActive()||commandList.isExit_menu_active()||exiting)

            return;



        if(handler.getKeyManager().up)

            yMove = -speed;

        if(handler.getKeyManager().down)

            yMove = speed;

        if(handler.getKeyManager().left)

            xMove = -speed;

        if(handler.getKeyManager().right)

            xMove = speed;

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

    }



    private BufferedImage getCurrentAnimationFrame(){

        if(xMove < 0){

            return animLeft.getCurrentFrame();

        }else if(xMove > 0){

            return animRight.getCurrentFrame();

        }else if(yMove < 0){

            return animUp.getCurrentFrame();

        }else{

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