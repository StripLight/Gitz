package org.gitz.commands;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Add command tests
 */
public class AddTest extends AbstractCommandTest {

	@Test
	public void testOK() {

		String args[] = {"-q", "./"};
		AddCommand command = new AddCommand(getManifest());
		command.setArgs(args);

		assertTrue(command.argsAreOK());

	}

	@Test
	public void testBad1() {

		String args[] = {"--edit", "./"};
		AddCommand command = new AddCommand(getManifest());
		command.setArgs(args);

		assertFalse(command.argsAreOK());
		assertEquals("Gitz!: disallowed commands found: --edit", command.getErrorMessage());

	}
}
