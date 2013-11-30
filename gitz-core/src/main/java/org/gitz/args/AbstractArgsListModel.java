package org.gitz.args;

import org.gitz.interfaces.ArgsListModelType;
import org.gitz.model.Constants;

/**
 * Common code for the argument list models
 */
public abstract class AbstractArgsListModel implements ArgsListModelType {

	private StringBuilder errorDetails = new StringBuilder();


	protected boolean isCommandArg(String testArg) {
		return testArg.startsWith(Constants.COMMAND_PREFIX);
	}

	protected StringBuilder getErrorDetails() {
		return errorDetails;
	}


	@Override
	public String getErrorMessage() {

		if (!hasMismatches()) {
			return "";
		}

		// remove trailing comma
		errorDetails.deleteCharAt(errorDetails.length() - 1);
		return errorDetails.toString();
	}

	/**
	 * Answer true if mismatches were found
	 *
	 * @return boolean
	 */
	public abstract boolean hasMismatches();

}
