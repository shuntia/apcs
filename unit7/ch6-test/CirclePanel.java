// *** Your name: 
import java.awt.Graphics;
import java.util.Arrays;
import javax.swing.JPanel;

public class CirclePanel extends JPanel {
	
	private int numCircles = 0;
	private Circle[] circles = new Circle[200];
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < numCircles; i++) {
		    circles[i].draw(g);
		}
	}
	
	public void addCircle(Circle Circle) {
	    if (numCircles == circles.length || Circle == null)
	        return;
	    circles[numCircles] = Circle;
	    numCircles++;
	}
	
	public Circle containsPoint(int x, int y) {
		for (int i = numCircles - 1; i >= 0; i--) {
		    if (circles[i].containsPoint(x, y))
		    		return circles[i];
		}
		return null;
	}
	
	public Circle[] getCircles() {
		return Arrays.copyOf(circles, numCircles);
	}

	public Circle clickedCircles(int x, int y){
		for(int i=numCircles-1;i>=0;i--){
			Circle c=circles[i];
			if(c.containsPoint(x, y))return c;
		}
		return null;
	}
	
	public int getNumCircles() {
		return numCircles;
	}
	
	public void removeMostRecentCircle() {
		if(numCircles==0)return;
		circles[numCircles-1]=null;
		numCircles--;
		repaint();
	}
	
	public void removeAllCircles() {
		numCircles=0;
		for(int i=0;i<numCircles;i++)
			circles[i]=null;
		repaint();
	}

}
