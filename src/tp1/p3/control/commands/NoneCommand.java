package tp1.p3.control.commands;

import tp1.p3.control.Command;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public class NoneCommand extends Command {

	public NoneCommand() {
		// default command
		super(true);
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_NONE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_NONE_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_NONE_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_NONE_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		return game.update();
	}

}
