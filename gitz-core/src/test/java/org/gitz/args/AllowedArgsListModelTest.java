package org.gitz.args;

import org.gitz.model.Constants;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for allowed commands. Any given command may also be a required command, so this set is also checked
 */
public class AllowedArgsListModelTest {


	@Test
	public void testOKFirstRequired() {

		Set<String[]> required = new HashSet<String[]>();
		String[] r1 = {"-m", "--message"};
		required.add(r1);
		RequiredArgsListModel requiredArgsListModel = new RequiredArgsListModel(required);

		Set<String> allowed = new HashSet<String>();
		allowed.add("-q");

		AllowedArgsListModel allowedArgsListModel = new AllowedArgsListModel(allowed, requiredArgsListModel);

		String testArgs[] = {"-m", "origin/master"};
		int result = allowedArgsListModel.match(testArgs);

		assertEquals(Constants.OK, result);
		assertEquals("", allowedArgsListModel.getErrorMessage());

	}

	@Test
	public void testOKSecondRequired() {

		Set<String[]> required = new HashSet<String[]>();
		String[] r1 = {"-m", "--message"};
		required.add(r1);
		RequiredArgsListModel requiredArgsListModel = new RequiredArgsListModel(required);

		Set<String> allowed = new HashSet<String>();
		allowed.add("-q");

		AllowedArgsListModel allowedArgsListModel = new AllowedArgsListModel(allowed, requiredArgsListModel);

		String testArgs[] = {"--message", "origin/master"};
		int result = allowedArgsListModel.match(testArgs);

		assertEquals(Constants.OK, result);
		assertEquals("", allowedArgsListModel.getErrorMessage());

	}

	@Test
	public void testOKAllowed() {

		Set<String> allowed = new HashSet<String>();
		allowed.add("-m");

		AllowedArgsListModel allowedArgsListModel = new AllowedArgsListModel(allowed);

		String testArgs[] = {"-m", "origin/master"};
		int result = allowedArgsListModel.match(testArgs);

		assertEquals(Constants.OK, result);
		assertEquals("", allowedArgsListModel.getErrorMessage());

	}


	@Test
	public void testBad1() {

		Set<String[]> required = new HashSet<String[]>();
		String[] r1 = {"-m", "--message"};
		required.add(r1);
		RequiredArgsListModel requiredArgsListModel = new RequiredArgsListModel(required);


		Set<String> allowed = new HashSet<String>();
		allowed.add("-q");

		AllowedArgsListModel allowedArgsListModel = new AllowedArgsListModel(allowed, requiredArgsListModel);

		String testArgs[] = {"--massage", "origin/master"};
		int result = allowedArgsListModel.match(testArgs);

		assertEquals(Constants.ERROR, result);
		assertEquals("Gitz!: un-allowed commands found: --massage", allowedArgsListModel.getErrorMessage());

	}

	@Test
	public void testBad2() {

		Set<String[]> required = new HashSet<String[]>();
		String[] r1 = {"-m", "--message"};
		required.add(r1);
		RequiredArgsListModel requiredArgsListModel = new RequiredArgsListModel(required);


		Set<String> allowed = new HashSet<String>();
		allowed.add("-q");

		AllowedArgsListModel allowedArgsListModel = new AllowedArgsListModel(allowed, requiredArgsListModel);

		String testArgs[] = {"--massage", "origin/master", "--bad"};
		int result = allowedArgsListModel.match(testArgs);

		assertEquals(Constants.ERROR, result);
		assertTrue(allowedArgsListModel.getMismatches().contains("--bad"));
		assertTrue(allowedArgsListModel.getMismatches().contains("--massage"));

	}


}
