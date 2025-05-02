package gameEntities;

import entryBehaviors.*;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Water extends GameEntity{

	public Water() {
		// Superclass constructor calls for: String name, Image image
		super("Water", new ImageIcon("water.gif").getImage());
		this.entryBehavior = EntryBehavior.NO_ENTRY;
	}
	
}