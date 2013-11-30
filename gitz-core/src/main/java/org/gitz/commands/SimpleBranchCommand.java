package org.gitz.commands;

import org.gitz.interfaces.ManifestType;
import org.gitz.interfaces.OutputWriterType;
import org.gitz.writers.SimpleBranchCommandWriter;

/**
 * Simple Branch command.
 * <p/>
 * This runs a cut down branch command to allow a quick snapshot of the branches running on a repo, so you might
 * get results like:
 * <p/>
 * repo1 -> master
 * repo2 -> next-working
 * repo3 -> master
 * <p/>
 * So you can quickly see repo2 is on a different branch from the others
 * <p/>
 * The magic is done by the different writer
 */
public class SimpleBranchCommand extends AbstractCommand {

	private static final String CMD[] = {"status", "-b"};

	public SimpleBranchCommand(ManifestType manifest) {
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

	@Override
	public OutputWriterType getWriter() {
		return new SimpleBranchCommandWriter();
	}

}
