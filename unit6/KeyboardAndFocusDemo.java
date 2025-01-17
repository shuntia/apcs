
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;


/**
 * This program demonstrates Focus events and Key events.  A colored square
 * is drawn on the panel.  By pressing the arrow keys, the user can move
 * the square up, down, left, or right.  By pressing the keys
 * R, G, B, or K, the user can change the color of the square to red,
 * green, blue, or black, respectively. The panel changes appearance when 
 * it has the input focus; a cyan-colored border is drawn around it.  
 * When it does not have the input focus, the message "Click to Activate" 
 * is displayed and the border is gray.  The panel should have focus
 * whenever the program window is active.
 * This class contains a main() routine so that it can be run as a program
 */
public class KeyboardAndFocusDemo extends JPanel implements KeyListener, FocusListener{

    /**
     * The main program just opens a window that shows an object of type
     * KeyboardAndFocusDemo.  Note that it should request focus for the panel.
     * This has to be done after the window is made visible for it to have
     * any effect.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Keyboard and Focus Demo");
        KeyboardAndFocusDemo content = new KeyboardAndFocusDemo();
        window.setContentPane( content );
        window.setSize(400,400);
        window.setLocation(100,100);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setVisible(true);
        //***something needs to be done here
        content.addKeyListener(content);
        content.addFocusListener(content);
        content.setFocusable(true);
        window.setFocusable(true);
        window.requestFocus();
        content.requestFocusInWindow();
    }

    private final Square square = new Square(100, 100, 50, Color.RED);

    /**
     * The constructor sets the initial position and color of the square
     * and registers itself to act as a listener for Key, Focus, and 
     * Mouse events.
     */
    public KeyboardAndFocusDemo() {

        setBackground(Color.WHITE);


    } // end constructor
    /*
     * Draws a border, square, and message in the panel.  The message and
     * the color of  the border depend on whether or not the pane has
     * the input focus.
     */
    @Override
    public void paintComponent(Graphics g) {
                                  // background color, which is white.

        //*** Draw a 3-pixel border, colored cyan if the panel has the
              //*** keyboard focus, or in light gray if it does not. */
        if (hasFocus()) {
            g.setColor(Color.CYAN);
        }
        else {
            g.setColor(Color.LIGHT_GRAY);
        }
        int width = getSize().width;  // Width of the panel.
        int height = getSize().height; // Height of the panel.
        g.fillRect(0,0,width-1,height-1);
        g.setColor(Color.WHITE);
        g.fillRect(2,2,width-5,height-5);

        //*** Draw the square.

        square.draw(g);
        

        /* Print a message that depends on whether the panel has the focus. */

        g.setColor(Color.MAGENTA);
        if (hasFocus()) {
            g.drawString("Arrow Keys Move Square",7,20);
            g.drawString("K, R, G, B Change Color",7,40);
        }
        else
            g.drawString("Click to activate",7,20);

    }  // end paintComponent()

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_LEFT -> square.x -= 10;
            case KeyEvent.VK_RIGHT -> square.x += 10;
            case KeyEvent.VK_UP -> square.y -= 10;
            case KeyEvent.VK_DOWN -> square.y += 10;
            case KeyEvent.VK_R -> square.color = Color.RED;
            case KeyEvent.VK_G -> square.color = Color.GREEN;
            case KeyEvent.VK_B -> square.color = Color.BLUE;
            case KeyEvent.VK_K -> square.color = Color.BLACK;
            default -> {return;}
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
    }

    class Square{
        int x,y,length;
        Color color;
        public Square(int x, int y, int length, Color color){
            this.x = x;
            this.y = y;
            this.length = length;
            this.color = color;
        }
        public void draw(Graphics g){
            g.setColor(color);
            g.fillRect(x,y,length,length);
        }
    }
} // end class KeyboardAndFocusDemo
