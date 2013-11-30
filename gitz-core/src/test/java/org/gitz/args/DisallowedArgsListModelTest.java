package org.gitz.args;

import org.gitz.model.Constants;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for disallowed commands.
 * <p/>
 * This uses the allowed model, but reverse the check
 */
public class DisallowedArgsListModelTest {


	@Test
	public void testOK() {

		Set<String> disallowed = new HashSet<String>();
		disallowed.add("-q");

		DisallowedArgsListModel disallowedArgsListModel = new DisallowedArgsListModel(disallowed);

		String testArgs[] = {"--message", "origin/master"};
		int result = disallowedArgsListModel.match(testArgs);

		assertEquals(Constants.OK, result);
		assertEquals("", disallowedArgsListModel.getErrorMessage());

	}

	@Test
	public void testOK2() {

		Set<String> disallowed = new HashSet<String>();
		disallowed.add("-q");

		DisallowedArgsListModel disallowedArgsListModel = new DisallowedArgsListModel(disallowed);

		String testArgs[] = {};
		int result = disallowedArgsListModel.match(testArgs);

		assertEquals(Constants.OK, result);
		assertEquals("", disallowedArgsListModel.getErrorMessage());

	}

	@Test
	public void testOK3() {

		Set<String> disallowed = new HashSet<String>();

		DisallowedArgsListModel disallowedArgsListModel = new DisallowedArgsListModel(disallowed);

		String testArgs[] = {"--message", "origin/master"};
		int result = disallowedArgsListModel.match(testArgs);

		assertEquals(Constants.OK, result);
		assertEquals("", disallowedArgsListModel.getErrorMessage());

	}

	@Test
	public void testBad1() {

		Set<String> disallowed = new HashSet<String>();
		disallowed.add("-q");

		DisallowedArgsListModel disallowedArgsListModel = new DisallowedArgsListModel(disallowed);

		String testArgs[] = {"-q", "origin/master"};
		int result = disallowedArgsListModel.match(testArgs);

		assertEquals(Constants.ERROR, result);
		assertEquals("Gitz!: disallowed commands found: -q", disallowedArgsListModel.getErrorMessage());
		assertTrue(disallowedArgsListModel.getMismatches().contains("-q"));

	}


}
