package tp1.p3.logic.gameobjects;

import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public class Sunflower extends Plant{
	private static final int COST = 20;
	private static final int ENDURANCE = 1;
	private static final int COOLDOWN = 2;
	private static final int DAMAGE = 0;
	private int cooldowncycles;
	
	public Sunflower() {
		
	}
	
	public Sunflower(GameWorld game, int col, int row) {
		super(game, col, row, ENDURANCE);	
	}
	

	@Override
	public String getDescription() {
		return Messages.plantDescription(getNameAndSymbol(), getCost(), getDamage(), getEndurance());	
	}
	
	@Override
	public int getEndurance() {
		return ENDURANCE;
	}

	@Override
	public int getDamage() {
		return DAMAGE;
	}

	public String getNameAndSymbol() {
		return Messages.SUNFLOWER_NAME_SHORTCUT;
	}
	
	@Override
	// Estaba protected
	public String getSymbol() {
		return Messages.SUNFLOWER_SYMBOL;
	}

	@Override
	// Estaba protected
	public String getName() {
		return Messages.SUNFLOWER_NAME;
	}

	@Override
	public int getCost() {
		return COST;
		
	}
	@Override
	public void update(GameObject gameObject) {
		if(cooldowncycles == 0) {
			game.addSun();
			this.cooldowncycles = COOLDOWN;
		}
		else {
			this.cooldowncycles--;
		}
	}

	@Override
	public Plant copy(GameWorld game, int col, int row) {
		return new Sunflower(game, col, row);
	}

	
	public void onDelete() {
		
	}

}
