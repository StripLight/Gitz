package org.gitz.commands;

import org.gitz.args.ArgsListModelFactory;
import org.gitz.args.DisallowedArgsListModel;
import org.gitz.helper.CloneCommandLineBuilder;
import org.gitz.interfaces.CommandLineBuilderType;
import org.gitz.interfaces.ManifestType;
import org.gitz.model.Constants;

/**
 * Clone command
 */
public class CloneCommand extends AbstractCommand {

	private static final String CMD[] = {"clone"};

	// Disallow the 'dangerous' clone operations
	private static final String INVALID_ARGS[] = {"-s", "--shared", "--reference"};

	private DisallowedArgsListModel disallowedArgsListModel;

	public CloneCommand(ManifestType manifest) {
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

	@Override
	public boolean runsInBaseFolder() {
		return true;
	}


	@Override
	public CommandLineBuilderType getBuilder() {
		return new CloneCommandLineBuilder(this);
	}
}
