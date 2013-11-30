package org.gitz.checkers;

import org.gitz.model.Manifest;
import org.gitz.process.ModelLoader;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;

/**
 * Runs the status checker against the Integration test suite
 *
 * Ensure the repos have been setup, enable the test when done
 *
 */
public class StatusCheckerTest {

	private static Manifest manifest;

	@BeforeClass
	public static void setup() {

		ModelLoader loader = new ModelLoader();
		manifest = loader.load("src/test/resources/manifests/local.xml");

	}

	@Test
	public void testWithNoLocalChanges() {

		StatusChecker checker = new StatusChecker();
		boolean result = checker.hasResults(manifest.getManifestEntries().get(0));
		assertFalse(result);

	}


}
