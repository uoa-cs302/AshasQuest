import javax.swing.*;
import javax.swing.Timer;
import java.awt.Rectangle;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.Graphics;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.io.IOException;

public class Window extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
    private List<Tile> tiles;
    private Boolean start;
    public String map_name = "maps/";
    public int room;

    public Window() {

        room = 1;
        if (room == 0) {
            map_name += "map0.txt";
        }
        if (room == 1) {
            map_name += "map1.txt";
        }
        if (room == 2) {
            map_name += "map2.txt";
        }
        if (room == 5) {
            map_name += "map5.txt";
        }
        if (room == 8) {
            map_name += "map8.txt";
        }
        if (room == 3 || room == 6 || room == 9) {
            map_name += "mapHoldingRoom.txt";
        }
        if (room == 4 || room == 7) {
            map_name += "mapBoss.txt";
        }
        if (room == 10) {
            map_name += "mapFinalBoss.txt";
        }
        if (room == 11) {
            map_name += "mapFinalRoom.txt";
        }
        
        tiles = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(map_name));
            String line;
            int y = 0;
            while ((line = reader.readLine()) != null) {
                String[] tile_types = line.split(" ");
                for (int x = 0; x < tile_types.length; x++) {
                    tiles.add(new Tile(x * Tile.WIDTH, y, room, tile_types[x]));
                }
                y += Tile.HEIGHT;
            }
        }
        catch(IOException e) {}
    }

    public void start() {
        addMouseListener(this);
        addMouseMotionListener(this);

        Timer timer = new Timer(1, this);
        timer.start();
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        start = true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (Iterator<Tile> iter = tiles.listIterator(); iter.hasNext();) {
            Tile tile = iter.next();
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Tile tile : tiles) {
            tile.draw(g);
        }
    }
}