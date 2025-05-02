package gameEntities;

import entryBehaviors.*;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

public class Slime extends Enemy {
	static int slimeCount = 0;
	
	public Slime() {
		// String name, Image image, int hp, int mp, int attackPower, int attackAccuracy, int experienceGiven
		super("Slime", new ImageIcon("slime.gif").getImage(), 15, 0, 1, 50, 1);
		this.entryBehavior = EntryBehavior.FIGHT;

        slimeCount++;
	}

}
