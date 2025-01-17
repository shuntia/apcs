
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class RectanglePanel extends JPanel {
	
	private int numRectangles = 0;
	ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();

	public RectanglePanel() {
		super();
		Thread interval=new Thread(() -> {
                    while(true){
                        try{
                            Thread.sleep(1000);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                        for(Rectangle r:rectangles){
                            r.setX(r.getX()-10);
                        }
                        repaint();
                    }
                });
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("press x to delete, right arrow to move", 10, 10);
		for (Rectangle rectangle : rectangles) {
			rectangle.draw(g);
		}
	}

	public void addRectangle(Rectangle rectangle) {
		if (rectangle == null)
			return;
		rectangles.add(rectangle);
		numRectangles++;
	}

	public Rectangle containsPoint(int x, int y) {
		for (int i = numRectangles - 1; i >= 0; i--) {
			if (rectangles.get(i).containsPoint(x, y))
				return rectangles.get(i);
		}
		return null;
	}
}
