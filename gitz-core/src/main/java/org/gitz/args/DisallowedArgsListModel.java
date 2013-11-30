package org.gitz.args;

import org.gitz.model.Constants;

import java.util.HashSet;
import java.util.Set;

/**
 * Class to represent a set of arguments and it's disallowed matching values.
 * <p/>
 * The args to check are passed as an array.
 */
public class DisallowedArgsListModel extends AbstractArgsListModel {

	private Set<String> disallowed = new HashSet<String>();

	private Set<String> mismatches = new HashSet<String>();

	public DisallowedArgsListModel(Set<String> disallowed) {
		this.disallowed = disallowed;
		getErrorDetails().append("Gitz!: disallowed commands found: ");
	}

	@Override
	public int match(String[] argsToTest) {

		for (String testArg : argsToTest) {
			if (isCommandArg(testArg)) {

				boolean argIsInDisallowedList = false;
				for (String disallowedElem : disallowed) {
					if (testArg.equals(disallowedElem)) {
						argIsInDisallowedList = true;
					}
				}
				if (argIsInDisallowedList) {
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
