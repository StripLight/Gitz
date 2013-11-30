package org.gitz.writers;

import org.gitz.interfaces.GitTaskType;
import org.gitz.interfaces.OutputWriterType;

/**
 * Class to ignore any out put request. Used in checker classes, which test output separately.
 */
public class NulWriter implements OutputWriterType {

	@Override
	public void print(String text, String repository, GitTaskType command) {
		return;
	}
}
