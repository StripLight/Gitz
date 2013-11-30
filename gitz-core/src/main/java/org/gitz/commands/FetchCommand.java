package org.gitz.commands;

import org.gitz.interfaces.ManifestType;

/**
 * Fetch command
 */
public class FetchCommand extends AbstractCommand {

	private static final String CMD[] = {"fetch"};

	public FetchCommand(ManifestType manifest) {
		super(manifest);
	}

	@Override
	public String[] getCommand() {
		return CMD;
	}

}
