package org.gitz.model;

import org.gitz.helper.Utility;
import org.gitz.interfaces.ManifestType;
import org.gitz.process.GitzException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * The top level model class holding the manifest data
 */
@XmlRootElement
public class Manifest implements ManifestType {

	public static final String VERSION = "1.0";

	private String baseFolder;

	private String description;

	private String defaultServerPath;

	private List<PostCommandTask> postCommandTasks = new ArrayList<PostCommandTask>();

	private List<ManifestEntry> manifestEntries;


	@XmlElement()
	public String getBaseFolder() {
		return baseFolder;
	}

	public void setBaseFolder(String baseFolder) {
		this.baseFolder = baseFolder;
	}

	@XmlElement()
	public String getDefaultServerPath() {
		return defaultServerPath;
	}

	public void setDefaultServerPath(String defaultServerPath) {
		this.defaultServerPath = defaultServerPath;
	}

	@XmlElement()
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElementWrapper(name = "manifestEntries")
	@XmlElement(name = "entry", type = ManifestEntry.class)
	public List<ManifestEntry> getManifestEntries() {
		return manifestEntries;
	}

	public void setManifestEntries(List<ManifestEntry> manifestEntries) {
		this.manifestEntries = manifestEntries;
	}

	@XmlElementWrapper(name = "postCommandTasks")
	@XmlElement(name = "postCommandTask", type = PostCommandTask.class)
	public List<PostCommandTask> getPostCommandTasks() {
		return postCommandTasks;
	}

	public void setPostCommandTasks(List<PostCommandTask> postCommandTasks) {
		this.postCommandTasks = postCommandTasks;
	}


	/**
	 * Tie up parent child relationships
	 */
	public void postLoad() {

		validate();

		for (ManifestEntry entry : getManifestEntries()) {
			entry.setParentManifest(this);
			entry.postLoad();
		}

		for (PostCommandTask postCommandTask : getPostCommandTasks()) {
			postCommandTask.loadRunFolder(getBaseFolder());
		}

	}

	/**
	 * Collect all the task entries that match the given command
	 *
	 * @param command String
	 * @return List<PostCommandTaskEntry>
	 */
	public List<PostCommandTaskEntry> collectPostCommandTaskEntries(String command) {

		List<PostCommandTaskEntry> entries = new ArrayList();

		for (PostCommandTask task : getPostCommandTasks()) {
			if (task.matchesCommand(command)) {
				entries.addAll(task.getTaskEntries());
			}
		}
		return entries;
	}


	public String toDisplayString(String manifestFilepath) {

		StringBuilder sb = new StringBuilder("Gitz!: ");
		sb.append(VERSION);
		sb.append("\nCurrent manifest: ");
		sb.append(manifestFilepath);
		sb.append("\nDescription:      ");
		sb.append(getDescription());

		return sb.toString();
	}


	private void validate() {

		if (Utility.isEmpty(defaultServerPath)) {
			throw new GitzException("A default server path must be defined!");
		}

		if (Utility.isEmpty(baseFolder)) {
			baseFolder = "./";
		}

		// resolve any tokens, server path only
		defaultServerPath = Utility.resolveTokenisedValue(defaultServerPath);

		baseFolder = Utility.checkEndsWithSeparator(baseFolder);

	}

}
