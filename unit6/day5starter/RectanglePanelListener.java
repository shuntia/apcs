
import java.awt.Color;
import java.awt.event.*;
import javax.swing.Timer;

public class RectanglePanelListener implements MouseListener, MouseMotionListener, KeyListener, ActionListener {
	
	private RectanglePanel rectPanel;
	private Rectangle currentlyDraggingRectangle;
	private int offsetX, offsetY;		
	private boolean dragging; 
	
	public RectanglePanelListener(RectanglePanel panel) {
		rectPanel = panel; 
		rectPanel.addMouseListener(this);
		rectPanel.addMouseMotionListener(this); //***don't forget
		rectPanel.addKeyListener(this);
		Timer timer = new Timer(1000, this);
		timer.start();
		rectPanel.setFocusable(true);
		rectPanel.requestFocusInWindow();
		dragging = false; 
	}

	@Override 
	public void mouseClicked(MouseEvent ev) { //using mouseClicked not pressed b/c you want to create a rectangle
		int width = 30;						//only when not dragging 
		int height = 20;
		Color color;
		if (ev.isMetaDown()) {
			color = Color.BLUE;
		}
		else {
			color = Color.RED;
		}
		rectPanel.addRectangle(new Rectangle(ev.getX(), ev.getY(), width, height, color));
		rectPanel.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent ev) {  }

	@Override
	public void mouseExited(MouseEvent ev) { 
		currentlyDraggingRectangle = null;
	}

	@Override
	public void mousePressed(MouseEvent ev) { 
		int currentlyClickedX = ev.getX();
		int currentlyClickedY = ev.getY();
		
		Rectangle clickedRectangle = rectPanel.containsPoint(currentlyClickedX, currentlyClickedY);
		//will be null if you just clicked on white space, will be the latest-added rectangle if you clicked on a rectangle
		if(clickedRectangle == null) 
			return; //do nothing 
		else {
			dragging = true;
			currentlyDraggingRectangle = clickedRectangle; // This is how we'll keep track of the Rectangle as we drag it
			// *** Must save offsets on mouse press.  This is how we animate smoothly as we drag,
			// by maintaining that same difference in coordinates between Rectangles (x,y) and the actual
			// pressed position. 
			offsetX = ev.getX() - currentlyDraggingRectangle.getX(); // say x = 50, I click on (53, 130),  offsetX would be 3.
			offsetY = ev.getY() - currentlyDraggingRectangle.getY(); // say y = 120, I click on (53, 130), offsetY would be 10.
			// As I drag, I must continually remember the Rectangle's actual (x,y) upper left hand corner
			// is to the left and above where I actually pressed.
		}
		
	}


	public void mouseReleased(MouseEvent ev) { 
		dragging = false;
	}
	

	public void mouseDragged(MouseEvent ev) {
		if(!dragging || currentlyDraggingRectangle == null )  //this would be like if it registers as dragging, but you hadn't selected a rectangle
			return;
		// *** This is continuously called as I drag.  Again, Rectangle's actual (x,y) would be set left, and above based on the original
		// offset where you did the mouse press.
		currentlyDraggingRectangle.setX(ev.getX() - offsetX); 
		currentlyDraggingRectangle.setY(ev.getY() - offsetY);
		rectPanel.repaint();
			
	}

	public void mouseMoved(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		if(e.getKeyCode() == KeyEvent.VK_X) {
			rectPanel.rectangles.clear();
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			for(Rectangle r:rectPanel.rectangles){
				r.setX(r.getX()+10);
			}
		}
		rectPanel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
		for(Rectangle r:rectPanel.rectangles){
			r.setX(r.getX()-10);
		}
		rectPanel.repaint();
    }
}
