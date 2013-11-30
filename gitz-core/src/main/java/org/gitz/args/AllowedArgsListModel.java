package org.gitz.args;

import org.apache.log4j.Logger;
import org.gitz.model.Constants;

import java.util.HashSet;
import java.util.Set;

/**
 * Class to represent a set of arguments and it's allowed matching values.
 * <p/>
 * The args to check are passed as an array.
 */
public class AllowedArgsListModel extends AbstractArgsListModel {

	private static final Logger LOG = Logger.getLogger(AllowedArgsListModel.class);

	private Set<String> allowed = new HashSet<String>();

	private Set<String> mismatches = new HashSet<String>();

	public AllowedArgsListModel(Set<String> allowed) {
		this.allowed = allowed;

		getErrorDetails().append("Gitz!: un-allowed commands found: ");
	}

	public AllowedArgsListModel(Set<String> allowed, RequiredArgsListModel requiredArgsModel) {
		this(allowed);
		addRequired(requiredArgsModel);
	}

	private void addRequired(RequiredArgsListModel requiredArgsModel) {

		if (requiredArgsModel.isEmpty()) {
			return;
		}

		for (String[] elem : requiredArgsModel.getRequired()) {
			allowed.add(elem[0]);
			allowed.add(elem[1]);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Added "+ (requiredArgsModel.getRequired().size() * 2) +
				" required elements to allowed list..." );
		}
	}

	@Override
	public int match(String[] argsToTest) {

		for (String testArg : argsToTest) {
			if (isCommandArg(testArg)) {

				boolean argIsOK = false;
				for (String allowedElem : allowed) {
					if (testArg.equals(allowedElem)) {
						argIsOK = true;
					}
				}
				if (!argIsOK) {
					addMismatch(testArg);
				}
			}
		}

		if (hasMismatches()) {
			return Constants.ERROR;
		}
		return Constants.OK;
	}

	@Override
	public boolean hasMismatches() {
		return mismatches.size() > 0;
	}

	public Set<String> getMismatches() {
		return mismatches;
	}

	private void addMismatch(String failedArg) {

		mismatches.add(failedArg);

		getErrorDetails().append(failedArg);
		getErrorDetails().append(",");
	}
}
