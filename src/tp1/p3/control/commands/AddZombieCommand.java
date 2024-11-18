package tp1.p3.control.commands; 

import tp1.p3.control.Command;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.logic.GameWorld;
import tp1.p3.logic.gameobjects.Zombie;
import tp1.p3.logic.gameobjects.ZombieFactory;
import tp1.p3.view.Messages;

public class AddZombieCommand extends Command implements Cloneable{

	private int zombieIdx;

	private int col;

	private int row;

	public AddZombieCommand() {

	}

	private AddZombieCommand(int zombieIdx, int col, int row) {
		super(true);
		this.zombieIdx = zombieIdx;
		this.col = col;
		this.row = row;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_ZOMBIE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_ZOMBIE_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_ZOMBIE_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_ZOMBIE_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		// Se comprueba el nombre
		if(!ZombieFactory.isValidZombie(zombieIdx)) {
			System.out.println(zombieIdx);
			throw new GameException(Messages.INVALID_GAME_OBJECT);
		}
		// Se comprueban las coordenadas
		if(!game.isValidZombiePosition(col, row)) {
			throw new GameException(Messages.INVALID_POSITION.formatted(col, row));
		}
		// Se comprueba que haya hueco
		if(!game.isPositionEmpty(col,row)){
			throw new GameException(Messages.INVALID_POSITION.formatted(col, row));
		}
		Zombie zombie = ZombieFactory.spawnZombie(zombieIdx, game, col, row);
		game.addItem(zombie);
		return game.update();
		 
	}

	
	public Command create(String[] parameters) throws GameException {
		// Se comprueba que el comando se haya escrito bien
		if(parameters.length != 3) {
			throw new GameException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		AddZombieCommand command = new AddZombieCommand(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]));
		try {
			command = (AddZombieCommand) clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return command;
	}
	
}
