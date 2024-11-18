package tp1.p3.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p3.control.exceptions.GameException;
import tp1.p3.logic.GameWorld;

public class PlantFactory {

	/* @formatter:off */
	private static final List<Plant> AVAILABLE_PLANTS = Arrays.asList(
		new Sunflower(),
		new Peashooter(),
		new WallNut(),
		new CherryBomb()
	);
	/* @formatter:on */

	public static boolean isValidPlant(String plantName){
		for (Plant p : AVAILABLE_PLANTS) {
			String name = p.getName();
			String symbol = p.getSymbol();
			if(name.equalsIgnoreCase(plantName) || symbol.equalsIgnoreCase(plantName)){
				return true;		
			}
		}
		return false;
	}

	public static Plant spawnPlant(String plantName, GameWorld game, int col, int row) throws GameException {
		for(Plant p: AVAILABLE_PLANTS) {
			String name = p.getName();
			String symbol = p.getSymbol();
			if(name.equalsIgnoreCase(plantName) || symbol.equalsIgnoreCase(plantName)){
				return p.copy(game, col, row);
            }
		}
		return null;
	}

	public static Iterable<Plant> getAvailablePlants() {
		return Collections.unmodifiableList(AVAILABLE_PLANTS);
	}

	/*
	 * Avoid creating instances of this class
	 */
	private PlantFactory() {
	}
}
