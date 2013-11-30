package org.gitz.args;

import org.gitz.model.Constants;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test the required args model
 */
public class RequiredArgsListModelTest {

	@Test
	public void testOK() {

		Set<String[]> required = new HashSet<String[]>();
		String[] r1 = {"-m", "--message"};
		required.add(r1);

		RequiredArgsListModel requiredArgsListModel = new RequiredArgsListModel(required);

		String testArgs[] = {"--message", "origin/master"};
		int result = requiredArgsListModel.match(testArgs);

		assertEquals(Constants.OK, result);
		assertEquals("", requiredArgsListModel.getErrorMessage());

	}

	@Test
	public void testMismatch1() {

		Set<String[]> required = new HashSet<String[]>();
		String[] r1 = {"-m", "--message"};
		required.add(r1);

		RequiredArgsListModel requiredArgsListModel = new RequiredArgsListModel(required);

		String testArgs[] = {"--massage", "origin/master"};
		int result = requiredArgsListModel.match(testArgs);

		assertEquals(Constants.ERROR, result);
		assertEquals("Gitz!: Required commands missing, one of: -m/--message", requiredArgsListModel.getErrorMessage());
		assertTrue(requiredArgsListModel.getMismatches().contains(r1));

	}

	@Test
	public void testMismatch2() {

		Set<String[]> required = new HashSet<String[]>();
		String[] r1 = {"-m", "--message"};
		String[] r2 = {"-a", "--add"};
		required.add(r1);
		required.add(r2);

		RequiredArgsListModel requiredArgsListModel = new RequiredArgsListModel(required);

		String testArgs[] = {"--message", "origin/master"};
		int result = requiredArgsListModel.match(testArgs);

		assertEquals(Constants.ERROR, result);
		assertEquals("Gitz!: Required commands missing, one of: -a/--add", requiredArgsListModel.getErrorMessage());
		assertTrue(requiredArgsListModel.getMismatches().contains(r2));

	}

	@Test
	public void testMismatch3() {

		Set<String[]> required = new HashSet<String[]>();
		String[] r2 = {"-a", "--add"};
		required.add(r2);

		RequiredArgsListModel requiredArgsListModel = new RequiredArgsListModel(required);

		String testArgs[] = {""};
		int result = requiredArgsListModel.match(testArgs);

		assertEquals(Constants.ERROR, result);
		assertEquals("Gitz!: Required commands missing, one of: -a/--add", requiredArgsListModel.getErrorMessage());
		assertTrue(requiredArgsListModel.getMismatches().contains(r2));

	}

	@Test
	public void testMismatch4() {

		Set<String[]> required = new HashSet<String[]>();
		String[] r1 = {"-m", "--message"};
		String[] r2 = {"-a", "--add"};
		required.add(r1);
		required.add(r2);

		RequiredArgsListModel requiredArgsListModel = new RequiredArgsListModel(required);

		String testArgs[] = {"origin/master", "-q"};
		int result = requiredArgsListModel.match(testArgs);

		assertEquals(Constants.ERROR, result);
		assertTrue(requiredArgsListModel.getMismatches().contains(r1));
		assertTrue(requiredArgsListModel.getMismatches().contains(r2));

	}


}
