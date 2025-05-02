package gameEntities;

import entryBehaviors.*;
import javax.swing.ImageIcon;

public class Tree extends GameEntity{

	public Tree() {
		// Superclass constructor calls for: String name, Image image
		super("Tree", new ImageIcon("tree.gif").getImage());
		this.entryBehavior = EntryBehavior.NO_ENTRY;
	}
	
}
