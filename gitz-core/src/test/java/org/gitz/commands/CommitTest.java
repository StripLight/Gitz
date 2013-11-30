package org.gitz.commands;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for commit
 */
public class CommitTest extends AbstractCommandTest {

	@Test
	public void testQuietMessageVerbose() {

		String args[] = {"-q", "-m", "--verbose"};
		CommitCommand command = new CommitCommand(getManifest());
		command.setArgs(args);
		assertTrue(command.argsAreOK());

	}

	@Test
	public void testAdd() {

		String args[] = {"-a"};
		CommitCommand command = new CommitCommand(getManifest());
		command.setArgs(args);

		// required is missing
		assertFalse(command.argsAreOK());

	}

	@Test
	public void testMessage() {

		String args[] = {"-m"};
		CommitCommand command = new CommitCommand(getManifest());
		command.setArgs(args);
		assertTrue(command.argsAreOK());

	}

	@Test
	public void testAmend() {

		/// not allowed across all repos...
		String args[] = {"--amend"};
		CommitCommand command = new CommitCommand(getManifest());
		command.setArgs(args);
		assertFalse(command.argsAreOK());

	}

	@Test
	public void testNoArgs() {

		/// must provide at least -m
		String args[] = {};
		CommitCommand command = new CommitCommand(getManifest());
		command.setArgs(args);
		assertFalse(command.argsAreOK());
		assertEquals("Gitz!: Required commands missing, one of: --message/-m", command.getErrorMessage());

	}
}
