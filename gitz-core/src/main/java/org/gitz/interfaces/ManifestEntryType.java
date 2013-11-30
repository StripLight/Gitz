package org.gitz.interfaces;

/**
 * Interface for a manifest element. Used to abstract away the behavioural model
 *
 */
public interface ManifestEntryType {


	/**
	 * Answer the run path for the manifest entry, in the context of this task
	 *
	 * @param task	 ExecutableTaskType
	 * @return	String
	 */
	String getPath(ExecutableTaskType task);

	/**
	 * A post model load hook
	 */
	void postLoad();

	/**
	 * Answers the name of the repository
	 *
	 * @return	String
	 */
	String getRepositoryName();

	/**
	 *	Answers the target repo name - ie the local file system name
	 *
	 * @return	String
	 */
	String getTargetName();

	/**
	 * Answrer the parent manifest
	 * @return  ManifestType
	 */
	ManifestType getParentManifest();

}
