package tp1.p3.logic.gameobjects;

import static tp1.p3.view.Messages.status;

import tp1.p3.logic.GameWorld;
import tp1.p3.logic.SunsManager;
import tp1.p3.view.Messages;

public class Sun extends GameObject{
	
	public static final int SUN_LIVES = 10 + 1;
	private static final int SUN_COINS = 10;
	
	
	
	public Sun(GameWorld game, int col, int row){
		super(game, col, row, SUN_LIVES);
	}
	
	
	@Override
	public boolean receiveZombieAttack(int damage) {
		return false;
	}

	@Override
	public boolean receivePlantAttack(int damage) {
		return false;
	}
	
	@Override
	public void update(GameObject gameObject) {
		this.lives--;
	}
	
	@Override
	protected void delayedAction() {
		
	}

	@Override
	public boolean catchObject() {
		game.addSunCoins(SUN_COINS);
		kill();
		SunsManager.setCaughtSuns(SunsManager.getCaughtSuns()+1);
		return true;
	}


	@Override
	public boolean isAlive() {
		return this.lives > 0;
	}

	@Override
	public String getSymbol() {
		return Messages.SUN_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.SUN_DESCRIPTION;
	}

	@Override
	public void onEnter() {
		
	}

	@Override
	public void onExit() {
		// No hace nada, lo pone en las explicaciones
	}
	
	@Override
	public boolean fillPosition() {
		return false;
	}

	@Override
	public void onDelete() {
	
		
	}
	
	@Override
	public String toString() {
		if (isAlive()) {
			return status(getSymbol(), this.lives);
		} else {
			return "";
		}
	}
	

}
