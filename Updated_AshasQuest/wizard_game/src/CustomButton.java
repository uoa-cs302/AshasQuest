import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomButton extends JButton implements MouseListener {
    //Size of the button
    Dimension size =  new Dimension(100,50);

    //Below keep track of how the button is being interacted with
    boolean hover = false;
    boolean click = false;

    String text = "";
    public CustomButton(String text) {
        setVisible(true);
        setFocusable(true);
        setContentAreaFilled(false);
        setBorderPainted(false);

        this.text = text;

        addMouseListener(this);
    }
    //Now it will always be under your control, matching dimension size
    public Dimension getPreferredSize(){
        return size;
    }

    public Dimension getMaximumSize(){
        return size;
    }

    public Dimension getMinimumSize(){
        return size;
    }

    public void setButtonText(String text){
        this.text = text;
    }

    public String getButtonText(){
        return text;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //this is what will occur if the button is clicked
        if (click){
            g.setColor(Color.WHITE);
            g.fillRect(0,0,100,100);
        }

        //if hovering over, 255 blue, otherwise blue is 180
        g.setColor(new Color(0,50,hover ? 255 :180));

        //Now we're drawing the borders
      //  g.fillRect(0,0,150,7);
       // g.fillRect(0,143,150,7);
        g.fillRect(0,0,100,100);
        g.fillRect(3,0,7,30);

        //This is the color that the inside of the button will look like. The "content" area.
        g.setColor(new Color(230,0,230));
        g.fillRect(10,16,85,122);

        //This below is the text itself
        g.setColor(Color.WHITE);

        //this is the font used in  the text
        g.setFont(Font.decode("arial-BOLD-12"));

        FontMetrics metrics = g.getFontMetrics();
        int width = metrics.stringWidth(text);

        //where is the text placed in the box
        g.drawString(text,53-width/2,35);

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        System.exit(1);
    //new Game();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        click = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        click = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        hover = true;

    }

    @Override
    public void mouseExited(MouseEvent e) {
        hover = false;
    }


}
