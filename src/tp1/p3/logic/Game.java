package tp1.p3.logic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import tp1.p3.control.Command;
import tp1.p3.control.Level;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.control.exceptions.RecordException;
import tp1.p3.logic.actions.ExplosionAction;
import tp1.p3.logic.actions.GameAction;
import tp1.p3.logic.gameobjects.GameObject;
import tp1.p3.view.Messages;

public class Game implements GameStatus, GameWorld {
	
	public static final int INITIAL_SUNCOINS = 50;
	private boolean playerQuits;
	private long seed;
	private Level level;
	private int cycle;
	private Random rand;
	private int suncoins;
	private ZombiesManager zombiesManager;
	private SunsManager sunsManager;
	private GameObjectContainer container;
	private Deque<GameAction> actions;
	private boolean playerDied;
	private int score;
	private int record;
	private List<GameObject> aux = new ArrayList<GameObject>();


	public Game(long seed, Level level) throws GameException {
		this.seed = seed;
		this.level = level;
		this.container = new GameObjectContainer();
		reset();
	}

	/**
	 * Resets the game.
	 */
	@Override
	public void reset() throws GameException {
		reset(this.level, this.seed);
	}

	public Level getLevel() {
		return level;
	}

	/**
	 * Resets the game with the provided level and seed.
	 * 
	 * @param level {@link Level} Used to initialize the game.
	 * @param seed Random seed Used to initialize the game.
	 */
	@Override
	public void reset(Level level, long seed) {
		this.level = level;
		this.seed = seed;
		this.playerQuits = false;
		this.playerDied = false;
		this.cycle = 0;
		this.score = 0;
		this.rand = new Random(seed);
		this.suncoins = INITIAL_SUNCOINS;
		this.zombiesManager = new ZombiesManager(this,level,this.rand);
		this.sunsManager = new SunsManager(this, this.rand);
		this.container = new GameObjectContainer();	
		this.actions = new ArrayDeque<>();
		try {
			loadRecord();
		} catch (RecordException ex) {
			System.out.format(ex.getMessage() + " %n %n");
			playerQuits = true; // No se si esto ira aasi
		}
	}

