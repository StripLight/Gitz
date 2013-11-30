package org.gitz.commands;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Rebase command tests
 */
public class RebaseTest extends AbstractCommandTest {

	@Test
	public void testOK() {

		String args[] = {"origin/master", "--verbose"};
		RebaseCommand command = new RebaseCommand(getManifest());
		command.setArgs(args);

		assertTrue(command.argsAreOK());

	}

	@Test
	public void testBad1() {

		String args[] = {"--interactive", "--skip"};
		RebaseCommand command = new RebaseCommand(getManifest());
		command.setArgs(args);

		assertFalse(command.argsAreOK());
		assertEquals("Gitz!: un-allowed commands found: --interactive,--skip", command.getErrorMessage());

	}
}
