package tp1.p3.control.commands;

import tp1.p3.control.Command;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.logic.GameWorld;
import tp1.p3.logic.gameobjects.Plant;
import tp1.p3.logic.gameobjects.PlantFactory;
import tp1.p3.view.Messages;

public class ListPlantsCommand extends Command {

	@Override
	protected String getName() {
		return Messages.COMMAND_LIST_NAME;
	}
	@Override
	protected String getShortcut() {
		return Messages.COMMAND_LIST_SHORTCUT;
	}
	@Override
	public String getDetails() {
		return Messages.COMMAND_LIST_DETAILS;
	}
	@Override
	public String getHelp() {
		return Messages.COMMAND_LIST_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException{
		StringBuilder buffer = new StringBuilder(Messages.AVAILABLE_PLANTS).append(Messages.LINE_SEPARATOR);
		for (Plant plant : PlantFactory.getAvailablePlants()) {
			/* @formatter:off */
			buffer.append(plant.getDescription()).append(Messages.LINE_SEPARATOR);
			/* @formatter:on */
		}
		System.out.println(buffer.toString());
		return false;
	}

}
