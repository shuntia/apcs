import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;

// This demos having the Listener class be SEPARATED in a different class from the component 
// For bigger programs especially, this separation of function is good style.
// However, now the Listener can't access instance variables and methods like repaint() without saving a reference 
// to this custom JPanel object in the Listener class, and we also 
// have to write methods to allow access to the member variables keeping track of state e.g. 'message'  
public class SmithPanel extends JPanel implements MouseListener{

	private String message; 

	// Kind of awkward conceptually to have main here, but to keep everything in one file...
	public static void main(String[] args) {
		JFrame window = new JFrame("Test3");
		window.setVisible(true);
		window.setSize(400,600);
		window.setLocation(300,300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(new SmithPanel());
	}
	
	public SmithPanel() {
		message = "default";
		this.addMouseListener(this);
	}
	
	// draws based on state set by member variables
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // otherwise, PCs have jumbled output
		g.drawString(message, 100, 100);
	}

	public void mouseClicked(MouseEvent e) {
		message = "mouse clicked"; // mouse was pressed, then released in same location
		repaint();
	}

	public void mousePressed(MouseEvent e) {
		message = "mouse pressed";
		repaint();
	}

	public void mouseReleased(MouseEvent e) {
		message = "mouse released";
		repaint();
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	public void setMessage(String message) {
		this.message = message;
		repaint();
	}

}
