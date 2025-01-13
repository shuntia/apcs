
import java.awt.Color;
import java.awt.event.*;

public class DrawingPanelListener implements MouseListener {
    DrawingPanel panel;
    public DrawingPanelListener(DrawingPanel panel){
        this.panel=panel;
        panel.addMouseListener(this);
    }
    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
        Color c;
        if (e.isShiftDown()){
            c=Color.BLUE;
        }else if(e.isMetaDown()){
            c=Color.GREEN;
        }else{
            c=Color.RED;
        }
        panel.addRectangle(e.getX(), e.getY(), 100, 50, c);
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
