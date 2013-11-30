package org.gitz.commands;

import org.gitz.args.ArgsListModelFactory;
import org.gitz.args.DisallowedArgsListModel;
import org.gitz.interfaces.ManifestType;
import org.gitz.model.Constants;

/**
 * Add command
 */
public class AddCommand extends AbstractCommand {

    private static final String CMD[] = {"add"};

    private static final String INVALID_ARGS[] = {"--interactive", "-i", "--patch", "-p", "--edit", "-e"};

	private DisallowedArgsListModel disallowedArgsListModel;

    public AddCommand(ManifestType manifest) {
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
