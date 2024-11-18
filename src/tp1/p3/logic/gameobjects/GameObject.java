package tp1.p3.logic.gameobjects;

import static tp1.p3.view.Messages.status;
import tp1.p3.logic.GameItem;
import tp1.p3.logic.GameWorld;
import tp1.p3.logic.SunsManager;

/**
 * Base class for game non playable character in the game.
 *
 */
public abstract class GameObject implements GameItem {
	
	protected static final int DEFAULT_COOLDOWN = 1;
	protected GameWorld game;
	protected int col;
	protected int row;	
	protected int lives;
	protected int cooldown;
	protected int cooldownCount;
	protected SunsManager manager;
	
	GameObject(){
		
	}

	GameObject(GameWorld game, int col, int row, int lives) {
		this(game, col, row, lives, DEFAULT_COOLDOWN);
	}

	GameObject(GameWorld game, int col, int row, int lives, int cooldown) {
		this.game = game;
		this.col = col;
		this.row = row;
		this.lives = lives;
		this.cooldown = cooldown;
		this.cooldownCount = cooldown;
	}

	public boolean isInPosition(int col, int row) {
		return this.col == col && this.row == row;
	}

	public int getCol() {
		return this.col;
	}

	public int getRow() {
		return this.row;
	}
	
	public boolean isAlive() {
		return this.lives > 0;
	}
	
	public void update(GameObject gameObject) {
		if(isAlive() && cooldownCount >= 0) {
			if(cooldownCount == 0) {
				cooldownCount = getCooldown();
				gameObject.delayedAction();
			}
			cooldownCount--;
		}
	}
	
	public String toString() {
		if (isAlive()) {
			return status(getSymbol(), this.lives);
		} else {
			return "";
		}
	}
	
	protected int getCooldown() {
		return cooldown;
	}
	
	protected void delayedAction() {
		
	}
	
	public void receiveDamage(int damage) {
		this.lives -= damage;
	}
	
	public void kill() {
		this.lives = 0;
	}
	
	@Override
	public boolean fillPosition() {
		return true;
	}
	
	@Override
	public boolean catchObject() {
		return false;
	}
	
	abstract public String getSymbol();

	abstract public String getDescription();
	
	abstract public void onEnter();
	
	abstract public void onExit();
		
	abstract public void onDelete();
}
