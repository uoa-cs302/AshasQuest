import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

    public int right_x = 940;
    public int height = 55;
    
    private Handler handler;

    public MouseInput(Handler handler){
        this.handler = handler;
    }

    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //PlayButton
        if (mx >= 670 && mx <= right_x){
            if (my >= 455 && my <= 455 + height){
                Game.States = Game.STATE.GAME;
                handler.getGame().changeSound("crawler");
            }
        }
        //Score button
        if (mx >= 810 && mx <= right_x){
            if (my >= 515 && my <= 515 + height){
                Game.States = Game.STATE.SCORE;
            }
        }
        //Credits button
        if (mx >= 790 && mx <= right_x){
            if (my >= 580 && my <= 580 + height){
                Game.States = Game.STATE.CREDITS;
            }
        }
        //QuitButton
        if (mx >= 835 && mx <= right_x){
            if (my >= 645 && my <= 645 + height){
                System.exit(1);
            }
        }
        //back button
        if (Game.States==Game.STATE.CREDITS || Game.States == Game.STATE.SCORE){
            if (mx>=200 && mx<=300){
                if (my>=500 && my<=550){
                    Game.States = Game.STATE.MENU;
                }
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