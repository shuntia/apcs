package gameEntities;
import java.awt.Image;
import java.util.*;


public abstract class GameCharacter extends GameEntity {
	// Hero and Enemies
	private int hp;
	private int mp;
	private int attackPower; // an int out of 100
	private int attackAccuracy;  // an int out of 100
	//private List<Spell> spells;
	private WeaponBehavior weapon;

	public GameCharacter(String name, Image image, int hp, int mp, int attackPower, int attackAccuracy, WeaponBehavior weapon) {
		super(name, image);
		this.hp = hp;
		this.mp = mp;
		this.attackPower = attackPower;
		this.attackAccuracy = attackAccuracy;
		this.weapon = weapon;
	}

	public int getHp() {
		return hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getAttackPower() {
		return attackPower;
	}

	public int getMp() {
		return mp;
	}
	
	public void setMp(int mp) {
		this.mp = mp;
	}
	
	public int getAttackAccuracy() {
		return attackAccuracy;
	}

	public void attack(GameCharacter adversary) {
		int randResult = (int)(Math.random()*100) + 1; // 1 - 100
		if(this instanceof Hero) {
			System.out.println("**Desparation attack!");
			this.weapon=new SwordBehavior();
		}
		if(randResult < this.attackAccuracy && this.weapon!=null) { // say this character has attackAccuracy = 70, you have 70/100 chance of success
			int damage=this.attackPower*this.weapon.useWeapon();
			System.out.println(this.getName() + " attacks, and hits for " + damage+ " points of damage!");
			adversary.setHp(adversary.getHp()-damage); 
		}
		else
			System.out.println(this.getName() + " tries to attack " + adversary.getName() + ", but misses!");
	
		System.out.println();
	}
	
	// Should create a different abstract class, that only magical characters inherit from
	public void magicAttack(GameCharacter adversary) {
		// random attack accuracy
		int randResult = (int)(Math.random()*100) + 1; // 1 - 100
		if(randResult < this.attackAccuracy) { // say accuracy = 70, you have 70/100 chance of success
			adversary.setHp(adversary.getHp() - this.attackPower); 
			System.out.println(this.getName() + " does ***Placeholder spell***, and causes " + this.attackPower + " points of damage!");
		}
		else
			System.out.println(this.getName() + " tries to attack " + adversary.getName() + " with a magic attack, but misses!");
	}
	
	public void brag() {
		System.out.println("I'm the best!");
	}
	


	
}
