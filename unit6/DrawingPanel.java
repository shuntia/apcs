
import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.JPanel;
public class DrawingPanel extends JPanel{
    private volatile int counter = 0;
    public volatile ArrayList<Drawable> shapes=new ArrayList<Drawable>();
    public DrawingPanel(){
        super();
        DraggingDrawingPanelListener listener=new DraggingDrawingPanelListener(this);
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
    }
    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);
        for(Drawable shape:shapes){
            shape.draw(g);
        }
    }
    Color randomColor(){
        return new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
    }
    public void addRectangle(int x, int y, int width, int height, Color color){
        Rect r=new Rect(x, y, width, height, color);
        shapes.add(r);
        repaint();
    }
    public void addRectangle(Rect r){
        shapes.add(r);
        repaint();
    }
}
