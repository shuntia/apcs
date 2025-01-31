import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


public class SimplePaintPanel extends JPanel {

    /**
     * This main routine allows this class to be run as a program.
     */

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
    public static final Color intoColor(int colorCode) {
        Color ret;
    	switch(colorCode) {
            case BLACK -> ret = Color.WHITE;
            case RED -> ret = Color.RED;
            case GREEN -> ret = Color.GREEN;
            case BLUE -> ret = Color.BLUE;
            case CYAN -> ret = Color.CYAN;
            case MAGENTA -> ret = Color.MAGENTA;
            case YELLOW -> ret = Color.YELLOW;
            default -> ret = Color.BLACK;
        }
        return ret;
    }
    private Color currentColor = intoColor(BLACK);  // The currently selected drawing color,
                                       //   coded as one of the above constants.
    public Color getcurrentColor() {
    	return currentColor;
    }
    /* The following variables are used when the user is sketching a
         curve while dragging a mouse. */

    // *** Let's make a nested class to define a new data type that will be stored in a data structure
    // e.g. (not a Rectangle[] rectangles)
    public Line createLine(int x1, int x2, int y1, int y2, int colorCode) {
    	return new Line(x1, x2, y1, y2, colorCode);
    }
    public class Line {
    	private int x1, x2, y1, y2; 
    	private int colorCode;
    	
		public Line(int x1, int x2, int y1, int y2, int colorCode) {
			super();
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
			this.colorCode = colorCode;
		} 
    }

    public class Point{
        public int x, y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public class Stroke{
        public ArrayList<Point> points = new ArrayList<Point>();
        public Color color;
        public Stroke(int colorCode){
            this.color = intoColor(colorCode);
        }
        public Stroke(Color c){
            this.color = c;
        }
        public void draw(Graphics g){
            g.setColor(color);
            for(int i = 1; i < points.size(); i++){
                Point p1 = points.get(i-1);
                Point p2 = points.get(i);
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
        public void addPoint(int x, int y){
            points.add(new Point(x, y));
        }
    }

    public Stroke newStroke(int colorCode){
        return new Stroke(colorCode);
    }
    public Stroke newStroke(Color c){
        return new Stroke(c);
    }
    
    public ArrayList<Stroke> lines = new ArrayList<Stroke>(); 
    
    // lines.size() --> lines.length
    // lines.get(i) --> lines[i]
    // lines.add(new Line(blah)) --> lines[i] = new Line(blah) 

    /**
     * Constructor for SimplePaintPanel class sets the background color to be
     * white and sets it to listen for mouse events on itself.
     */
    class ColorButton extends JButton{
        public final int col;
        ColorButton(int col){
            this.col = col;
        }
    }
    ColorButton[] colorButtons = new ColorButton[7];
    JButton clearButton;
    JButton customColor;
    JPanel colorPanel;
    SimplePaintPanel() {
        this.requestFocus();
        this.requestFocusInWindow();
        colorPanel= new JPanel();
        colorPanel.setLayout(new GridLayout(9,1));
        for(int i = 0; i < 7; i++) {
            colorButtons[i] = new ColorButton(i);
            colorButtons[i].setBackground(intoColor(i));
            colorButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    currentColor = intoColor(((ColorButton)e.getSource()).col);
                    repaint();
                }
            });
            colorPanel.add(colorButtons[i]);
        }
        customColor = new JButton("CUSTOM");
        customColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color newColor = JColorChooser.showDialog(SimplePaintPanel.this, "Choose Custom Color", currentColor);
                if(newColor != null) {
                    currentColor = newColor;
                    repaint();
                }
            }
        });
        colorPanel.add(customColor);
        clearButton = new JButton("CLEAR");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lines.clear();
                repaint();
            }
        });
        colorPanel.add(clearButton);
        this.setLayout(new BorderLayout());
        this.add(colorPanel, BorderLayout.EAST);
        this.setBackground(Color.BLACK);
        new SimplePaintListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Fill with background color (white).

        int width = getWidth();    // Width of the panel.
        int height = getHeight();  // Height of the panel.
        // int colorSpacing = (height - 56) / 7; // Removed unused variable
        int colorSpacing = (height - 56) / 7;
        g.setColor(Color.GRAY);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));

        /* Draw the "Clear button" as a 50-by-50 white rectangle in the lower right
             corner of the panel, allowing for a 3-pixel border. */
        /*
        g.setColor(Color.WHITE);
        g.fillRect(width-53,  height-53, 50, 50);
        g.setColor(Color.BLACK);
        g.drawRect(width-53, height-53, 49, 49);
        g.drawString("CLEAR", width-48, height-23); 

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
        */
        /* Draw a 2-pixel white border around the color rectangle
             of the current drawing color. */
        
             
        // *** Re-draw all of the information, based on the state of our data structure
        for(Stroke s:lines){
            s.draw(g);
        }
        /*
        for (int i = 0; i < lines.size(); i++) {
            switch(lines.get(i).colorCode) {
                case BLACK -> g.setColor(Color.BLACK);
                case RED -> g.setColor(Color.RED);
                case GREEN -> g.setColor(Color.GREEN);
                case BLUE -> g.setColor(Color.BLUE);
                case CYAN -> g.setColor(Color.CYAN);
                case MAGENTA -> g.setColor(Color.MAGENTA);
                case YELLOW -> g.setColor(Color.YELLOW); 
            }
            g.drawLine(lines.get(i).x1, lines.get(i).y1, 
            lines.get(i).x2, lines.get(i).y2);
        }
        */
        colorPanel.repaint();
    } // end paintComponent()


    public void changeColor(int y) {
        int width = getWidth();           // Width of panel.
        int height = getHeight();         // Height of panel.
        int colorSpacing = (height - 56) / 7;  // Space for one color rectangle.
        int newColor = y / colorSpacing;       // Which color number was clicked?
       // System.out.println("y: " + y + " colorSpacing: " + colorSpacing + " newColor: " + newColor);

        if (newColor < 0 || newColor > 6)      // Make sure the color number is valid.
            return;

        currentColor = intoColor(newColor);  // Change the drawing color.
    } 

    public void undo() {
        if (!lines.isEmpty()) {
            lines.get(lines.size() - 1).points.remove(lines.get(lines.size() - 1).points.size() - 1);
        }
        repaint();
    }
    public void undoStroke() {
        if (!lines.isEmpty()) {
            lines.remove(lines.size() - 1);
        }
        repaint();
    }
} // end class SimplePaint
