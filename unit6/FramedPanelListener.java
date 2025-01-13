
import java.awt.Color;
import java.awt.event.*;

public class FramedPanelListener implements MouseListener{
    FramedPanel panel;
    public FramedPanelListener(FramedPanel panel){
        this.panel=panel;
        panel.addMouseListener(this);
    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        if(e.isMetaDown()){
            panel.addShape(new Oval(e.getX(), e.getY(), 50, 50, Color.RED));
        }else if(e.isShiftDown()){
            panel.addShape(new Rect(e.getX(), e.getY(), 50, 50, Color.BLUE));
        }else if(e.isAltDown()){
            panel.addShape(new Oval(e.getX(), e.getY(), 50, 50, Color.BLUE));
        }else if(e.isControlDown()){
            panel.clearShapes();
        }else{
            panel.addShape(new Rect(e.getX(), e.getY(), 50, 50, Color.RED));
        }
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}