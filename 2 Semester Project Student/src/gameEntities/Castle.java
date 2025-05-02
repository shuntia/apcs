package gameEntities;

import entryBehaviors.*;
import javax.swing.ImageIcon;

public class Castle extends GameEntity {

	public Castle() {
		// Superclass constructor calls for: String name, Image image
		super("Castle", new ImageIcon("castle.jpg").getImage());
		this.entryBehavior = EntryBehavior.NO_ENTRY;
	}

	
}

