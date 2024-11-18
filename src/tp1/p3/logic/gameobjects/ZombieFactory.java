package tp1.p3.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p3.control.exceptions.GameException;
import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public class ZombieFactory {

	/* @formatter:off */
	private static final List<Zombie> AVAILABLE_ZOMBIES = Arrays.asList(
		new Zombie(),
		new BucketHead(),
		new Sporty(),
		new ExplosiveZombie()
	);
	/* @formatter:on */

	
	public static boolean isValidZombie(String zombieName){
		for(Zombie p: AVAILABLE_ZOMBIES) {
			String name = p.getName();
			String symbol = p.getSymbol();
			if(name.equalsIgnoreCase(zombieName) || symbol.equalsIgnoreCase(zombieName)) {
				return true;
		
			}
		}
		return false;
	}

	
	public static boolean isValidZombie(int zombieIdx) {
		return zombieIdx >= 0 && zombieIdx < AVAILABLE_ZOMBIES.size();
	}

	public static Zombie spawnZombie(int zombieIdx, GameWorld game, int col, int row) throws GameException  {
		if (!isValidZombie(zombieIdx)) {
			throw new GameException(Messages.INVALID_GAME_OBJECT);
		}
		return AVAILABLE_ZOMBIES.get(zombieIdx).copy(game, col, row, AVAILABLE_ZOMBIES.get(zombieIdx).getEndurance(), AVAILABLE_ZOMBIES.get(zombieIdx).getSpeed());
	}

	public static List<Zombie> getAvailableZombies() {
		return Collections.unmodifiableList(AVAILABLE_ZOMBIES);
	}

	/*
	 * Avoid creating instances of this class
	 */
	private ZombieFactory() {
	}
	
}
