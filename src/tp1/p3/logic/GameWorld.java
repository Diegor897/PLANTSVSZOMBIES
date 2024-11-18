package tp1.p3.logic;




import tp1.p3.control.Level;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.logic.actions.ExplosionAction;
import tp1.p3.logic.actions.GameAction;
import tp1.p3.logic.gameobjects.GameObject;


public interface GameWorld {

	public static final int NUM_ROWS = 4;

	public static final int NUM_COLS = 8;

	void addSun();

	void addSunCoins(int sunCoins);
	
	public void playerQuits();

	public boolean isPositionEmpty(int col, int row);

	public boolean attackPlant(int col, int row, int damage);

	public boolean attackZombie(int col, int row, int damage);

	public boolean isValidPlantPosition(int col, int row);

	public boolean isValidZombiePosition(int col, int row);

	public void zombieDied();

	public Level getLevel();
	
	public int getRecord();

	public void zombieArrived();
	

	boolean isFullyOcuppied(int col, int row);

	void pushAction(ExplosionAction explosionAction);

	void pushAction(GameAction gameAction);

	boolean addItem(GameObject item) throws GameException;

	boolean isFinished();
	
	boolean tryToCatchObject(int col, int row) throws GameException;
	
	boolean update() throws GameException;

	void reset() throws GameException;

	void reset(Level level, long seed) throws GameException;

	boolean tryToBuy(int cost) throws GameException;

	void checkValidPlantPosition(int col, int row) throws GameException;

	void checkValidZombiePosition(int col, int row) throws GameException;

	public int getScore();
	
	public void setScore(int score);

}
