import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu extends JPanel implements MouseListener {

    boolean started = false;
   // public Rectangle playButton = new Rectangle(420,250,200,50);
    public Rectangle scoreButton = new Rectangle(515,350,80,35);
    public Rectangle creditsButton = new Rectangle(500,400,100,35);
  //  public Rectangle quitButton = new Rectangle(520,420,150,25);

    public Menu() {


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        System.out.println("Started");
        add(Box.createVerticalStrut(280));
        addMouseListener(this);

        //Test functionality of Button (4/11/2019) Move it around to fit on the background image

        //CustomButton button = new CustomButton("Single Player");

        //button.setAlignmentX(15);
       // button.setAlignmentY(20);

       // button.addActionListener(new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                start();
//
//            }
//
//        });
//        add(button);

//        try {
//            System.out.println("In try");
//
//            //Add the most recent pieces here
//           // URL url = Menu.class.getResource("magic-chime-01.wav");
//           // AudioInputStream audio = AudioSystem.getAudioInputStream(url);
//           // Clip music = AudioSystem.getClip();
//            music.open(audio);
//            music.loop(-1);
//
//        } catch (Exception ex) {
//             ex.printStackTrace();
//        }
    }

        public void start(){
            removeAll();
            started = true;
            repaint();
        }

       // public void render(Graphics g){
       // Graphics2D g2d = (Graphics2D) g;
       //     g2d.draw(playButton);
       //     g2d.draw(helpButton);
        //    g2d.draw(quitButton);
      //  }

        //could follow RealTutGML's tutorial for menu design, this following part is the only necessary part from WaffleStar
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            if (!started) {
                g.drawImage(new ImageIcon(Menu.class.getResource("title-screen.png")).getImage(), 0, 0, 640, 480, this);
                Graphics2D g2d = (Graphics2D) g;
                Font fnt1 = new Font("arial",Font.BOLD,15);
                g.setFont(fnt1);
                g.setColor(Color.WHITE);
               // g.drawString("Play",playButton.x+19,playButton.y+30);
               // g2d.draw(playButton);
                g.drawString("Score",scoreButton.x+19,scoreButton.y+30);
                g2d.draw(scoreButton);
                g.drawString("Credits",creditsButton.x+19,creditsButton.y+30);
                g2d.draw(creditsButton);

//                g.drawString("Quit",quitButton.x+19,quitButton.y+30);
//                g2d.draw(quitButton);


            }else{
            setBackground(Color.BLUE);
            }

        }

    @Override
    public void mouseClicked(MouseEvent e) {
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
    public void mousePressed(MouseEvent e) {



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