	public int getRecord() {
		return record;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Executes the game actions and update the game objects in the board.
	 * @return 
	 * 
	 */
	@Override
	public boolean update() throws GameException {
		// 1. Execute pending actions
		executePendingActions();
		// 2. Execute game Actions
		this.zombiesManager.update();
		// 3. Game object updates		
		this.container.update();
		this.sunsManager.update();
		addNewItems();
		// 4. & 5. Remove dead and execute pending actions
		boolean deadRemoved = true;
		while (deadRemoved || areTherePendingActions()) {
			// 4. Remove dead
			deadRemoved = this.container.removeDead();
			// 5. execute pending actions
			executePendingActions();
		}
		this.cycle++;
		// 6. Notify commands that a new cycle started
		Command.newCycle();
		return true;
	}

	public void updateRecord() throws RecordException {
        if (isNewRecord()) {
            Record.save(score, level.toString());
        }
    }

    public boolean isNewRecord() {
        return (score > record);
    }

    public void loadRecord() throws RecordException {
        record = Record.load(level.toString());
    }

	
	private void executePendingActions() {
		while (!this.actions.isEmpty()) {
			GameAction action = this.actions.removeLast();
			action.execute(this);
		}
	}

	private boolean areTherePendingActions() {
		return this.actions.size() > 0;
	}

	
	public boolean isFinished() {
		boolean result = false;
		if (this.zombiesManager.allZombiesDied() || isPlayerDied() || isPlayerQuits()) {
			result = true;
			try {
				updateRecord();
			} catch (RecordException ex) {
				System.out.format(ex.getMessage() + " %n %n");
			}
		}
		return result;
	}

	public boolean isPlayerQuits() {
		return this.playerQuits;
	}

	
	@Override
	public void pushAction(GameAction gameAction) {
		this.actions.addLast(gameAction);
	}
	
	@Override
	public boolean isValidPlantPosition(int col,int row) {
		boolean inLimits = col >= 0 && row >= 0 && col < GameWorld.NUM_COLS && row < GameWorld.NUM_ROWS;
		if(inLimits) {
			return !this.container.isObjectInPosition(col,row);
		}
		return false;	
	}
	
	@Override
	public boolean isValidZombiePosition(int col,int row) {
		boolean inLimits = col >= 0 && row >= 0 && col <= GameWorld.NUM_COLS && row <= GameWorld.NUM_ROWS;
		if(inLimits) {
			return !this.container.isObjectInPosition(col,row);
		}	
		return false;	
	}
	
	@Override
	public boolean attackPlant(int col,int row,int damage) {
		List<GameObject> gameItems = this.container.getGameItemsInPosition(col, row);
		boolean attacked = false;
		for(GameObject gameItem : gameItems) {
			attacked = gameItem.receiveZombieAttack(damage);
		}	
		return attacked;
	}
	
	@Override
	public boolean attackZombie(int col,int row, int damage) {
		List<GameObject> gameItems = this.container.getGameItemsInPosition(col, row);
		boolean attacked = false;
		for(GameObject gameItem : gameItems) {
			attacked = gameItem.receivePlantAttack(damage);
		}
		return attacked;
	}
	
	@Override
	public boolean addItem(GameObject item) throws GameException {
		int col = item.getCol();
		int row = item.getRow();
		if(!isPositionInLimits(col, row) || (item.fillPosition() && isFullyOcuppied(col,row))) {
			throw new GameException(Messages.INVALID_POSITION);
	
		}
		this.container.add(item);
		return true;
	}
	
	public void addNewItems() {
        for(int i = 0; i < aux.size(); i++) {
            container.add(aux.get(i));
        }
        aux.clear();
    }

	
	public boolean isFullyOcuppied(int col, int row) {
		return this.container.isFullyOccupied(col, row);
	}

	private boolean isPositionInLimits(int col, int row) {
		return col <= NUM_COLS && col >= 0 && row <= NUM_ROWS && row >= 0;
	}

	
	@Override
	public boolean tryToBuy(int cost) {
		boolean enough = this.suncoins >= cost;
		if(enough) {
			this.suncoins -= cost;
		}
		return enough;
	}


	@Override
	public void addSunCoins(int sunCoins) {
		this.suncoins += sunCoins;
		
	}

	@Override
	public void playerQuits() {
		this.playerQuits = true;
		
	}


	@Override
	public boolean isPositionEmpty(int col, int row) {
		boolean res = false;
		List<GameObject> result = this.container.getGameItemsInPosition(col, row);
		if(result.isEmpty() || result.size() == 1 && result.get(0).getSymbol() == "*") res = true;
		return res;
	}

	@Override
	public void zombieDied() {
		zombiesManager.zombieDied();
		
	}

	@Override
	public void zombieArrived() {
		this.playerDied = true;		
	}


	@Override
	public int getCycle() {
		return cycle;
	}

	@Override
	public int getSuncoins() {
		return suncoins;
	}

	@Override
	public int getRemainingZombies() {
		return zombiesManager.getRemainingZombies();
	}

	@Override
	public String positionToString(int col, int row) {
		return container.positionToString(col, row);
	}

	@Override
	public int getGeneratedSuns() {
		return SunsManager.getGeneratedSuns();
	}

	@Override
	public int getCaughtSuns() {
		return SunsManager.getCaughtSuns();
	}

	@Override
	public boolean isPlayerDied() {
		return this.playerDied;
	}

	@Override
	public boolean allZombiesDied() {
		return zombiesManager.allZombiesDied();
	}

	@Override
	public void addSun() {
		sunsManager.addSun(aux);
	}

	
	@Override
	public boolean tryToCatchObject(int col, int row) {
		return container.TryToCatchObject(col,row);
	}


	@Override
	public void pushAction(ExplosionAction explosionAction) {
		this.actions.addLast(explosionAction);
	}

	@Override
	public void checkValidPlantPosition(int col, int row) throws GameException {
	
		
	}

	@Override
	public void checkValidZombiePosition(int col, int row) throws GameException {
		// TODO Auto-generated method stub
		
	}

	public boolean execute(Command command) throws GameException {
			return command.execute(this);

	}

}
