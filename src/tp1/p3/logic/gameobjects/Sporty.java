package tp1.p3.logic.gameobjects;

import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public class Sporty extends Zombie{
	
	private static final int ENDURANCE = 2;
	private static final int SPEED = 1;
	
	Sporty(){
	}
	
	Sporty(GameWorld game, int col, int row){
		super(game, col, row, ENDURANCE, SPEED);
	}
	
	public String getName() {
		return Messages.SPORTY_ZOMBIE_NAME;
	}
	
	public String getSymbol() {
		return Messages.SPORTY_ZOMBIE_SYMBOL;
	}
	
	@Override
	protected int getSpeed() {
		return SPEED;
	}
	
	@Override
	protected int getEndurance() {
		return ENDURANCE;
	}
	
	
	
	@Override
	public String getDescription() {
		return Messages.zombieDescription(Messages.SPORTY_ZOMBIE_NAME, getSpeed(), getDamage(), ENDURANCE);
	}
}
