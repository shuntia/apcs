package gameEntities;

import entryBehaviors.*;
import javax.swing.ImageIcon;

public class OpenSpace extends GameEntity {

	public OpenSpace() {
		// Superclass constructor calls for: String name, Image image
		super("Open Space", new ImageIcon("no.gif").getImage());
		this.entryBehavior = EntryBehavior.DO_NOTHING;
	}
	
}
