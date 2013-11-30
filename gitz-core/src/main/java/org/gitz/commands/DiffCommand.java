package org.gitz.commands;

import org.gitz.interfaces.ManifestType;

/**
 * Diff command
 */
public class DiffCommand extends AbstractCommand {

	private static final String CMD[] = {"diff"};

	public DiffCommand(ManifestType manifest) {
		super(manifest);
	}

	@Override
	public String[] getCommand() {
		return CMD;
	}

}
