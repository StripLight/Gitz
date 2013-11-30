package org.gitz.args;

import org.gitz.model.Constants;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;

/**
 * test the factory class
 */
public class ArgsListModelFactoryTest {


	@Test
	public void testAllowed() {

		String allowed[] = {"-q", "-v"};
		AllowedArgsListModel allowedArgsListModel = ArgsListModelFactory.createAllowed(allowed);

		String testArgs[] = {"-v", "origin/master"};
		int result = allowedArgsListModel.match(testArgs);

		assertEquals(Constants.OK, result);
		assertEquals("", allowedArgsListModel.getErrorMessage());
	}

	@Test
	public void testDisallowed() {

		String disallowed[] = {"-D"};
		DisallowedArgsListModel disallowedArgsListModel = ArgsListModelFactory.createDisallowed(disallowed);

		String testArgs[] = {"-v", "origin/master"};
		int result = disallowedArgsListModel.match(testArgs);

		assertEquals(Constants.OK, result);
		assertEquals("", disallowedArgsListModel.getErrorMessage());
	}


	@Test
	public void testAllowedRequired() {

		String required[][] = {{"-m", "--message"}};
		String allowed[] = {"-q", "--verbose"};

		AllowedArgsListModel allowedArgsListModel = ArgsListModelFactory.createAllowed(allowed, required);

		String testArgs[] = {"--message", "origin/master"};
		int result = allowedArgsListModel.match(testArgs);

		assertEquals(Constants.OK, result);
		assertEquals("", allowedArgsListModel.getErrorMessage());

	}

	@Test
	public void testAllowedRequiredModel() {

		String allowed[] = {"-q", "--verbose"};

		Set<String[]> required = new HashSet<String[]>();
		String[] r1 = {"-m", "--message"};
		required.add(r1);

		RequiredArgsListModel requiredArgsListModel = new RequiredArgsListModel(required);
		AllowedArgsListModel allowedArgsListModel = ArgsListModelFactory.createAllowed(allowed, requiredArgsListModel);

		String testArgs[] = {"--message", "origin/master"};
		int result = allowedArgsListModel.match(testArgs);

		assertEquals(Constants.OK, result);
		assertEquals("", allowedArgsListModel.getErrorMessage());

	}

}
