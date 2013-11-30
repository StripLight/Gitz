package org.gitz.model;

import org.gitz.helper.CommandLineBuilder;
import org.gitz.interfaces.CommandLineBuilderType;
import org.gitz.interfaces.ExecutableTaskType;
import org.gitz.interfaces.ManifestEntryType;
import org.gitz.interfaces.PostCommandTaskEntryType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A single task entry for a given git command - holds one task eg set some config to be
 * executed after a Git command - eg clone
 */
@XmlRootElement
public class PostCommandTaskEntry implements ExecutableTaskType, PostCommandTaskEntryType {

	private String commandLine;

	private String baseRunFolder;

	@XmlElement()
	public String getCommandLine() {
		return commandLine;
	}

	public void setCommandLine(String commandLine) {
		this.commandLine = commandLine;
	}

	public String getBaseRunFolder() {
		return baseRunFolder;
	}

	public void setBaseRunFolder(String baseRunFolder) {
		this.baseRunFolder = baseRunFolder;
	}

	/**
	 * No separate args for a task entry, everything to be run is loaded in
	 *
	 * @return
	 */
	@Override
	public String[] getArgs() {
		return new String[0];
	}

	@Override
	public String getRunFolder(ManifestEntryType manifestEntry) {
		return manifestEntry.getPath(this);
	}

	@Override
	public String[] getCommand() {
		return getCommandLine().split(" ");
	}

	@Override
	public boolean runsInBaseFolder() {
		return false;
	}

	@Override
	public CommandLineBuilderType getBuilder() {
		return new CommandLineBuilder(this);
	}

	/**
	 * Not relevant for task, as they have all they need in their command line
	 *
	 * @return null
	 */
	@Override
	public String getExecutable() {
		return null;
	}

	@Override
	public boolean needsExecutable() {
		return false;
	}

	@Override
	public boolean hasArgs() {
		return false;
	}


}
