import java.awt.*; 
import javax.swing.*;
			// TestPanel is-a JPanel (inherits variables/methods)
public class TestPanel extends JPanel {

	// constructor
	public TestPanel() {
		JButton open = new JButton("Open");
		JButton close = new JButton("Close");
		
		// Nothing happened!
		// Because we need to add the JComponents to the container, our JPanel object
		// ***syntax: container.add(component)
//		this.add(open);
//		this.add(close);
		
		// What if we want to fix the layout?  Using a LayoutManager
		// means you don't worry about coordinates -- it resizes it for you!
		this.setLayout(new BorderLayout());
		
		// need 2 more containers
		JPanel northContainer = new JPanel();
		JPanel southContainer = new JPanel();
		
		// Syntax: container.add(component);
		northContainer.add(open);
		northContainer.add(close);
	
		//default layout for northContainer is FlowLayout
		this.add(northContainer, BorderLayout.NORTH);

		JButton prev = new JButton("Previous");
		JButton play = new JButton("Play");
		JButton next = new JButton("Next");
		JButton rewind = new JButton("Rewind");
		JButton pause = new JButton("Pause");
		JButton forward = new JButton("Forward");
		
		//you can set the Layout style of this smaller container as well
		southContainer.setLayout(new GridLayout(2,3));
		
		southContainer.add(prev);
		southContainer.add(play);
		southContainer.add(next);
		southContainer.add(rewind);
		southContainer.add(pause);
		southContainer.add(forward);

		//Aha! haven't added southContainer to the TestPanel object
		this.add(southContainer, BorderLayout.SOUTH);
				
	}
	
}
