package gameEntities;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import entryBehaviors.*;


public  class Hero extends GameCharacter {
	
	int experiencePoints;
	int level;

	public Hero() {
		// superclass constructor asks for (String name, Image image, int hp, int mp, int attackPower, int attackAccuracy)
		super("DefaultName", new ImageIcon("hero.gif").getImage(), 100, 10, 10, 70, new KnifeBehavior());
		experiencePoints = 0;
		level = 1;
	}

	public int getExperiencePoints() {
		return experiencePoints;
	}

	public void setExperiencePoints(int experiencePoints) {
		this.experiencePoints = experiencePoints;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}

