package gameEntities;

import gameEntities.*;

// Groups together a GameEntity with the number of times it appears on the board.
// Used to easily distribute the desired number of each type of Entity with a single for loop
// in OverworldData.
public class EntityInfo {

	GameEntity entity; 
	int numOfThisEntity;
	
	public EntityInfo(GameEntity entity, int numOfThisEntity) {
		this.entity = entity;
		this.numOfThisEntity = numOfThisEntity;
	}

	public GameEntity getEntity() {
		return entity;
	}

	public void setEntity(GameEntity entity) {
		this.entity = entity;
	}

	public int getNumOfThisEntity() {
		return numOfThisEntity;
	}

	public void setNumOfThisEntity(int numOfThisEntity) {
		this.numOfThisEntity = numOfThisEntity;
	}


	
	
}
