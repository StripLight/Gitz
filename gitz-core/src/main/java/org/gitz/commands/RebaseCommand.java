package org.gitz.commands;

import org.gitz.args.AllowedArgsListModel;
import org.gitz.args.ArgsListModelFactory;
import org.gitz.interfaces.ManifestType;
import org.gitz.model.Constants;

/**
 * Rebase command
 */
public class RebaseCommand extends AbstractCommand {

	private static final String CMD[] = {"rebase"};

	private static final String ALLOWED[] = {"--stat", "-v", "--verbose", "-q", "--quiet"};

	private AllowedArgsListModel allowedArgsListModel;

	public RebaseCommand(ManifestType manifest) {
		super(manifest);
		allowedArgsListModel = ArgsListModelFactory.createAllowed(ALLOWED);

	}

	@Override
	public String[] getCommand() {
		return CMD;
	}

	@Override
	public boolean argsAreOK() {

		int result = allowedArgsListModel.match(getArgs());
		if (result == Constants.ERROR) {
			setErrorMessage(allowedArgsListModel.getErrorMessage());
			return false;
		}

		return true;
	}
}
