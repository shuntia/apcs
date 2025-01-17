import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


public class SimplePaint extends JPanel implements MouseListener, MouseMotionListener {
    class Stroke implements Drawable{
        ArrayList<Point> points = new ArrayList<>();
        Color color;
        public void draw(Graphics g){
            for(int i=0;i<points.size()-1;i++){
                g.setColor(color);
                g.drawLine(points.get(i).x, points.get(i).y, points.get(i+1).x, points.get(i+1).y);
            }
        }
        public Stroke(int color){
            switch(color){
                case BLACK -> this.color=Color.BLACK;
                case RED -> this.color=Color.RED;
                case GREEN -> this.color=Color.GREEN;
                case BLUE -> this.color=Color.BLUE;
                case CYAN -> this.color=Color.CYAN;
                case MAGENTA -> this.color=Color.MAGENTA;
                case YELLOW -> this.color=Color.YELLOW;
            }
        }
        public Stroke(){
            this.color=Color.BLACK;
        }
        public void add(Point p){
            points.add(p);
        }
        public void add(int x, int y){
            points.add(new Point(x,y));
        }
    }
    /**
     * This main routine allows this class to be run as a program.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Simple Paint");
        SimplePaint content = new SimplePaint();
        window.setContentPane(content);
        window.setSize(700,380);
        window.setLocation(100,100);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setVisible(true);
    }

    /**
     * Some constants to represent the color selected by the user.
     */
    private final static int BLACK = 0,
            RED = 1,     
            GREEN = 2,   
            BLUE = 3, 
            CYAN = 4,   
            MAGENTA = 5,
            YELLOW = 6;

    private int currentColor = BLACK;  // The currently selected drawing color,
                                       //   coded as one of the above constants.

    /* The following variables are used when the user is sketching a
         curve while dragging a mouse. */

    private int prevX, prevY;     // The previous location of the mouse.
    private boolean dragging;      // This is set to true while the user is drawing.

