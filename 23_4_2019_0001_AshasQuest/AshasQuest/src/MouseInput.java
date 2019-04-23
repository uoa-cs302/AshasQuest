import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
//       public Rectangle playButton = new Rectangle(WIDTH/2+120,150,100,50);
//        public Rectangle helpButton = new Rectangle(WIDTH/2+120,250,100,50);
//        public Rectangle quitButton = new Rectangle(WIDTH/2+120,350,100,50);
        //PlayButton
        if (mx >= 672 && mx <= 992){
            if (my >= 400 && my <= 480){
                //Pressed PlayButton
                Game.States  = Game.STATE.GAME;
            }
        }

        //QuitButton
        //Functionally the quit button is complete
        //For this reason, the rectangle for it is no longer necessary
        if (mx >= 832 && mx <= 992){
            if (my >= 672 && my <= 800){
                //Pressed QuitButton
                System.exit(1);
            }
        }



    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}