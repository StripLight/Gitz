package org.gitz.interfaces;

/**
 * Interface for command line runner, ie the class that encapsulates the os call to run the git command
 */
public interface CLIRunnerType {


	/**
	 * Entry point
	 *
	 * Execute the command for the given manifest entry
	 *
	 * Answer the output from the command
	 *
	 * @param cmd	 GitTaskType
	 * @param manifestEntry  ManifestEntryType
	 * @return   String
	 */
	String execute(GitTaskType cmd, ManifestEntryType manifestEntry);

}
