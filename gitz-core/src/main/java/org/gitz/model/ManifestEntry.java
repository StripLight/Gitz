package org.gitz.model;

import org.gitz.helper.Utility;
import org.gitz.interfaces.ExecutableTaskType;
import org.gitz.interfaces.ManifestEntryType;
import org.gitz.interfaces.ManifestType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class to model an individual manifest entry
 */
@XmlRootElement
public class ManifestEntry implements ManifestEntryType {

	private String repositoryName;

	private String targetName;

	private Manifest parentManifest;

	@XmlElement(name = "repository")
	public String getRepositoryName() {
		return repositoryName;
	}

	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}

	@XmlElement()
	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public ManifestType getParentManifest() {
		return parentManifest;
	}

	public void setParentManifest(Manifest parentManifest) {
		this.parentManifest = parentManifest;
	}


	/**
	 * Double dispatch this to find the right run path for the given task
	 *
	 * @param task ExecutableTaskType
	 * @return String
	 */
	public String getPath(ExecutableTaskType task) {

		if (task.runsInBaseFolder()) {
			return Utility.normalizePath(getParentManifest().getBaseFolder());
		} else {
			return Utility.buildPath(getParentManifest().getBaseFolder(), getTargetName());
		}
	}


	/**
	 * Perform any post load clean up.
	 * <p/>
	 * Specifically adjust the target path, if missing.
	 */
	public void postLoad() {

		if (Utility.isEmpty(getTargetName())) {

			String workingName = getRepositoryName();

			// assume this should be stripped of any path to just use the repo name
			if (Utility.hasEmbeddedPath(workingName)) {
				setTargetName(Utility.extractRepositoryName(workingName));
			} else {
				setTargetName(workingName);
			}
		}

	}


}
