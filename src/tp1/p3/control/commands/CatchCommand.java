package tp1.p3.control.commands;

import tp1.p3.control.Command;
import tp1.p3.control.exceptions.GameException;
import tp1.p3.logic.GameWorld;
import tp1.p3.view.Messages;

public class CatchCommand extends Command {

	private static boolean caughtSunThisCycle = false;

	private int col;

	private int row;

	public CatchCommand() {
		caughtSunThisCycle = false;
	}
	
	@Override
	protected void newCycleStarted() {
		caughtSunThisCycle = false;
	}

	private CatchCommand(int col, int row) {
		this.col = col;
		this.row = row;
		caughtSunThisCycle = false;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_CATCH_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_CATCH_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_CATCH_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_CATCH_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		if(caughtSunThisCycle) {
			throw new GameException(Messages.SUN_ALREADY_CAUGHT);
		}
		
		if(!game.tryToCatchObject(col, row)) {
			throw new GameException(Messages.NO_CATCHABLE_IN_POSITION.formatted(col, row));
		}
		
		caughtSunThisCycle = true;
		return true;
	}

	@Override
	public Command create(String[] parameters) throws GameException  {
		if(parameters.length < 2) {
			throw new GameException(Messages.COMMAND_PARAMETERS_MISSING);
		}
		int col;
		int row;
		try {
			col = Integer.parseInt(parameters[0]);
			row = Integer.parseInt(parameters[1]);
					
		}catch(NumberFormatException nfe){
			throw new GameException(Messages.INVALID_POSITION);
		}
		
		return new CatchCommand(col, row);
		
	}

}
