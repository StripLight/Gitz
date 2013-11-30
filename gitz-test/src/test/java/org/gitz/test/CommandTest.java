package org.gitz.test;

import org.gitz.cli.Gitz;
import org.gitz.cli.ReadManifest;
import org.gitz.model.Constants;
import org.gitz.model.Manifest;
import org.gitz.model.ManifestEntry;
import org.gitz.process.ModelLoader;
import org.junit.Ignore;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

/**
 * Test suite to exercise the command line execution against the local test repositories.
 *
 * These tests are disabled by default, as the setup process must have been followed.
 *
 * Create the repos and then enable
 */
@Ignore
public class CommandTest {

	@Test
	public void testStatus() {

		ModelLoader loader = new ModelLoader();
		Manifest manifest = loader.load("src/test/resources/manifests/local-repo.xml");

		Gitz gitz = new Gitz();
		String args[] = {"status"};
		gitz.setManifest(manifest);

		int result = gitz.run(args);
		assertEquals(0, result);

	}

	@Test
	public void testBranch() {

		ModelLoader loader = new ModelLoader();
		Manifest manifest = loader.load("src/test/resources/manifests/local-repo.xml");

		Gitz gitz = new Gitz();
		String args[] = {"branch", "-v"};
		gitz.setManifest(manifest);

		int result = gitz.run(args);
		assertEquals(0, result);

	}

	@Test
	public void testManifest() {

		String expected = "Gitz!: 1.0\nCurrent manifest: src/test/resources/manifests/local-repo.xml\n" +
				"Description:      Manifest for local and scruffy";

		Map testMap = new HashMap();
		testMap.put(Constants.MANIFEST_FILE_PATH, "src/test/resources/manifests/local-repo.xml");

		ReadManifest rm = new ReadManifest();
		rm.setRunEnvironment(testMap);

		rm.run();
		assertEquals(expected, rm.getRunOutput());

	}

	@Test
	public void testBadManifest() {

		Map testMap = new HashMap();
		testMap.put(Constants.MANIFEST_FILE_PATH, "src/test/resources/manifests/local-repo.xm");

		ReadManifest rm = new ReadManifest();
		rm.setRunEnvironment(testMap);

		rm.run();

		// will be empty for a failed run
		assertEquals("", rm.getRunOutput());

	}

	/**
	 * Test a clone of the local bare server.
	 */
	@Test
	public void testClone() {

		ModelLoader loader = new ModelLoader();
		Manifest manifest = loader.load("src/test/resources/manifests/clone-test.xml");

		/* 	Adds date/time to the target clone name for uniqueness
			This creates a new clone each time, consider some housekeeping...
		 */
		Date currentDate = new Date();
		String tempName = new SimpleDateFormat("yyMMddHHmmss").format(currentDate);

		ManifestEntry entry = manifest.getManifestEntries().get(0);
		entry.setTargetName(entry.getTargetName() + tempName);

		Gitz gitz = new Gitz();
		String args[] = {"clone"};
		gitz.setManifest(manifest);

		int result = gitz.run(args);
		assertEquals(0, result);

	}

}
