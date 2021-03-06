package model.commands.math;

import model.commands.CommandException;

public class Minus extends Math {

	@Override
	public double getReturnValue() {
		return -parameters.get(0);
	}

	@Override
	public void validate() throws CommandException {
		if (parameters.size() != 1) {
			throw new CommandException("Invalid number of arguments: " + parameters.size());
		}
	}

}
