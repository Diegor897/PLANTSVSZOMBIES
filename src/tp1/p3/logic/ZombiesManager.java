package tp1.p3.logic;

import java.util.Random;

import tp1.p3.control.Level;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.logic.gameobjects.Zombie;
import tp1.p3.logic.gameobjects.ZombieFactory;

/**
 * Manage zombies in the game.
 *
 */
public class ZombiesManager {

	private GameWorld game;

	private Level level;

	private Random rand;

	private int remainingZombies;
	
	private int zombiesAlived;
	
	public ZombiesManager(GameWorld game, Level level, Random rand) {
		this.game = game;
		this.level = level;
		this.rand = rand;
		this.remainingZombies = level.getNumberOfZombies();
		this.zombiesAlived = 0;
	}

	/**
	 * Checks if the game should add (if possible) a zombie to the game.
	 * 
	 * @return <code>true</code> if a zombie should be added to the game.
	 */
	private boolean shouldAddZombie() {
		return rand.nextDouble() < level.getZombieFrequency();
	}

	/**
	 * Return a random row within the board limits.
	 * 
	 * @return a random row.
	 */
	private int randomZombieRow() {
		return rand.nextInt(GameWorld.NUM_ROWS);
	}

	private int randomZombieType() {
		return rand.nextInt(ZombieFactory.getAvailableZombies().size());
	}

	public void update() throws GameException {
		addZombie();
	}

	public boolean addZombie() throws GameException {
		int row = randomZombieRow();
		return addZombie(row);
	}

	public boolean addZombie(int row) throws GameException {
		boolean canAdd = areThereZombiesLeftToSpawn() && shouldAddZombie() && isPositionEmpty(GameWorld.NUM_COLS, row);
		int zombieType = randomZombieType();

		if (canAdd) {
			
			Zombie zombie = ZombieFactory.spawnZombie(zombieType, game, GameWorld.NUM_COLS, row);
			this.game.addItem(zombie);
			this.zombiesAlived++;
		}
		return canAdd;
	}


	private boolean isPositionEmpty(int numCols, int row) {
		return game.isPositionEmpty(numCols, row);
	}

	public int getRemainingZombies() {
		return remainingZombies;
	}
	
	public boolean areThereZombiesLeftToSpawn() {
		return zombiesAlived < level.getNumberOfZombies();
	}
	
	public int getZombiesAlived() {
		return zombiesAlived;
	}
	
	public boolean allZombiesDied() {
		return this.remainingZombies == 0 && this.zombiesAlived >= level.getNumberOfZombies();
	}
	
	public void zombieDied() {
		this.remainingZombies--;
	}
	
	public String getDescription() {
		return null;
	}

}
