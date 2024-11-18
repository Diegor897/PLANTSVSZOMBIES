package tp1.p3.logic.gameobjects;

import tp1.p3.logic.GameWorld;
import tp1.p3.logic.actions.ExplosionAction;
import tp1.p3.view.Messages;

public class CherryBomb extends Plant{
	private static final int COST = 30;
	private static final int ENDURANCE = 3;
	private static final int DAMAGE = 10;
	private static final int DELAY_TO_EXPLODE = 2;
	private boolean explode;
	
	CherryBomb(){
		
	}
	
	CherryBomb(GameWorld game, int col,int row){
		super(game, col, row, ENDURANCE, DELAY_TO_EXPLODE);
	}
	
	@Override
	public String getDescription() {
		return Messages.plantDescription(Messages.CHERRY_BOMB_NAME_SHORTCUT, getCost(), DAMAGE, ENDURANCE);
	}
	
	@Override
	public int getCost() {
		return COST;
	}
	
	@Override
	protected void delayedAction() {
		if(isAlive()) {
			explode = true;
			kill();
		}
	}
	
	@Override
	public int getCooldown() {
		return DELAY_TO_EXPLODE;
	}
	
	@Override
	public void onExit() {
		if(explode) {
			game.pushAction(new ExplosionAction(this.col, this.row, DAMAGE, true));
		}
	}
	
	@Override
	public Plant copy(GameWorld game,int col, int row) {
		return new CherryBomb(game, col, row);
	}

	// La he creado yo
	public String getNameAndSymbol() {
		return Messages.CHERRY_BOMB_NAME_SHORTCUT;
	}
	
	@Override
	// Estaba protected
	public String getSymbol() {
		return Messages.CHERRY_BOMB_SYMBOL;
	}

	@Override
	// Estaba protected
	public String getName() {
		return Messages.CHERRY_BOMB_NAME;
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
	
	@Override
	public void update(GameObject g) {
		lives--;
		if(lives == 0) {
			ExplosionAction ea = new ExplosionAction(col, row, DAMAGE, true);
			ea.execute(game);
		}
	}
}
