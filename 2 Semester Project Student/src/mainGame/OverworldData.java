package mainGame;

import gameEntities.*;
import java.awt.Image;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import entryBehaviors.*;

// The "model" -- data, logic, state
public class OverworldData {
	//private List<Observer> observers = new ArrayList<Observer>(); // Model is the 'subject' the 'publisher'
	private GamePanel view;
	private int numColsInGameWorld = 20;
	private int numRowsInGameWorld = 20;
	private int herosCurrRow = 0, herosCurrCol = 0;
	private Hero hero = new Hero();

	List<EntityInfo> entityInfo = new ArrayList<EntityInfo>();  // store info on each type of Entity (to make looping through easier)
	private int numTrees = 36, numWater = 36;
	private int numWizards = 8, numMagicians = 8, numSlimes = 24, numWolves = 12;

	//***Create a 2D array that can hold ALL of our stuff for the game
	private GameEntity[][] gameEntities = new GameEntity[numRowsInGameWorld][numColsInGameWorld];
	boolean[][] occupied = new boolean[numRowsInGameWorld][numColsInGameWorld]; 

	public OverworldData(String heroName) {
		// Add Enemy info (used when creating game world).  e.g. 36 trees, 36 waters, 8 wizards...
		entityInfo.add(new EntityInfo(new Tree(), numTrees));
		entityInfo.add(new EntityInfo(new Water(), numWater));
		entityInfo.add(new EntityInfo(new Wizard(), numWizards));
		entityInfo.add(new EntityInfo(new Magician(), numMagicians));
		entityInfo.add(new EntityInfo(new Slime(), numSlimes));
		entityInfo.add(new EntityInfo(new Wolf(), numWolves));

		this.setUpGameData();
	}

	private void setUpGameData() {
		// First, fill everything with open space
		for(int i = 0; i < numColsInGameWorld; i++) {
			for(int j = 0; j < numRowsInGameWorld; j++) {
				gameEntities[i][j] = new OpenSpace();
			}
		}
		gameEntities[herosCurrRow][herosCurrCol] = hero; // set Hero and Castle in designated spot
		gameEntities[numColsInGameWorld - 1][numRowsInGameWorld - 1] = new Castle();
		occupied[herosCurrRow][herosCurrCol] = true;
		occupied[numColsInGameWorld - 1][numRowsInGameWorld - 1] = true; 

		for(EntityInfo entityType:entityInfo) { // for each type of entity...
			
			int row, col;  // e.g. if there are 4 Wizards, then do this 4 times...
			for(int i = 0; i < entityType.getNumOfThisEntity(); i++) {
				do {
					row = (int)(Math.random()*(numRowsInGameWorld - 3)) + 2; // generate rand row  (leave a bit of space around both edges)
					col = (int)(Math.random()*(numColsInGameWorld - 3)) + 2; // generate rand col  (leave a bit of space around both edges) 
				} while(occupied[row][col]); // keep looking until you find an open spot (Open Spaces were not marked as occupied)
				// Add the appropriate object to this randomly chosen spot o the game board
				if(entityType.getEntity() instanceof Tree)
					gameEntities[row][col] = new Tree();
				if(entityType.getEntity() instanceof Water)
					gameEntities[row][col] = new Water();
				if(entityType.getEntity() instanceof Slime)
					gameEntities[row][col] = new Slime();
				if(entityType.getEntity() instanceof Wizard)
					gameEntities[row][col] = new Wizard();
				if(entityType.getEntity() instanceof Magician)
					gameEntities[row][col] = new Magician();
				if(entityType.getEntity() instanceof Wolf)
					gameEntities[row][col] = new Wolf();
				
				occupied[row][col] = true; // mark so no one can overwrite
			}
		}
	}
	
	
	// Provide a public interface to allow player to move to be called by controller
	public void movePlayer(Direction direction) {
		// Only need to modify the 'state' -- the entities 2D array.  The painting will be done in the View.
		int herosPrevRow = herosCurrRow, herosPrevCol = herosCurrCol;
		GameEntity entityOccupyingSpace = null;
		
		if(direction == Direction.LEFT) { 
			if(herosCurrCol == 0) return; // prevent moving off screen 
			
			entityOccupyingSpace = gameEntities[herosCurrRow][herosCurrCol - 1];
	
			if(entityOccupyingSpace.getEntryType() == EntryBehavior.NO_ENTRY) return; // prevent walking into an impassable object
			
			herosCurrCol--; // if everything is OK, move in the desired direction.
		}
		else if(direction == Direction.RIGHT) {
			if(herosCurrCol == numColsInGameWorld - 1) return;
			
			entityOccupyingSpace = gameEntities[herosCurrRow][herosCurrCol + 1];
			
			if(entityOccupyingSpace.getEntryType() == EntryBehavior.NO_ENTRY) return;
			
			herosCurrCol++;
		}
		else if(direction == Direction.UP) {
			if(herosCurrRow == 0) return;
			
			entityOccupyingSpace = gameEntities[herosCurrRow - 1][herosCurrCol];
	
			if(entityOccupyingSpace.getEntryType() == EntryBehavior.NO_ENTRY) return;
			
			herosCurrRow--;
		}
		else if(direction == Direction.DOWN) {
			if(herosCurrRow == numRowsInGameWorld - 1) return;
			
			entityOccupyingSpace = gameEntities[herosCurrRow + 1][herosCurrCol];
		
			if(entityOccupyingSpace.getEntryType() == EntryBehavior.NO_ENTRY) return;
			
			herosCurrRow++;
		}
		
		if(entityOccupyingSpace.getEntryType() == EntryBehavior.FIGHT)
			fight(entityOccupyingSpace);
		
		gameEntities[herosPrevRow][herosPrevCol] = new OpenSpace();  //  even if an enemy was here before, it is now defeated. 
		gameEntities[herosCurrRow][herosCurrCol] = hero; //update where hero is
	}
	
	// Placeholder fight functionality -- Perhaps make a separate class
	public void fight(GameEntity entityToFight) { 
		Enemy enemy = (Enemy)entityToFight;  

		while(hero.getHp() > 0 && enemy.getHp() > 0) {
			hero.attack(enemy);
			if(enemy.getHp() <= 0)
				break;
			enemy.attack(hero);

		}
		if(hero.getHp() <= 0) {
			JOptionPane.showMessageDialog( view, hero.getName() + " has lost all his energy!\nGame Over!");
			System.exit(0);
		}
		else{
			JOptionPane.showMessageDialog( view, hero.getName() + " has been victorious over the " + enemy.getName() + ".\n"
					+ enemy.getExperienceGiven() + " experience points gained!");
		}
			
		hero.setExperiencePoints(hero.getExperiencePoints() + enemy.getExperienceGiven());
		view.updateStatsPanel();
	}

	public void registerView(GamePanel panel) {
		view = panel;
	}
	

	public Hero getHero() {
		return hero;
	}
	

	public GameEntity[][] getGameEntities() {
		return gameEntities;
	}

	
	public int getNumColsInGameWorld() {
		return numColsInGameWorld;
	}

	public int getNumRowsInGameWorld() {
		return numRowsInGameWorld;
	}
	
}
