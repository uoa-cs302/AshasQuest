import java.awt.*;
import java.awt.event.KeyEvent;

public class CommandList {
    private boolean exit_menu_active = false;
    private Handler handler;

    public CommandList(Handler handler){

        this.handler = handler;

       // inventoryItems = new ArrayList<Item>();

    }

    public void tick(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))

          exit_menu_active = !exit_menu_active;

        }



    public void render(Graphics g) {

        if (!exit_menu_active)

            return;
                    if (exit_menu_active){
//
//
              //  g.drawString("Press Q: Exit game", 100, 90);
               // g.drawString("Press M: Return to Menu screen",150, 90);

                g.setColor(Color.black);
                g.fillRect(305, 307,400, 280);

                g.setColor(Color.LIGHT_GRAY);
                g.drawRect(305, 307, 400, 280);

                Font fnt3 = new Font("helvetica",Font.BOLD,20);
                g.setFont(fnt3);
                g.setColor(Color.white);
                g.drawString("Command List: " , 440, 327);

                Font fnt4 = new Font("arial",Font.BOLD,15);
                g.setFont(fnt4);
                g.setColor(Color.white);
               // g.drawString("Command List: " , 460, 327);
                g.drawString("W: Move Up", 460, 347);
                g.drawString("A: Move Left", 460, 367);
                g.drawString("S: Move Down", 460, 387);
                g.drawString("D: Move Right", 460, 407);
                g.drawString("Q: Exit Game", 460, 427);
                g.drawString("M: Exit to Menu", 460, 447);
                g.drawString("I: Call Inventory", 460, 467);
                g.drawString("Up Arrow: Attack Up", 445, 487);
                g.drawString("Left Arrow: Attack Left", 435, 507);
                g.drawString("Right Arrow: Attack Right", 425, 527);
                g.drawString("Down Arrow: Attack Down", 425, 547);





            }
    }



    // GETTERS SETTERS

    public Handler getHandler() {

        return handler;

    }



    public void setHandler(Handler handler) {

        this.handler = handler;

    }



    public boolean isExit_menu_active() {

        return exit_menu_active;

    }

}