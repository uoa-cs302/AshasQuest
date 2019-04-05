import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.event.KeyEvent;

class AshasQuest implements KeyListener {
    public static final int WIDTH = 1400;
    public static final int HEIGHT = 900;

    private Window window;

    private void run() {
        JFrame frame = new JFrame("Asha's Quest");

        window = new Window();

        frame.add(window);

        frame.addKeyListener(this);

        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AshasQuest ashasQuest = new AshasQuest();
                ashasQuest.run();
            }
        });
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Invoked when a key has been typed.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Invoked when a key has been pressed.
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            window.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Invoked when a key has been released.
    }
}