package mainGame;

import java.awt.event.*;

// Controller
public class GamePanelListener implements KeyListener, FocusListener, MouseListener {
	OverworldData model;
	GamePanel view;
	
	
	public GamePanelListener(OverworldData d, GamePanel p) {
		model = d;
		view = p;
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		//System.out.println("Focus Gained");
	}

	@Override
	public void focusLost(FocusEvent e) {
		//System.out.println("Focus Lost");
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();  

        if (key == KeyEvent.VK_LEFT) {  // left arrow key
        	model.movePlayer(Direction.LEFT);
            view.repaint(); 
        }
        else if (key == KeyEvent.VK_RIGHT) {  // right arrow key
        	model.movePlayer(Direction.RIGHT);
            view.repaint();
        }
        else if (key == KeyEvent.VK_UP) {  // up arrow key
        	model.movePlayer(Direction.UP);
            view.repaint();
        }
        else if (key == KeyEvent.VK_DOWN) {  // down arrow key
        	model.movePlayer(Direction.DOWN);
            view.repaint();
        }
	}

	public void keyReleased(KeyEvent e) {}

	public void mouseClicked(MouseEvent e) {
		System.out.println("x: " + e.getX() + ", y: " + e.getY());	
	}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

}
