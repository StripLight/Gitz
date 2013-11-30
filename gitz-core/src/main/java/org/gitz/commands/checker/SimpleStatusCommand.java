package org.gitz.commands.checker;

import org.gitz.commands.AbstractCommand;
import org.gitz.interfaces.ManifestType;

/**
 * Simple status command
 * <p/>
 * Runs a cut down status command, and is used within the StatusChecker to detect work to be done.
 */
public class SimpleStatusCommand extends AbstractCommand {

	private static final String CMD[] = {"status", "-s"};

	public SimpleStatusCommand(ManifestType manifest) {
		super(manifest);
	}

	@Override
	public String[] getCommand() {
		return CMD;
	}

	@Override
	public boolean isQuiet() {
		return true;
	}
}
