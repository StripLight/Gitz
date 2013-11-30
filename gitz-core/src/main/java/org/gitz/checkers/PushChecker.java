package org.gitz.checkers;

import org.apache.log4j.Logger;
import org.gitz.commands.checker.DryRunCommand;
import org.gitz.interfaces.ManifestEntryType;
import org.gitz.interfaces.ResultCheckerType;
import org.gitz.runner.DefaultCLIRunner;

/**
 * Checker class to perform a test push. Used to see if there is actually work to do for this repository.
 * <p/>
 * If the output does not match the expected nothing to do result, it is assumed there is work to do for this repo. No
 * other results are tested for...
 * <p/>
 * Used in the PushCommand
 */
public class PushChecker implements ResultCheckerType {

	private static final Logger LOG = Logger.getLogger(PushChecker.class);

	// Output from a push dry run command, guess this could be liable to change...
	private static final String TEST_RESULT = "EVERYTHING UP-TO-DATE";

	@Override
	public boolean hasResults(ManifestEntryType entry) {

		DryRunCommand pushCmd = new DryRunCommand(entry.getParentManifest());

		DefaultCLIRunner runner = new DefaultCLIRunner();
		String result = runner.execute(pushCmd, entry);

		if (result == null) {
			return false;
		}

		result = result.trim().toUpperCase();

		if (LOG.isDebugEnabled()) {
			LOG.debug("Checked push status, found: " + result);
		}

		return !result.equals(TEST_RESULT);
	}
}
