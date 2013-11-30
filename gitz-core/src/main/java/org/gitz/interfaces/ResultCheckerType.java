package org.gitz.interfaces;

/**
 * Interface for checker classes that test for any work to do for a given command
 */
public interface ResultCheckerType {

	/**
	 * Answer true if the current repository has work to do for the current command
	 *
	 * @param entry ManifestEntryType
	 * @return boolean
	 */
	boolean hasResults(ManifestEntryType entry);
}
