package org.gitz.commands.checker;

import org.gitz.commands.AbstractCommand;
import org.gitz.interfaces.ManifestType;
import org.gitz.interfaces.OutputWriterType;
import org.gitz.writers.NulWriter;

/**
 * DryRun command
 * <p/>
 * Actually executes a push dry run. This command is actually used to check to see if a p0ush should be executed
 * for this repository.
 * <p/>
 * See the PushChecker.
 * <p/>
 * Output from this command is not written out, it is read by the PushChecker to see if there is pending
 * stuff to push
 */
public class DryRunCommand extends AbstractCommand {

	private static final String CMD[] = {"push", "--dry-run"};

	public DryRunCommand(ManifestType manifest) {
		super(manifest);
	}

	@Override
	public String[] getCommand() {
		return CMD;
	}

	@Override
	public OutputWriterType getWriter() {
		return new NulWriter();
	}
}
