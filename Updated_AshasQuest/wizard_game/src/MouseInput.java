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
        if (mx >= 420 && mx <= 620){
            if (my >= 250 && my <= 300){
                //Pressed PlayButton
                Game.State  = Game.STATE.GAME;
            }
        }

        //QuitButton
        //Functionally the quit button is complete
        //For this reason, the rectangle for it is no longer necessary
        if (mx >= 520 && mx <= 620){
            if (my >= 420 && my <= 500){
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