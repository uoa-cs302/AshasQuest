import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ScoreScreen {

    private boolean exit_menu_active = false;
    private Handler handler;
    private String path;

    public ScoreScreen(Handler handler){
        this.handler = handler;
        path = handler.getGame().path;
    }

    public ArrayList<String[]> getScores(){
        ArrayList<String[]> scores = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] score = line.split(" ");
                scores.add(score);
            }
        }
        catch(IOException e) {}
        
        return scores;
    }

    public void tick(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_C))
          exit_menu_active = !exit_menu_active;
    }

    public void render(Graphics g) {
        if (!exit_menu_active)
            return;
        if (exit_menu_active){
            g.setColor(Color.black);
            g.fillRect(305, 307,380, 210);
            g.setColor(Color.LIGHT_GRAY);
            g.drawRect(305, 307, 380, 210);
            Font fnt3 = new Font("helvetica",Font.BOLD,20);
            g.setFont(fnt3);
            g.setColor(Color.white);
            g.drawString("Command List: " , 410, 327);
            Font fnt4 = new Font("arial",Font.BOLD,15);
            g.setFont(fnt4);
            g.setColor(Color.white);
            // Left column
            g.drawString("W: Move Up", 320, 357);
            g.drawString("A: Move Left", 320, 377);
            g.drawString("S: Move Down", 320, 397);
            g.drawString("D: Move Right", 320, 417);
            g.drawString("Q: Exit Game", 320, 437);
            g.drawString("M: Exit to Menu", 320, 457);
            g.drawString("I: Call Inventory", 320, 477);
            //Right column
            g.drawString("B: Use Alucard Shield",470, 357);
            g.drawString("P: Use Power Up",470,377);
            g.drawString("Down Arrow: Attack Down",470,397);
            g.drawString("Up Arrow: Attack Up", 470, 417);
            g.drawString("Left Arrow: Attack Left", 470, 437);
            g.drawString("Right Arrow: Attack Right", 470, 457);
            g.drawString("Space: Shadowpiercer Slash", 470, 477);
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