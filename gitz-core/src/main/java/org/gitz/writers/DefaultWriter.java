package org.gitz.writers;

import org.gitz.helper.Utility;
import org.gitz.interfaces.GitTaskType;
import org.gitz.interfaces.OutputWriterType;

/**
 * Default output class, dumps the command output typically prefixed by the repo name
 */
public class DefaultWriter implements OutputWriterType {

	@Override
	public void print(String text, String repository, GitTaskType command) {

		if (Utility.isEmpty(text)) {
			return;
		}

		System.out.println(buildOutput(text, repository, command));
	}


	protected String buildOutput(String text, String repository, GitTaskType command) {

		StringBuilder builder = new StringBuilder();

		if (!command.isQuiet()) {
			builder.append("### Gitz!: repository: ");
			builder.append(repository);
			builder.append("\n");
		}
		builder.append(text);

		return builder.toString();
	}


}
