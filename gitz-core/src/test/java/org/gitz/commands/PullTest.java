package org.gitz.commands;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Pull command tests
 */
public class PullTest extends AbstractCommandTest {

	@Test
	public void testOK() {

		String args[] = {"origin", "next"};
		PullCommand command = new PullCommand(getManifest());
		command.setArgs(args);

		assertTrue(command.argsAreOK());

	}

	@Test
	public void testBad1() {

		String args[] = {"origin", "next", "--edit"};
		PullCommand command = new PullCommand(getManifest());
		command.setArgs(args);

		assertFalse(command.argsAreOK());
		assertEquals("Gitz!: disallowed commands found: --edit", command.getErrorMessage());

	}
}
