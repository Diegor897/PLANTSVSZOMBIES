package tp1.p3.logic.gameobjects;

import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public class Peashooter extends Plant {
	private static final int COST = 50;
	private static final int ENDURANCE = 3;
	private static final int DAMAGE = 1;
	
	Peashooter(){
		
	}
	
	Peashooter(GameWorld game,int col,int row){
		super(game, col, row, ENDURANCE);
	}
	
	@Override
	public String getDescription() {
		return Messages.plantDescription(Messages.PEASHOOTER_NAME_SHORTCUT, getCost(), DAMAGE, ENDURANCE);
	}
	
	@Override
	public String getSymbol() {
		return Messages.PEASHOOTER_SYMBOL;	
	}
	
	@Override
	public int getCost() {
		return COST;
	}
	
	@Override
	public void update(GameObject g) {
		if(isAlive()) {
			for(int i = this.col + 1; i < GameWorld.NUM_COLS; i++) {
				if(game.attackZombie(i, this.row, DAMAGE)) {
					break;
				}
			}
		}
	}
	
	public Plant copy(GameWorld game,int col,int row) {
		return new Peashooter(game,col,row);
	}
	
	@Override
	public String getName() {
		return Messages.PEASHOOTER_NAME;
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
