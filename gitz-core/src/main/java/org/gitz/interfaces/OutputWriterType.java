package org.gitz.interfaces;

/**
 * Interface for the output writers.
 *
 * Used to print out command output
 */
public interface OutputWriterType {

	/**
	 * Print the output
	 *
	 * @param text	String output text
	 * @param repository	String repo name
	 * @param command  GitTaskType
	 */
	void print(String text, String repository, GitTaskType command);

}
