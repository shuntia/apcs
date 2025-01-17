
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

public class DraggingDrawingPanelListener implements MouseListener, MouseMotionListener {
    Rect selected=null;
    DrawingPanel panel;
    Point dragStart=null;
    ArrayList<Rect> dragging=new ArrayList<>();
    public DraggingDrawingPanelListener(DrawingPanel panel){
        this.panel=panel;
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        dragStart=new Point(e.getX(), e.getY());
        for(Drawable shape:panel.shapes){
            if(((Rect)shape).contains(dragStart)){
                dragging.add((Rect)shape);
            }
        }
        if(dragging.isEmpty()){
            selected=new Rect(e.getX(), e.getY(), 0, 0, Color.BLUE);
            panel.addRectangle(selected);
        }
        panel.repaint();
    }
    public void mouseReleased(MouseEvent e) {
        selected=null;
        for(Rect r:dragging){
            r.finalizeOffset();
        }
        dragging.clear();
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {
        Point mouseCoord=new Point(e.getX(), e.getY());
        if(selected==null){
            if(dragging.isEmpty())return;
            Point diff=mouseCoord.diff(dragStart);
            for(Rect r:dragging){
                r.offset(diff.x, diff.y);
            }
        }else{
            selected.setCorners(selected.getCoord().x, selected.getCoord().y, e.getX(), e.getY());
        }
        panel.repaint();
    }
    
}
