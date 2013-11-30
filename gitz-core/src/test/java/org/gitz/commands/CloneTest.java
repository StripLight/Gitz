package org.gitz.commands;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Clone command tests
 */
public class CloneTest extends AbstractCommandTest {

	@Test
	public void testOK() {

		String args[] = {"ssh://me@some.where:12345/repo.git", "repo"};
		CloneCommand command = new CloneCommand(getManifest());
		command.setArgs(args);

		assertTrue(command.argsAreOK());

	}

	@Test
	public void testBad1() {

		String args[] = {"ssh://me@some.where:12345/repo.git", "repo", "--shared"};
		CloneCommand command = new CloneCommand(getManifest());
		command.setArgs(args);

		assertFalse(command.argsAreOK());
		assertEquals("Gitz!: disallowed commands found: --shared", command.getErrorMessage());

	}
}
