
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

public class RectanglePanelListener implements MouseListener, MouseMotionListener {
    public static final boolean STACK=false;
    Rect selected=null;
    RectanglePanel panel;
    Point dragStart=null;
    ArrayList<Rect> dragging=new ArrayList<>();
    public RectanglePanelListener(RectanglePanel panel){
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
            Rect tmp=new Rect(e.getX(), e.getY(), 30,20, e.isShiftDown()?Color.BLUE:(e.isMetaDown()?Color.GREEN:Color.RED));
            panel.addRectangle(tmp);
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
            if(STACK){
                for(Rect r:dragging){
                    r.offset(diff.x, diff.y);
                }
            }else{
                dragging.get(dragging.size()-1).offset(diff.x, diff.y);
            }
        }else{
            selected.setCorners(selected.getCoord().x, selected.getCoord().y, e.getX(), e.getY());
        }
        panel.repaint();
    }
    
}
