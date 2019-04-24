
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.io.File;


public class Game extends Canvas implements Runnable {



    private Display display;

    private int width, height;
    private World world;

    public String title;

    //private boolean exiting = false;



    private boolean running = false;

    private Thread thread;

    //States

    public enum STATE{
        MENU,GAME
    }
    public static Game.STATE States = Game.STATE.MENU;


    private BufferStrategy bs;

    private Graphics g;

    private Player player;



    //Input

    private KeyManager keyManager;

    private MouseManager mouseManager;

    private MouseInput mouseInput;

    private Menu menu;



    //Camera

    private GameCamera gameCamera;



    //Handler

    private Handler handler;



    public Game(String title, int width, int height){
        //Takes in the 3 variables that are passed to the Display and initialises the key and mouse manager.

        this.width = width;

        this.height = height;

        this.title = title;

        keyManager = new KeyManager();

        mouseManager = new MouseManager();

        mouseInput = new MouseInput();

        menu = new Menu();

        player = new Player(handler,0,0);


    }



    private void init(){
        //Everything we want to initialise in the game goes here. The frame is used because it has several
        //in built commands regarding adding Mouse and Key Listeners, as seen below.

        display = new Display(title, width, height);

        display.getFrame().addKeyListener(keyManager);

        display.getFrame().addMouseListener(mouseManager);

        display.getFrame().addMouseMotionListener(mouseManager);

        display.getCanvas().addMouseListener(mouseManager);

        display.getCanvas().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseInput);

        //Ensures that all sprites assigned to assets in the Assets class gets brought onto the Frame.

        Assets.init();




        handler = new Handler(this);

        gameCamera = new GameCamera(handler, 0, 0);



       // gameState = new GameState(handler);
        world = new World(handler, "../res/world1.txt");

        handler.setWorld(world);

      //  menuState = new MenuState(handler);

      //  State.setState(menuState);

    }



    private void tick() {
        //Updates everything

        keyManager.tick();


        //Below checks if we currently have a State that actually exists.
        if (States == STATE.GAME) {
            world.tick();

//            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q)){
//                //System.exit(1);
//                exiting = !exiting;
//            }
//
//            if (exiting&&handler.getKeyManager().keyJustPressed(KeyEvent.VK_Y)){
//                System.exit(1);
//            }
//
//            if (exiting&&handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)){
//                exiting=false;
//            }



            //if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))

            //    exit_menu_active = !exit_menu_active;

            //}
            //  if(State.getState() != null)
            //This calls the tick() method in whatever State we're currently in.

            //      State.getState().tick();

        }
    }