    // *** Let's make a nested class to define a new data type that will be stored in a data structure
    // e.g. (not a Rectangle[] rectangles)
    ArrayList<Stroke> pixels = new ArrayList<>();
    Stroke currentStroke = null;
    /**
     * Constructor for SimplePaintPanel class sets the background color to be
     * white and sets it to listen for mouse events on itself.
     */
    SimplePaint() {
        this.setBackground(Color.WHITE);
        //*** Since the SimplePaint JPanel is also a Listener, register appropriately

                // *** Ignore the video, should addMouseMotionListener here    
        this.addMouseListener(this);
        this.addMouseMotionListener(this);    
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Fill with background color (white).

        int width = getWidth();    // Width of the panel.
        int height = getHeight();  // Height of the panel.

        int colorSpacing = (height - 56) / 7;

        g.setColor(Color.GRAY);
        g.drawRect(0, 0, width-1, height-1);  // one rectangle for each pixel 
        g.drawRect(1, 1, width-3, height-3);
        g.drawRect(2, 2, width-5, height-5);

        /* Draw a 56-pixel wide gray rectangle along the right edge of the panel.
             The color palette and Clear button will be drawn on top of this.
             (This covers some of the same area as the border I just drew. */

        g.fillRect(width - 56, 0, 56, height);

        /* Draw the "Clear button" as a 50-by-50 white rectangle in the lower right
             corner of the panel, allowing for a 3-pixel border. */

        g.setColor(Color.WHITE);
        g.fillRect(width-53,  height-53, 50, 50);
        g.setColor(Color.BLACK);
        g.drawRect(width-53, height-53, 49, 49);
        g.drawString("CLEAR", width-48, height-23); 

        /* Draw the seven color rectangles. */

        g.setColor(Color.BLACK);
        g.fillRect(width-53, 3 + 0*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.RED);
        g.fillRect(width-53, 3 + 1*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.GREEN);
        g.fillRect(width-53, 3 + 2*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.BLUE);
        g.fillRect(width-53, 3 + 3*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.CYAN);
        g.fillRect(width-53, 3 + 4*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.MAGENTA);
        g.fillRect(width-53, 3 + 5*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.YELLOW);
        g.fillRect(width-53, 3 + 6*colorSpacing, 50, colorSpacing-3);

        /* Draw a 2-pixel white border around the color rectangle
             of the current drawing color. */

        g.setColor(Color.WHITE);
        g.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing);
        g.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2);
 
        
        // *** Re-draw all of the information, based on the state of our data structure
        
        for(Stroke s:pixels){
            s.draw(g);
        }
        
    } // end paintComponent()


    private void changeColor(int y) {
        int width = getWidth();           // Width of panel.
        int height = getHeight();         // Height of panel.
        int colorSpacing = (height - 56) / 7;  // Space for one color rectangle.
        int newColor = y / colorSpacing;       // Which color number was clicked?
        System.out.println("y: " + y + " colorSpacing: " + colorSpacing + " newColor: " + newColor);

        if (newColor < 0 || newColor > 6)      // Make sure the color number is valid.
            return;

        currentColor = newColor;
        repaint();
    } 


    /**
     * This is called when the user presses the mouse anywhere in the panel.  
     * There are three possible responses, depending on where the user clicked:  
     * Change the current color, clear the drawing, or start drawing a curve.  
     * (Or do nothing if user clicks on the border.)
     */
    public void mousePressed(MouseEvent evt) {
        int x = evt.getX();   // x-coordinate where the user clicked.
        int y = evt.getY();   // y-coordinate where the user clicked.

        int width = getWidth();    // Width of the panel.
        int height = getHeight();  // Height of the panel.

        if (currentStroke!=null)return;           //    when user is already drawing a curve.
                               //    (This can happen if the user presses
                               //    two mouse buttons at the same time.)
									//***like left button is down+dragging but you click the right button
        if (x > width - 53) {
            if (y > height - 53) {
            			//  ***Clicked on "CLEAR button".
                      pixels.clear();  // Remove all rectangles from the list.
            }
            else {
                changeColor(y);  // Clicked on the color palette.
                 				// ***update the highlighted square of color
            }
        }
        else if (x > 3 && x < width - 56 && y > 3 && y < height - 3) {
                // The user has clicked on the white drawing area.
                // Start drawing a curve from the point (x,y).
            currentStroke = new Stroke(currentColor);
        	pixels.add(currentStroke);
        }
        repaint();

    } // end mousePressed()


    /**
     * Called whenever the user releases the mouse button. If the user was drawing 
     * a curve, the curve is done, so we should set dragging to false
     */
    public void mouseReleased(MouseEvent evt) {
        if (currentStroke != null) {
            currentStroke = null;
        }
    }


    /**
     * Called whenever the user moves the mouse while a mouse button is held down.  
     * If the user is drawing, draw a line segment from the previous mouse location 
     * to the current mouse location, and set up prevX and prevY for the next call.  
     * Note that in case the user drags outside of the drawing area, the values of
     * x and y are "clamped" to lie within this area.  This avoids drawing on the color 
     * palette or clear button.
     */
    public void mouseDragged(MouseEvent evt) {
        if (currentStroke == null) {
            return;
        }

        int x = evt.getX();   // x-coordinate of mouse.
        int y = evt.getY();   // y-coordinate of mouse.

        if (x < 3)                          // Adjust the value of x,
            x = 3;                           //   to make sure it's in
        if (x > getWidth() - 57)       //   the drawing area.
            x = getWidth() - 57;

        if (y < 3)                          // Adjust the value of y,
            y = 3;                           //   to make sure it's in
        if (y > getHeight() - 4)       //   the drawing area.
            y = getHeight() - 4;

        // *** update our data structure to reflect the new state as the user is dragging
        // Remember, NO DRAWING here!
        currentStroke.add(x,y);
        
        repaint();
    } // end mouseDragged()


    public void mouseEntered(MouseEvent evt) { }   // Some empty routines.
    public void mouseExited(MouseEvent evt) { }    //    (Required by the MouseListener
    public void mouseClicked(MouseEvent evt) { }   //    and MouseMotionListener
    public void mouseMoved(MouseEvent evt) { }     //    interfaces).



} // end class SimplePaint
