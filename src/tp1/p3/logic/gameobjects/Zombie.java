package tp1.p3.logic.gameobjects;

import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public class Zombie extends GameObject implements Cloneable{
	private static final int ENDURANCE = 5;
	private static final int DAMAGE = 1;
	private static final int SPEED = 2;

	
	Zombie(){
	}
	
	Zombie(GameWorld game, int col, int row){
		this(game, col, row, ENDURANCE, SPEED);
	}

	Zombie(GameWorld game, int col, int row, int endurance, int speed) {
		super(game, col, row, endurance, speed);
	}

	
	@Override
	public boolean receiveZombieAttack(int damage) {
		return false;
	}

	@Override
	public boolean receivePlantAttack(int damage) {
		if(isAlive()) {
			receiveDamage(damage);
			if(this.lives < 1) {
				onExit();
				if(damage > 1) {
					game.setScore(game.getScore()+20);
				}
				else {
					game.setScore(game.getScore()+10);
				}
			}
			return true;
		}
		return false;
	}
	
	@Override
	public void update(GameObject g) {
		super.update(this);
		if(isAlive()) {
			game.attackPlant(this.col-1, this.row, getDamage());
		}
	}
	

	public Zombie copy(GameWorld game, int col, int row, int endurance, int speed) {
		try {
			Zombie zombie = (Zombie) super.clone();
			zombie.game = game;
			zombie.col = col;
			zombie.row = row;
			zombie.lives = endurance;
			zombie.cooldown = speed;
			zombie.cooldownCount = speed;
			return zombie;
		}catch(CloneNotSupportedException e) {
			throw new RuntimeException(Messages.UNEXPECTED_RUNTIME_ERROR);
		}
	}

	public String getName() {
		return Messages.ZOMBIE_NAME;
	}
	
	public String getSymbol() {
		return Messages.ZOMBIE_SYMBOL;
	}
	
	
	@Override
	public String getDescription() {
		return Messages.zombieDescription(Messages.ZOMBIE_NAME,getSpeed(),getDamage(),ENDURANCE);
	}
	
	protected int getSpeed() {
		return SPEED;
	}
	
	protected int getDamage() {
		return DAMAGE;
	}
	
	protected int getEndurance() {
		return ENDURANCE;
	}
	
	@Override
	protected void delayedAction() {
		if(isAlive() && !game.isFullyOcuppied(col-1, row)) {
			col--;
			if(col == 0) game.zombieArrived();
		}
	}

	@Override
	public void onEnter() {
	}
	
	public void onExit() {
		game.zombieDied();	
	}

	@Override
	public void onDelete() {
	
	}

}