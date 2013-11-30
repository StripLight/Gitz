package org.gitz.checkers;

import org.apache.log4j.Logger;
import org.gitz.commands.checker.SimpleStatusCommand;
import org.gitz.helper.Utility;
import org.gitz.interfaces.ManifestEntryType;
import org.gitz.interfaces.ResultCheckerType;
import org.gitz.runner.DefaultCLIRunner;

/**
 * Checker class to see if there is work to do for a commit command.
 */
public class StatusChecker implements ResultCheckerType {

	private static final Logger LOG = Logger.getLogger(StatusChecker.class);


	@Override
	public boolean hasResults(ManifestEntryType entry) {

		SimpleStatusCommand ssc = new SimpleStatusCommand(entry.getParentManifest());

		DefaultCLIRunner runner = new DefaultCLIRunner();
		String result = runner.execute(ssc, entry);

		if (result == null) {
			return false;
		}

		result = result.trim().toUpperCase();

		if (LOG.isDebugEnabled()) {
			LOG.debug("Checked status, found:  " + result);
		}

		return Utility.isNotEmpty(result);
	}
}
