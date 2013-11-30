package org.gitz.commands;

import org.gitz.checkers.PushChecker;
import org.gitz.interfaces.ManifestEntryType;
import org.gitz.interfaces.ManifestType;

/**
 * Push command
 */
public class PushCommand extends AbstractCommand {

	private static final String CMD[] = {"push"};

	public PushCommand(ManifestType manifest) {
		super(manifest);
	}

	@Override
	public String[] getCommand() {
		return CMD;
	}

	@Override
	public boolean hasWorkTodo(ManifestEntryType e) {

		PushChecker checker = new PushChecker();
		return checker.hasResults(e);
	}
}
