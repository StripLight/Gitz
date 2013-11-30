package org.gitz.commands;

import org.gitz.args.AllowedArgsListModel;
import org.gitz.args.ArgsListModelFactory;
import org.gitz.args.RequiredArgsListModel;
import org.gitz.checkers.StatusChecker;
import org.gitz.interfaces.ManifestEntryType;
import org.gitz.interfaces.ManifestType;
import org.gitz.model.Constants;

/**
 * Commit command
 */
public class CommitCommand extends AbstractCommand {

	private static final String CMD[] = {"commit"};

	private static final String ALLOWED[] = {"--add", "-a", "-v", "--verbose", "-q", "--quiet"};
	private static final String REQUIRED[][] = {{"--message", "-m"}};

	private RequiredArgsListModel requiredArgsListModel;
	private AllowedArgsListModel allowedArgsListModel;

	public CommitCommand(ManifestType manifest) {
		super(manifest);

		requiredArgsListModel = ArgsListModelFactory.createRequired(REQUIRED);
		allowedArgsListModel = ArgsListModelFactory.createAllowed(ALLOWED, requiredArgsListModel);

	}

	@Override
	public String[] getCommand() {
		return CMD;
	}

	@Override
	public boolean argsAreOK() {

		int result = requiredArgsListModel.match(getArgs());
		if (result == Constants.ERROR) {
			setErrorMessage(requiredArgsListModel.getErrorMessage());
			return false;
		}
		result = allowedArgsListModel.match(getArgs());
		if (result == Constants.ERROR) {
			setErrorMessage(allowedArgsListModel.getErrorMessage());
			return false;
		}

		return true;
	}


	@Override
	public boolean hasWorkTodo(ManifestEntryType e) {

		StatusChecker statusChecker = new StatusChecker();
		return statusChecker.hasResults(e);
	}
}
