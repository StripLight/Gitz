package org.gitz.checkers;

import org.gitz.model.Manifest;
import org.gitz.model.PostCommandTaskEntry;
import org.gitz.process.ModelLoader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * Runs the push checker against the Integration test suite
 *
 * Ensure the repos have been setup, enable the test when done
 */
public class PushCheckerTest {

	private static Manifest manifest;

	@BeforeClass
	public static void setup() {

		ModelLoader loader = new ModelLoader();
		manifest = loader.load("src/test/resources/manifests/local.xml");

	}

	@Test
	public void testWithNoLocalChanges() {

		PushChecker checker = new PushChecker();
		boolean result = checker.hasResults(manifest.getManifestEntries().get(0));
		assertFalse(result);

	}


}
