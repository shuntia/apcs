package gameEntities;

import entryBehaviors.*;
import javax.swing.ImageIcon;

public class Wizard extends Enemy {
	static int wizardCount = 0;
	
	public Wizard() {
		// String name, Image image, int hp, int mp, int attackPower, int attackAccuracy, int experienceGiven
		super("Wizard", new ImageIcon("wizard.gif").getImage(), 25, 100, 10, 50, 5);

		this.entryBehavior = EntryBehavior.FIGHT;

		wizardCount++;
	}
	
}
