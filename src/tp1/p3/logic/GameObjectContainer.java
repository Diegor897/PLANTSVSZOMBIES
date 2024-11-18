package tp1.p3.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.p3.logic.gameobjects.GameObject;
import tp1.p3.view.Messages;

public class GameObjectContainer {

	private List<GameObject> gameObjects;

	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
	}

	public String positionToString(int col, int row) {
		StringBuilder buffer = new StringBuilder();
		boolean sunPainted = false;
		boolean sunAboutToPaint = false;

		for (GameObject g : gameObjects) {
			if(g.isAlive() && g.getCol() == col && g.getRow() == row) {
				String objectText = g.toString();
				sunAboutToPaint = objectText.indexOf(Messages.SUN_SYMBOL) >= 0;
				if (sunAboutToPaint) {
					if (!sunPainted) {
						buffer.append(objectText);
						sunPainted = true;
					}
				} 
				else {
					buffer.append(objectText);
				}
			}
		}

		return buffer.toString();
	}

	public boolean removeDead() {
		List<GameObject> auxList = new ArrayList<GameObject>();
		for (GameObject aux : gameObjects) {
			if (!aux.isAlive()) {
				auxList.add(aux);
			}
		}	
		for (GameObject aux : auxList) {
			aux.onDelete();
			gameObjects.remove(aux);
		}
		return !(auxList.size() == 0);
	}

	public boolean isObjectInPosition(int col, int row) {
		return !getGameItemsInPosition(col, row).isEmpty();
	}


	public List<GameObject> getGameItemsInPosition(int col, int row) {
		List<GameObject> result = new ArrayList<GameObject>();
		for (GameObject aux : gameObjects) {
			if (aux.isInPosition(col, row)) {
				result.add(aux);
			}
		}	
		return result;
	}

	public void add(GameObject item) {
		item.onEnter();
		gameObjects.add(item);
		
	}

	public boolean isFullyOccupied(int col, int row) {
		int i = 0;
		boolean occupied = false;
		while(i < gameObjects.size() && !occupied) {
			GameObject g = gameObjects.get(i);
			if(g.isAlive() && g.isInPosition(col, row)) {
				occupied = g.fillPosition();
			}
			i++;
		}	
		return occupied;
	}

	public void update() {
		for(GameObject g : gameObjects) {
			if(g.isAlive()) {
				g.update(g); 
			
			}
		}
	}
	
	public boolean TryToCatchObject(int col, int row) {
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i).getCol() == col && gameObjects.get(i).getRow() == row && gameObjects.get(i).getSymbol() == "*") {
				gameObjects.get(i).catchObject();
				return true;
			}
		}
		return false;
	}
	
	public void clear() {
		gameObjects.clear();	
	}

}
