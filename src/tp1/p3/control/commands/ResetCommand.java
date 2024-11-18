package tp1.p3.control.commands;
import tp1.p3.control.Command;
import tp1.p3.control.Level;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public class ResetCommand extends Command {

	private Level level;

	private long seed;

	public ResetCommand() {
	}

	public ResetCommand(Level level, long seed) {
		this.level = level;
		this.seed = seed;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException{
		if(this.level != null) {
			game.reset(level,seed);
		}
		else {
			game.reset();
		}
		return true;
	}

	@Override
	public Command create(String[] parameters) throws GameException {
		if(parameters.length != 0 && parameters.length != 2 ) {
			throw new GameException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		
		if(parameters.length == 2) {
			String levelArgument = parameters[0];
			String seedArgument = parameters[1];
			
			Level level = Level.valueOfIgnoreCase(levelArgument);
			if(level == null) {
				throw new GameException(Messages.INVALID_COMMAND);
			}
			
			long seed = -1;
			try {
				seed = Long.parseLong(seedArgument);
			}catch(NumberFormatException nfe) {
				throw new GameException(Messages.INVALID_COMMAND);
			}
			return new ResetCommand(level,seed);
			
		}
		return this;
	}

}
