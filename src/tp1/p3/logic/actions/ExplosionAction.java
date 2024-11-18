package tp1.p3.logic.actions;

import tp1.p3.logic.GameWorld;

public class ExplosionAction implements GameAction {

	private int col;
	private int row;
	private int damage;
	boolean attackZombies;

	public ExplosionAction(int col, int row, int damage, boolean b) {
		this.col = col;
		this.row = row;
		this.damage = damage;
		this.attackZombies = b;
	}

	@Override
	public void execute(GameWorld game) {
		if(attackZombies){
			for(int x = row - 1; x <= row + 1; x++) {
				for(int y = col - 1; y <= col + 1; y++ ) {
					game.attackZombie(y, x, damage);
				}
			}
		}
		else {
			for(int x = row - 1; x <= row + 1; x++) {
				for(int y = col - 1; y <= col + 1; y++ ) {
					game.attackPlant(y, x, damage);
				}
			}
		}	
	}
}
