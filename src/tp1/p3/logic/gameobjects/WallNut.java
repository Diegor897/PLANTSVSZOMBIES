package tp1.p3.logic.gameobjects;

import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public class WallNut extends Plant{
	private static final int COST = 50;
	private static final int ENDURANCE = 10;
	private static final int DAMAGE = 0;
	
	
	WallNut(){
		
	}
	public WallNut(GameWorld game, int col, int row) {
		super(game, col, row, ENDURANCE);
	}
	
	@Override
	public String getDescription() {
		return Messages.plantDescription(Messages.WALL_NUT_NAME_SHORTCUT, getCost(), DAMAGE, ENDURANCE);
	}
	
	@Override
	public String getSymbol() {
		return Messages.WALL_NUT_SYMBOL;	
	}
	
	@Override
	public int getCost() {
		return COST;
	}
	
	public void update() {
		
	}
	
	@Override
	public Plant copy(GameWorld game,int col,int row) {
		return new WallNut(game, col, row);
	}
	@Override
	public String getName() {
		return Messages.WALL_NUT_NAME;
	}
	@Override
	public int getEndurance() {
		return ENDURANCE;
	}
	@Override
	public int getDamage() {
		return DAMAGE;
	}
	@Override
	public void onDelete() {
		
	}
}
