package model.commands.control;

import java.util.List;

import model.commands.Command;
import model.commands.CommandException;
import model.state.State;

public class MakeUserInstruction extends Command {
	private String commandName;
	private int returnval;

	@Override
	public List<State> execute(List<State> states) throws CommandException {
		clearParameters();
		validate();
		
		returnval = 0;
		
		commandName = ((StringCommand) commands.get(0)).getString();
		commandDictionary.addCommand(commandName, (ListOpen) commands.get(1), (ListOpen) commands.get(2));
		
		returnval = 1;
		
		return states;
		
	}

	@Override
	public double getReturnValue() {
		return returnval;
	}

	@Override
	protected void validate() throws CommandException {

	}
}
