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

	public GameCharacter(String name, Image image, int hp, int mp, int attackPower, int attackAccuracy) {
		super(name, image);
		this.hp = hp;
		this.mp = mp;
		this.attackPower = attackPower;
		this.attackAccuracy = attackAccuracy;
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
		if(randResult < this.attackAccuracy) { // say this character has attackAccuracy = 70, you have 70/100 chance of success

			// *** This design is NOT what we want, if we can avoid it ***  
			// It is programming to a CONCRETE implementation, not a supertype (like an interface)
			// Thus, changes & additions must be HARD CODED every time we add a new weapon, based on its type
			// Hurting maintainability (more potential for error) and flexibility (can't change at runtime) 
//			int damageMultiplyer = 0;
//			if(this.weapon == "Sword") {
//				Sword swordWeapon = new Sword();
//				System.out.println("Ka-Shank!! The sword slices its target!");
//				damageMultiplyer = 4;
//				swordWeapon.attack(enemy, damageMultiplyer);
//			}
//			else if(this.weapon == "Knife") {
//				Knife knifeWeapon = new Knife();
//				System.out.println("Slice!!  The Knife cuts!");
//				damageMultiplyer = 1;
//				knifeWeapon.attack(enemy, damageMultiplyer);
//			}
//			else if(this.weapon == "Arrow") {
//				Arrow arrowWeapon = new Arrow();
//				System.out.println("Thud!!  The arrow finds its mark!");
//				damageMultiplyer = 2;
//				arrowWeapon.attack(enemy, damageMultiplyer);
//			}		
//			else if(this.weapon == "Axe") {
//				Axe axeWeapon = new Axe();
//				System.out.println("Whomp!!  The Axe strikes!")
//				damageMultiplyer = 3;
//				axeWeapon.attack(enemy, damageMultiplyer);
//			}		

			
			// Instead we want is for polymorphism does it's work. 
			// Loose coupling means that Character doens't really know much about its weapon...
			// just that the Character can call use() on it.  
			// This also means that weapons can be swapped during runtime easily.  
			// Also new weapons can be added to the game with minimal changes to the code (less chances to break stuff)
			System.out.println(this.getName() + " attacks, and hits for " + this.attackPower + " points of damage!");
			adversary.setHp(adversary.getHp() - this.attackPower); 
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
