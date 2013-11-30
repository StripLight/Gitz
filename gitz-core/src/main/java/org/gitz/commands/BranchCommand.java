package org.gitz.commands;

import org.gitz.args.ArgsListModelFactory;
import org.gitz.args.DisallowedArgsListModel;
import org.gitz.interfaces.ManifestType;
import org.gitz.model.Constants;

/**
 * Branch command
 */
public class BranchCommand extends AbstractCommand {

	private static final String CMD[] = {"branch"};

	// disallow a forced Delete/move of a branch
	private static final String INVALID_ARGS[] = {"-D", "-M"};

	private DisallowedArgsListModel disallowedArgsListModel;


	public BranchCommand(ManifestType manifest) {
		super(manifest);
		disallowedArgsListModel = ArgsListModelFactory.createDisallowed(INVALID_ARGS);
	}

	@Override
	public String[] getCommand() {
		return CMD;
	}

	@Override
	public boolean argsAreOK() {

		int result = disallowedArgsListModel.match(getArgs());
		if (result == Constants.ERROR) {
			setErrorMessage(disallowedArgsListModel.getErrorMessage());
			return false;
		}
		return true;
	}
}
