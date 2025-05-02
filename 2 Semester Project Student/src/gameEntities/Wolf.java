package gameEntities;

import entryBehaviors.*;
import javax.swing.ImageIcon;


public class Wolf extends Enemy {
	static int wolfCount = 0;
	
	public Wolf() {
		// String name, Image image, int hp, int mp, int attackPower, int attackAccuracy, int experienceGiven
		super("Wolf", new ImageIcon("wolf.gif").getImage(), 40, 0, 50, 50, 10);

		this.entryBehavior = EntryBehavior.FIGHT;
		wolfCount++;
	}
	

}