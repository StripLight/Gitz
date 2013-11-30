package org.gitz.interfaces;

/**
 * Interface for command line builds
 *
 * These generate the String[] array passed to a Process to execute the task
 */
public interface CommandLineBuilderType {

	/**
	 * Generate the command line
	 * @param	manifestEntry  ManifestEntryType
	 */
	void build(ManifestEntryType manifestEntry);

	/**
	 * Answer the generated command line
	 * @return	String[]
	 */
	String[] getCommandLine();

}
