package gameEntities;

import entryBehaviors.*;
import java.awt.Image;

public abstract class GameEntity {
	// Could be Hero, Enemy (Slime/Wizard...etc), Tree, Blank, Castle, etc
	private String name; // private => subclasses CANNOT directly access (unlike protected/public/package)
	private Image image;
	protected EntryBehavior entryBehavior;  // Using enum instead of class or an int
	
	public GameEntity(String name, Image image) {
		this.name = name;
		this.image = image;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Image getImage() {
		return image;
	}

	public EntryBehavior getEntryType() {
		return entryBehavior;
	}

	public String toString() {
		return name;
	}
	
}
