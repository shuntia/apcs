
import java.awt.Color;
import java.awt.event.*;

public class DraggingDrawingPanelListener implements MouseListener, MouseMotionListener {
    Rect selected=null;
    DrawingPanel panel;
    public DraggingDrawingPanelListener(DrawingPanel panel){
        this.panel=panel;
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        selected=new Rect(e.getX(), e.getY(), 0, 0, Color.BLUE);
        panel.addRectangle(selected);
        panel.repaint();
    }
    public void mouseReleased(MouseEvent e) {
        selected=null;
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {
        selected.setCorners(selected.getCoord().x, selected.getCoord().y, e.getX(), e.getY());
        panel.repaint();
    }
    
}
