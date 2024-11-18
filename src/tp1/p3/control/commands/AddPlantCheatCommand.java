package tp1.p3.control.commands;



import tp1.p3.control.Command;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.logic.GameWorld;
import tp1.p3.logic.gameobjects.Plant;
import tp1.p3.logic.gameobjects.PlantFactory;
import tp1.p3.view.Messages;

public class AddPlantCheatCommand extends Command implements Cloneable {
	
	private int col;

	private int row;

	private String plantName;

	
	public AddPlantCheatCommand(){
		super(true);
	}
	
	public AddPlantCheatCommand(String plantName, int col, int row) {
		super(true);
		this.plantName = plantName;
		this.col = col;
		this.row = row;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_CHEAT_PLANT_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_CHEAT_PLANT_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_CHEAT_PLANT_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_CHEAT_PLANT_HELP;
	}
	
	@Override
	public boolean execute(GameWorld game) throws GameException{
		// Se comprueba el nombre
		if(!PlantFactory.isValidPlant(plantName)) {
			System.out.println(plantName);
			throw new GameException(Messages.INVALID_GAME_OBJECT);
		}
		// Se comprueban las coordenadas
		else if(!game.isValidPlantPosition(col, row)) {
			throw new GameException(Messages.INVALID_POSITION.formatted(col, row));
		}
		// Se comprueba que haya hueco
		else if(!game.isPositionEmpty(col,row)){
			throw new GameException(Messages.INVALID_POSITION.formatted(col, row));
		}
		
		Plant plant = PlantFactory.spawnPlant(plantName, game, col, row);
		game.addItem(plant);
		return game.update();
	}

	@Override
	public Command create(String[] parameters) throws GameException {
		// Se comprueba que el comando se haya escrito bien
		if(parameters.length != 3) {
			throw new GameException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		AddPlantCheatCommand command = new AddPlantCheatCommand(parameters[0], Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]));
		if(PlantFactory.isValidPlant(plantName) && Integer.parseInt(parameters[1]) > -1 && Integer.parseInt(parameters[2]) > -1) {
			try {
				command = (AddPlantCheatCommand) clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		return command;
	}

}
