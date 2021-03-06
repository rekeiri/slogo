package model.commands.math;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

/**
 * @author Martin
 *
 * Math commands are used to perform operations on the return values of other commands. Each math
 * command differs by what is given in their getReturnValue method.
 */
public abstract class Math extends Command {
	public static final double EQUAL_ERROR = .0000001;
	
	@Override
	public List<State> execute(List<State> states) throws CommandException {
		clearParameters();
		for (Command c : commands) {
			states = (c.execute(states));
			parameters.add(c.getReturnValue());
		}
		validate();
		return states;
	}
	
	@Override
	public List<State> groupExecute(List<State> states, List<Command> groupCommands) throws CommandException {
		clearParameters();
		states = groupCommands.get(0).execute(states);
		for (Command c : groupCommands.subList(1, groupCommands.size())) {
			states = c.execute(states);
			parameters.add(c.getReturnValue());
		}

		return states;
	}
}
