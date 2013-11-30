package org.gitz.interfaces;

/**
 * Interface to abstract some behaviour for post command tasks
 *
 */
public interface PostCommandTaskEntryType {

	/**
	 * Answer the folder to run the task in, given this manifest entry
	 *
	 * @param manifestEntry   ManifestEntryType
	 * @return   String
	 */
	String getRunFolder(ManifestEntryType manifestEntry);
}
