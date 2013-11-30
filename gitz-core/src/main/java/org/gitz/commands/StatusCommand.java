package org.gitz.commands;

import org.gitz.interfaces.ManifestType;

/**
 * Status command
 */
public class StatusCommand extends AbstractCommand {

	private static final String CMD[] = {"status"};

	public StatusCommand(ManifestType manifest) {
		super(manifest);
	}

	@Override
	public String[] getCommand() {
		return CMD;
	}

}
