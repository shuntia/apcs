import java.awt.event.*;

public class SimplePaintListener implements MouseListener, MouseMotionListener {
    SimplePaintPanel panel;
    private SimplePaintPanel.Stroke currentStroke; // The current line being drawn (if the user is drawing).
    private int prevX, prevY;     // The previous location of the mouse.
    private boolean dragging;      // This is set to true while the user is drawing.
    public SimplePaintListener(SimplePaintPanel panel) {
        this.panel = panel;
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
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
        
     //   System.out.println(x); 
     //   System.out.println(y);

        int width = panel.getWidth();    // Width of the panel.
        int height = panel.getHeight();  // Height of the panel.

        if (dragging == true)  // Ignore mouse presses that occur
            return;            //    when user is already drawing a curve.
                               //    (This can happen if the user presses
                               //    two mouse buttons at the same time.)
									//***like left button is down+dragging but you click the right button
        if (x > width - 53) {
            if (y > height - 53) {
            			//  ***Clicked on "CLEAR button".
            	// ***Just point to a new ArrayList<Line>().  
            	// The old one containing all the lines will be garbage collected
            		panel.lines.clear();
            		panel.repaint(); 
            }
            else {
                panel.changeColor(y);  // Clicked on the color palette.                 				
                panel.repaint(); // ***update the highlighted square of color
            }
        }
        else if (x > 3 && x < width - 56 && y > 3 && y < height - 3) {
                // The user has clicked on the white drawing area.
                // Start drawing a curve from the point (x,y).
        		//*** save the starting x, y for the first Line to be added 
            prevX = x;  
            prevY = y; 
            dragging = true; 
            
        currentStroke = panel.newStroke(panel.getcurrentColor());
        currentStroke.addPoint(x, y);
        panel.lines.add(currentStroke);
        }

    } // end mousePressed() 


    /**
     * Called whenever the user releases the mouse button. If the user was drawing 
     * a curve, the curve is done, so we should set dragging to false
     */
    public void mouseReleased(MouseEvent evt) {
        if (dragging == false)
        		return; // Nothing to do because the user isn't drawing.
        dragging = false;
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
        if (dragging == false)
            return;  // Nothing to do because the user isn't drawing.

        int x = evt.getX();   // x-coordinate of mouse.
        int y = evt.getY();   // y-coordinate of mouse.

        if (x < 3)                          // Adjust the value of x,
            x = 3;                           //   to make sure it's in
        if (x > panel.getWidth() - 57)       //   the drawing area.
            x = panel.getWidth() - 57;

        if (y < 3)                          // Adjust the value of y,
            y = 3;                           //   to make sure it's in
        if (y > panel.getHeight() - 4)       //   the drawing area.
            y = panel.getHeight() - 4;

        // *** update our data structure to reflect the new state as the user is dragging
        // Remember, NO DRAWING here!
        prevX = x; // the old current X will become the new prevX for the next Line to be added
        prevY = y; // the old current Y will become the new prevY for the next Line to be added
        currentStroke.addPoint(x, y);
        panel.repaint();
    } // end mouseDragged() 


    public void mouseEntered(MouseEvent evt) { }   // Some empty routines.
    public void mouseExited(MouseEvent evt) { }    //    (Required by the MouseListener
    public void mouseClicked(MouseEvent evt) { }   //    and MouseMotionListener
    public void mouseMoved(MouseEvent evt) { }     //    interfaces).


}
