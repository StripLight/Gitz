package org.gitz.interfaces;

/**
 * Interface for git commands to execute over the set of repositories
 * <p/>
 * Extends the ExecutableTaskType adding git task specifics
 */
public interface GitTaskType extends ExecutableTaskType {

	/**
	 * Answer true if the arguments passed to the git command are OK
	 *
	 * @return boolean
	 */
	boolean argsAreOK();

	/**
	 * Entry point
	 * <p/>
	 * Execute the command with the arguments passed.
	 * <p/>
	 * The return value is a return code, either from the CLI invocation, or a local error code if the arguments are invalid
	 *
	 * @param givenArgs String[]
	 * @return ReturnCode errorCode
	 */
	ReturnCode execute(String givenArgs[]);

	/**
	 * Answer the writer to write out the command output
	 *
	 * @return OutputWriterType
	 */
	OutputWriterType getWriter();

	/**
	 * Answer true if the command is quiet, ie it does not write the standard header for each repo
	 *
	 * @return boolean
	 */
	boolean isQuiet();

	/**
	 * Answer true if the git command has work to do.
	 * <p/>
	 * Can be over-ridden to perform a pre command check. Eg before a push a dry run is executed to see if there is
	 * work to push for that repository.
	 * <p/>
	 * Only used by Commit and Push commands currently
	 *
	 * @param manifestEntry ManifestEntryType
	 * @return boolean
	 */
	boolean hasWorkTodo(ManifestEntryType manifestEntry);

	/**
	 * Answer any generated error message - ie for invalid arguments
	 *
	 * @return String
	 */
	String getErrorMessage();

	/**
	 * Answer the name of the git command to run - eg 'branch'
	 *
	 * @return String
	 */
	String getCommandName();


}
