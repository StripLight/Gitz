package org.gitz.args;

import org.gitz.model.Constants;

import java.util.HashSet;
import java.util.Set;

/**
 * Class to represent a set of arguments and it's allowed matching values.
 * <p/>
 * The args to check are passed as an array.
 */
public class RequiredArgsListModel extends AbstractArgsListModel {

	private Set<String[]> required = new HashSet<String[]>();

	private Set<String[]> mismatches = new HashSet<String[]>();

	public RequiredArgsListModel(Set<String[]> required) {
		this.required = required;
		getErrorDetails().append("Gitz!: Required commands missing, one of: ");
	}


	@Override
	public int match(String[] argsToTest) {

		// args must contain one of the required pairs
		int errorCount = 0;
		for (String[] requiredElem : required) {
			boolean found = false;

			for (String testArg : argsToTest) {
				if (isCommandArg(testArg)) {
					if (matches(testArg, requiredElem)) {
						found = true;
					}
				}
			}
			if (!found) {
				addMismatch(requiredElem);
				errorCount++;
			}
		}

		if (errorCount == 0) {
			return Constants.OK;
		}
		return Constants.ERROR;

	}

	@Override
	public boolean hasMismatches() {
		return mismatches.size() > 0;
	}

	public Set<String[]> getMismatches() {
		return mismatches;
	}

	public Set<String[]> getRequired() {
		return required;
	}

	public boolean isEmpty() {
		return required.isEmpty();
	}


	private boolean matches(String testArg, String[] requiredElem) {

		if (testArg.equals(requiredElem[0])) {
			return true;
		}
		if (testArg.equals(requiredElem[1])) {
			return true;
		}
		return false;
	}

	private void addMismatch(String[] requiredElem) {

		mismatches.add(requiredElem);

		getErrorDetails().append(requiredElem[0]);
		getErrorDetails().append("/");
		getErrorDetails().append(requiredElem[1]);

		getErrorDetails().append(",");
	}

}