//    public boolean isExit_menu_active(){
//        while (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)){
//            return true;
//        }
//        return false;
//    }



    private void render(){
            //Draws everything

            //The Buffer Strategy is inside the Canvas of our display.

            //We get the Canvas of the current window, and this sets whatever the buffer strategy is to be the
            //same as our current one.

            bs = display.getCanvas().getBufferStrategy();

            //Buffer Strategy tells the computer how to draw things using buffers.

            //A Buffer is a "hidden computer screen"(just memory) within the computer. We draw everything to one buffer,
            //which moves to the next buffer, until it gets to the computer. This is to prevent flickering.

            //Below, if there isn't already a Buffer Strategy, we initiate one.
            if(bs == null){

                display.getCanvas().createBufferStrategy(3);

                return;

            }

            g = bs.getDrawGraphics();

            //Clear Screen

            g.clearRect(0, 0, width, height);

            //Draw Here!



            //If we currently have a real state in place, then call the render function inside whatever state we're in.
           //// if(State.getState() != null)

              //  State.getState().render(g);
        if (States==STATE.GAME) {
            world.render(g);
            Font fnt2 = new Font("arial",Font.BOLD,15);
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("HP: " , 5, 25);

            g.setColor(Color.green);
            g.fillRect(25, 10,player.getHealth()* 16, 20);

            g.setColor(Color.black);
            g.drawRect(25, 10, 160, 20);
            g.setColor(Color.WHITE);
            g.drawString("Press C for Commands",5,50);

//            if (exiting){
//                g.setColor(Color.black);
//                g.fillRect(305, 307,350, 70);
//
//                g.setColor(Color.LIGHT_GRAY);
//                g.drawRect(305, 307, 350, 70);
//
//                Font fnt3 = new Font("helvetica",Font.BOLD,20);
//                g.setFont(fnt3);
//                g.setColor(Color.white);
//                g.drawString("Are you sure you want to exit? " , 330, 327);
//                g.drawString("Y:Yes", 410, 357);
//                g.drawString("N:No", 490, 357);
//
//            }


////            if (exit_menu_active){
////
////
////              //  g.drawString("Press Q: Exit game", 100, 90);
////               // g.drawString("Press M: Return to Menu screen",150, 90);
////
////                g.setColor(Color.LIGHT_GRAY);
////                g.fillRect(305, 307,400, 200);
////
////                g.setColor(Color.black);
////                g.drawRect(305, 307, 400, 200);
////
////                Font fnt3 = new Font("arial",Font.BOLD,15);
////                g.setFont(fnt3);
////                g.setColor(Color.black);
////                g.drawString("Exit Menu: " , 465, 327);
//
//
//            }
        } else if (States==STATE.MENU){
            menu.paintComponent(g);


        }



            //End Drawing!

            bs.show();

            g.dispose();

    }



    public void run(){



        //Soon as the game runs, everything we have in the init method will begin.
        init();
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        //Most of the below steps are to implement a timer, maximising the speed at which the game is running and
        //to have the game run at the same speed on any machine that it's on.


        //fps = Frames Per Second. How many times each second we want tick and render to run.
        int fps = 60;

        //i billion /fps. As there are 1 billion nanoseconds in 1 second.

        //The below gets us the max amount of time we are allowed to run tick and render per second.

        double timePerTick = 1000000000 / fps;

        double delta = 0;


        long now;

        //System.nanoTime returns current time of the computer in nano seconds.

        long lastTime = System.nanoTime();

        long timer = 0;

        int ticks = 0;



        while(running){
            //Delta is added to. Now - last time will give the amount of time that has been passed
            //since this line of code was called. Divided by the amount of time we are allowed to call them.

            //Delta essentially tells the computer how much time there is until they have to call tick and render.

            now = System.nanoTime();

            delta += (now - lastTime) / timePerTick;

            timer += now - lastTime;

            //lastTime = currentTime again.

            lastTime = now;


            //this checks if we need to call tick or render yet or not.

            if(delta >= 1){

                tick();

                render();

                ticks++;

                delta--;

            }


            if(timer >= 1000000000){

                //Below just checks how many times tick and render are being called, to confirm the code works
                //This reset happens when the timer reaches 1 billion nanoseconds

               // System.out.println("Ticks and Frames: " + ticks);

                ticks = 0;

                timer = 0;

            }
            toolkit.sync();

        }

        stop();

    }



    public KeyManager getKeyManager(){

        return keyManager;

    }



    public MouseManager getMouseManager(){

        return mouseManager;

    }



    public GameCamera getGameCamera(){

        return gameCamera;

    }



    public int getWidth(){

        return width;

    }



    public int getHeight(){

        return height;

    }

//    public boolean isExit_menu_active() {
//
//        return exit_menu_active;
//
//    }



    public synchronized void start(){

        //When we call start, we need to check if the game is previously running.

        if(running)

            return;

        //the below steps will start a new thread and call the run method.
        running = true;

        thread = new Thread(this);

        thread.start();

    }



    public synchronized void stop(){

        if(!running)

            return;

        running = false;

        //We have to throw a try and catch statement around the thread joining, else an error is thrown.
        //Joining the thread is when the thread ends.

        try {

            thread.join();

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }

    public static void main(String[] args){

        Game game = new Game("Asha's Quest", 1024, 768);

        game.start();

    }



}