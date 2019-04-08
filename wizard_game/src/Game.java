import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{
    //our main class where everything gets called
    //our game loop, updating, characters, rendering etc.

    private static final long serialVersionUID = 1L;

    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private Camera camera;
    private SpriteSheet ss;

    private BufferedImage level = null;
    private BufferedImage sprite_sheet = null;
    private BufferedImage floor = null;

    public int ammo = 100;
    public int hp = 100;


    public Game(){
        new Window(1000,563,"Wizard Game",this);

        start();

        handler = new Handler();
        camera = new Camera(0,0);

        //this is crucial, as it tells the canvas of the game to listen to the input
        this.addKeyListener(new KeyInput(handler));

        //now to load the image
        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage("res/wizard_level.png");
        sprite_sheet = loader.loadImage("res/sprite_sheet.png");

        ss = new SpriteSheet(sprite_sheet);
        floor = ss.grabImage(4,2,32,32);
        //this is for the Magic Bullet
        this.addMouseListener(new MouseInput(handler,camera,this, ss));


        //handler.addObject(new Wizard(100,100,ID.Player,handler));

        loadLevel(level);

        //to test add objects in the game
        //  handler.addObject(new Box(100,100, ID.Block));

    }

    private void start(){
        isRunning = true;

        thread = new Thread(this);

        thread.start();

    }

    private void stop(){
        isRunning = false;

        try {

            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    //thread will always call this run method
    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountofTicks = 60.0;
        double ns = 1000000000/ amountofTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        int frames = 0;
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                //updates++;
                delta--;

            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
                //updates = 0
            }
        }
        stop();

    }

    public void tick(){
        //will update everything in our game. updates 60 times a second
        for (int i = 0; i<handler.object.size();i++) {
            if (handler.object.get(i).getID()==ID.Player){
                camera.tick(handler.object.get(i));
            }
        }
        handler.tick();


    }
    public void render(){
        //gets updated several thousand times a second. Renders everything in the game

        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null){
            //3 arguments to create the engine itself. Preloads frames behind the window.
            //could make it higher than 3, but it would slow things down.
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;

        //Everything between the two g2d.translates is getting translated into 2D graphics.

        //between the lines, you can draw things in the game
        ////////////////////////////////


        g2d.translate(-camera.getX(), -camera.getY());



        for (int xx = 0;xx <30*72; xx+= 32){
            for (int yy = 0; yy < 30*72; yy+=32){
                g.drawImage(floor,xx,yy,null);

            }
        }


        //below must go below background line always

        handler.render(g);

        g2d.translate(camera.getX(), camera.getY());
        g.setColor(Color.gray);
        g.fillRect(5,5,200,32);

        g.setColor(Color.green);
        g.fillRect(5,5,hp*2,32);

        g.setColor(Color.black);
        g.drawRect(5,5,200,32);

        g.setColor(Color.white);
        g.drawString("Ammo: " + ammo, 5,50);
        ///////////////////////////////

        g.dispose();
        bs.show();

    }

    //loading the level
    private void loadLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();

        for (int xx = 0; xx< w; xx++){
            for (int yy=0; yy<h;yy++){
                int pixel = image.getRGB(xx,yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue= (pixel) & 0xff;

                if(red == 255)
                    handler.addObject(new Block(xx*32, yy*32, ID.Block,ss));

                if(blue==255 && green ==0)
                    handler.addObject(new Wizard(xx*32, yy*32, ID.Player, handler, this,ss));
                if(green==255 && blue ==0)
                    handler.addObject(new Enemy(xx*32, yy*32, ID.Enemy, handler,ss));
                if(green==255 && blue ==255)
                    handler.addObject(new Crate(xx*32, yy*32, ID.Crate,ss));
            }
        }
    }

    public static void main(String args[]){
        new Game();

    }
}