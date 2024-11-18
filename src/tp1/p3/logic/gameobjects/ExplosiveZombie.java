package tp1.p3.logic.gameobjects;

import tp1.p3.logic.actions.ExplosionAction;
import tp1.p3.view.Messages;

public class ExplosiveZombie extends Zombie{
	public static final int EXPLOSION_DAMAGE = 3;
	private static final int DAMAGE = 1;
	
	
	ExplosiveZombie(){
		
	}
	public String getName() {
		return Messages.EXPLOSIVE_ZOMBIE_NAME;
	}
	
	@Override
	public String getSymbol() {
		return Messages.EXPLOSIVE_ZOMBIE_SYMBOL;
	}
	
	@Override
	public void onExit() {
		game.pushAction(new ExplosionAction(this.col, this.row, EXPLOSION_DAMAGE, false));
		game.zombieDied();
	}
	
	@Override
	public String getDescription() {
		return Messages.zombieDescription(Messages.EXPLOSIVE_ZOMBIE_NAME, getSpeed(), getDamage(), DAMAGE);
	}
	
	@Override
	public void update(GameObject g) {
		super.update(this);
		if(lives == 0) {
			ExplosionAction ea = new ExplosionAction(col, row, DAMAGE, false);
			ea.execute(game);
		}
	}
}
