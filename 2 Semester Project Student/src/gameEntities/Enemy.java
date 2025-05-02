package gameEntities;

import java.awt.Image;

public abstract class Enemy extends GameCharacter {
	
	private int experienceGiven;
		
	public Enemy(String name, Image image, int hp, int mp, int attackPower, int attackAccuracy, int experienceGiven) {
		super(name, image, hp, mp, attackPower, attackAccuracy);
		this.experienceGiven = experienceGiven;
	}

	public int getExperienceGiven() {
		return experienceGiven;
	}

	public void setExperienceGiven(int experienceGiven) {
		this.experienceGiven = experienceGiven;
	}
	
	
}
