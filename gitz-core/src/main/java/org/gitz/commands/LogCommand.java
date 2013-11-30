package org.gitz.commands;

import org.gitz.interfaces.ManifestType;

/**
 * Log command
 */
public class LogCommand extends AbstractCommand {

	private static final String CMD[] = {"log"};

	public LogCommand(ManifestType manifest) {
		super(manifest);
	}

	@Override
	public String[] getCommand() {
		return CMD;
	}

}
