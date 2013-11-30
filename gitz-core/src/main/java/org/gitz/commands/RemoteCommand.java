package org.gitz.commands;

import org.gitz.interfaces.ManifestType;

/**
 * Remote command
 */
public class RemoteCommand extends AbstractCommand {

	private static final String CMD[] = {"remote"};

	public RemoteCommand(ManifestType manifest) {
		super(manifest);
	}

	@Override
	public String[] getCommand() {
		return CMD;
	}

}
