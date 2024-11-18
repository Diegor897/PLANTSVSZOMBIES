package tp1.p3.control.commands;


import tp1.p3.control.Command;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.logic.GameWorld;
import tp1.p3.logic.gameobjects.Plant;
import tp1.p3.logic.gameobjects.PlantFactory;
import tp1.p3.view.Messages;

public class AddPlantCommand extends Command implements Cloneable {

	private int col;

	private int row;

	private String plantName;

	@SuppressWarnings("unused")
	private boolean consumeCoins;

	public AddPlantCommand() {
		this(true);
	}

	public AddPlantCommand(boolean consumeCoins) {
		this.consumeCoins = consumeCoins;
	}

	public AddPlantCommand(String plantName, int col, int row) {
		super(true);
		this.plantName = plantName;
		this.col = col;
		this.row = row;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_HELP;
	}


	@Override
	public boolean execute(GameWorld game) throws GameException {
		if(!PlantFactory.isValidPlant(plantName)) {
			throw new GameException(Messages.INVALID_GAME_OBJECT);
		}
		// Se comprueban las coordenadas
		else if(!game.isValidPlantPosition(col, row)) {
			throw new GameException(Messages.INVALID_POSITION.formatted(col, row));
		}
		else if(!game.isPositionEmpty(col, row)){
			throw new GameException(Messages.INVALID_POSITION.formatted(col, row));
		}
		else {
			Plant plant = PlantFactory.spawnPlant(plantName, game, col, row);
			if(game.tryToBuy(plant.getCost())) {
				game.addItem(plant);
				return game.update();
				
			}		
			throw new GameException(Messages.NOT_ENOUGH_COINS);
	
		}
	}

	@Override
	public Command create(String[] parameters) throws GameException {
		if(parameters.length != 3) {
			throw new GameException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		AddPlantCommand command = new AddPlantCommand(parameters[0], Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]));
		
		if(PlantFactory.isValidPlant(plantName) && Integer.parseInt(parameters[1]) > -1 && Integer.parseInt(parameters[2]) > -1) {
			try {
				command = (AddPlantCommand) clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		return command;
		
	}

}