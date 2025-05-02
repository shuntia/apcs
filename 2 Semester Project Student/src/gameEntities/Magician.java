package gameEntities;

import entryBehaviors.*;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Magician extends Enemy {
	static int magicianCount = 0;
	
	public Magician() {
		// String name, Image image, int hp, int mp, int attackPower, int attackAccuracy, int experienceGiven
		super("Magician", new ImageIcon("magician.gif").getImage(), 50, 50, 3, 40, 10);
		this.entryBehavior = EntryBehavior.FIGHT; // 
		magicianCount++;
	}

}