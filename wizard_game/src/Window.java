import javax.swing.*;
import java.awt.*;

public class Window {
    //creates our game window / frame
    public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.add(game);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

   // public static void main(String[] args) {
        // TODO Auto-generated method stub
      ////  Game game = new Game();
      //  new Window(WIDTH, HEIGHT, "Wizard Top Down Shooter", game);
   // }

}