package org.gitz.helper;

import org.gitz.interfaces.CommandLineBuilderType;
import org.gitz.interfaces.ExecutableTaskType;
import org.gitz.interfaces.ManifestEntryType;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to build the command line to be executed.
 * <p/>
 * Loads a local ArrayList, then returns this as a String array - to be used by the Process call.
 */
public class CommandLineBuilder implements CommandLineBuilderType {

	private List<String> lineParts = new ArrayList();

	private ExecutableTaskType task;

	private ManifestEntryType manifestEntry;

	public CommandLineBuilder(ExecutableTaskType command) {
		this.task = command;
	}

	/**
	 * Entry point.
	 * <p/>
	 * Builds the common command line elements
	 */
	public void build(ManifestEntryType manifestEntry) {

		this.manifestEntry = manifestEntry;

		appendShell();
		appendCommand();
		appendArgs();

	}

	/**
	 * Create the required array from the command line created here
	 *
	 * @return String[]
	 */
	public String[] getCommandLine() {

		String line[] = new String[lineParts.size()];
		lineParts.toArray(line);

		return line;
	}


	private void appendArgs() {

		if (task.hasArgs()) {
			for (String elem : task.getArgs()) {
				addLine(elem);
			}
		}

	}

	/**
	 *	Protected to allow override for the Clone CLI builder
	 */
	protected void appendCommand() {
		// add an preceding binary, if needed - eg 'git'
		if (task.needsExecutable()) {
			addLine(task.getExecutable());
		}

		// then add the command, eg 'branch'
		for (String elem : task.getCommand()) {
			addLine(elem);
		}
	}

	protected void addLine(String line) {
		lineParts.add(line);
	}


	private void appendShell() {

		if (Utility.isWindows()) {
			addLine("cmd");
			addLine("/c");
		} else {
			addLine("sh");
			addLine("-c");
		}

	}

	protected ManifestEntryType getManifestEntry() {
		return manifestEntry;
	}

	protected ExecutableTaskType getTask() {
		return task;
	}
}
