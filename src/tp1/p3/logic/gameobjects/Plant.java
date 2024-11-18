package tp1.p3.logic.gameobjects;

import tp1.p3.logic.GameWorld;

public abstract class Plant extends GameObject {
	
	protected Plant(){
		
	}

	public Plant(GameWorld game, int col, int row, int endurance) {
		super(game, col, row, endurance);
	}

	public Plant(GameWorld game, int col, int row, int endurance, int delayToExplode) {
		super(game, col, row, endurance, delayToExplode);
	}

	public String getDescription() {
		return null;
	}

	public int getCost() {
		return 0;
	}

	public String getSymbol() {
		return null;
	}

	public Plant copy(GameWorld game, int col, int row) {
		return null;
	}

	protected void delayedAction() {
		
	}
	
	@Override
	public boolean receivePlantAttack(int damage) {
		return false;
	}
	
	@Override
	public boolean receiveZombieAttack(int damage) {
		boolean res = false;
		if(isAlive()) {
			receiveDamage(damage);
			res = true;
		}
		return res;
	}

	@Override
	public void onEnter() {
		
	}

	@Override
	public void onExit() {
		
	}

	abstract public String getName();

	abstract public int getEndurance();

	abstract public int getDamage();

}
