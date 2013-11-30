package org.gitz.helper;

import org.gitz.interfaces.CommandLineBuilderType;
import org.gitz.interfaces.ExecutableTaskType;

/**
 * Specific command line builder for the clone command.
 * <p/>
 * This needs some extra data in the mix, so is provided as a different implementation.
 */
public class CloneCommandLineBuilder extends CommandLineBuilder implements CommandLineBuilderType {

	public CloneCommandLineBuilder(ExecutableTaskType command) {
		super(command);
	}

	/**
	 * Loads a revised command in for the clone command
	 */
	protected void appendCommand() {
		// add an preceding binary, if needed - eg 'git'
		if (getTask().needsExecutable()) {
			addLine(getTask().getExecutable());
		}

		for (String elem : getTask().getCommand()) {
			addLine(elem);
		}

		// add path to repo, plus any target name defined
		addLine(generateRepositoryPath());
		addLine(getManifestEntry().getTargetName());


	}

	private String generateRepositoryPath() {

		StringBuilder repoPath = new StringBuilder();

		repoPath.append(getManifestEntry().getParentManifest().getDefaultServerPath());
		repoPath.append(getManifestEntry().getRepositoryName());

		return repoPath.toString();

	}

}
