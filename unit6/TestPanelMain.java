
import java.awt.Color;

import javax.swing.*;

public class TestPanelMain {
	
	public static void main(String[] args) {
		JFrame window = new JFrame("My Title");
		window.setSize(400,600);
		window.setLocation(100,100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel content = new TestPanel(); // TestPanel is-a JPanel
		window.setContentPane(content);
		
		window.setVisible(true);
	}

}




