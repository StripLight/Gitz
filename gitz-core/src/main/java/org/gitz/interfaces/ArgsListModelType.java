package org.gitz.interfaces;

/**
 * Interface for argument models
 */
public interface ArgsListModelType {

	/**
	 * Match the arguments against the underlying model
	 *
	 * @param argsToTest String[]
	 * @return int (0 or 1 for error)
	 */
	int match(String argsToTest[]);

	/**
	 * Answer an error message showing the invalid arguments
	 *
	 * @return String
	 */
	String getErrorMessage();
}
