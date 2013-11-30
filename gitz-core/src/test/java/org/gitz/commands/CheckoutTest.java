package org.gitz.commands;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Checkout command tests
 */
public class CheckoutTest extends AbstractCommandTest {

	@Test
	public void testOK() {

		String args[] = {"-b", "new-branch", "--quiet"};
		CheckoutCommand command = new CheckoutCommand(getManifest());
		command.setArgs(args);

		assertTrue(command.argsAreOK());

	}

	@Test
	public void testBad1() {

		String args[] = {"-f", "new-branch"};
		CheckoutCommand command = new CheckoutCommand(getManifest());
		command.setArgs(args);

		assertFalse(command.argsAreOK());
		assertEquals("Gitz!: disallowed commands found: -f", command.getErrorMessage());

	}
}
