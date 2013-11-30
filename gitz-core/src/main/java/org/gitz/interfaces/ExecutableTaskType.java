package org.gitz.interfaces;

/**
 * Interface for a task that can be executed by Gitz.
 *
 * This can be either a Git task or anything else defined as a post command task
 */
public interface ExecutableTaskType {

	/**
	 * Answer the folder that the task is to be run in most run within the repo, but clone runs outside
	 * @param manifestEntry   ManifestEntryType
	 * @return    String
	 */
	String getRunFolder(ManifestEntryType manifestEntry);

	/**
	 * Answer the arguments loaded for the task
	 *
	 * @return	String[]
	 */
	String[] getArgs();

	/**
	 * Answer the parts of the base command to be run - eg status -b
	 * @return	String[]
	 */
	String[] getCommand();

	/**
	 * Answer true if the command runs in the base, rather than target repository folder
	 *
	 * @return	boolean
	 */
	boolean runsInBaseFolder();

	/**
	 * Answer the name of the executable to run.
	 *
	 * Only used where the task needs an executable.
	 *
	 * All git commands require this - the executable being 'git'
	 * @return	String
	 */
	String getExecutable();

	/**
	 * Answer true if this task requires an executable to run.
	 *
	 * @return	boolean
	 */
	boolean needsExecutable();

	/**
	 * Answer true if this command has arguments
	 *
	 * @return	boolean
	 */
	boolean hasArgs();

	/**
	 * Answer the command line builder for this task - this is only over-ridden by the clone task which needs to
	 * build its own command line
	 *
	 * @return	CommandLineBuilderType
	 */
	CommandLineBuilderType getBuilder();

}
