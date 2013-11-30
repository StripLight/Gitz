package org.gitz.writers;

import org.gitz.interfaces.GitTaskType;
import org.gitz.interfaces.OutputWriterType;

/**
 * Prints the output for the checker branch command
 */
public class SimpleBranchCommandWriter implements OutputWriterType {

	private static final String BRANCH_PREFIX = "# On branch";
	private static final char NEWLINE = '\n';

	@Override
	public void print(String text, String repository, GitTaskType command) {

		if (text.isEmpty()) {
			return;
		}

		System.out.println(buildOutput(text, repository));
	}

	protected String buildOutput(String text, String repository) {

		String workingBranch = text;

		int index = text.indexOf(BRANCH_PREFIX);
		if (index != -1) {
			// get the branch name
			int startIndex = index + BRANCH_PREFIX.length();
			int endIndex = text.indexOf(NEWLINE);

			workingBranch = text.substring(startIndex, endIndex).trim();
		}

		return repository + " -> " + workingBranch;

	}
}
