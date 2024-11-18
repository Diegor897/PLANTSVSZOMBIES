package tp1.p3.logic;

import java.util.List;
import java.util.Random;

import tp1.p3.logic.gameobjects.GameObject;
import tp1.p3.logic.gameobjects.Sun;

public class SunsManager {

	private static final int COOLDOWN_RANDOM_SUN = 5;

	private GameWorld game;

	private Random rand;

	private int cooldown;
	
	private static int generatedSuns = 0;
	
	private static int caughtSuns = 0;

	public SunsManager(GameWorld game, Random rand) {
		this.game = game;
		this.rand = rand;
		this.cooldown = COOLDOWN_RANDOM_SUN;
		resetCounters();
	}

	public static int getCaughtSuns() {
		return caughtSuns;
	}

	public static int getGeneratedSuns() {
		return generatedSuns;
	}
	
	public static void resetCounters() {
		generatedSuns = 0;
		caughtSuns = 0;
	}

	public static void setCaughtSuns(int caughtSuns) {
		SunsManager.caughtSuns = caughtSuns;
	}

	public void update() {
		if (cooldown == 0) {
			game.addSun();
			cooldown = COOLDOWN_RANDOM_SUN;
		} else {
			cooldown--;
		}
	}

	private int getRandomInt(int bound) {
		return this.rand.nextInt(bound);
	}

	public void addSun(List<GameObject> aux) {
        int col = getRandomInt(GameWorld.NUM_COLS);
        int row = getRandomInt(GameWorld.NUM_ROWS);
        aux.add(new Sun(this.game, col, row));
        generatedSuns++;
    }
}
