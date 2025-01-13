import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SmithPanelListener implements MouseListener {
	SmithPanel panel;
	public SmithPanelListener(SmithPanel panel) {
		this.panel = panel;
		panel.addMouseListener(this);
	}
	// Adjust the state of the member variables in SmithPanel 
	// call repaint to inform System to call paintComponent() to have 
	// these changes updated on the screen
	public void mouseClicked(MouseEvent e) {
		panel.setMessage("mouse clicked"); 
	}
	public void mousePressed(MouseEvent e) {
		panel.setMessage("mouse pressed");
	}
	public void mouseReleased(MouseEvent e) {
		panel.setMessage("mouse released");
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
