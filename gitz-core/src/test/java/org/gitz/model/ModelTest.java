package org.gitz.model;

import org.gitz.process.GitzException;
import org.gitz.process.ModelLoader;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Tests for the loaded model
 */
public class ModelTest {

	private static final String MANIFEST_3 = "src/test/resources/manifests/example-3.xml";
	private static final String MANIFEST_5 = "src/test/resources/manifests/example-5.xml";

	private static final String BAD_SERVER_PATH = "src/test/resources/manifests/missing-server-path.xml";


	@Test
	public void testOKLoad() {

		ModelLoader loader = new ModelLoader();
		Manifest manifest = loader.load(MANIFEST_3);

		String expected = "Gitz!: 1.0\nCurrent manifest: " + MANIFEST_3 +
				"\nDescription:      Manifest to use /temp/test-[1-2] repos";

		assertEquals(manifest.getPostCommandTasks().size(), 2);

		List<PostCommandTaskEntry> cloneEntries = manifest.collectPostCommandTaskEntries("clone");
		assertEquals(cloneEntries.size(), 1);

		List<PostCommandTaskEntry> branchEntries = manifest.collectPostCommandTaskEntries("branch");
		assertEquals(branchEntries.size(), 2);

		assertEquals(expected, manifest.toDisplayString(MANIFEST_3));
	}


	@Test
	public void testOKNoBasePath() {

		ModelLoader loader = new ModelLoader();
		Manifest manifest = loader.load(MANIFEST_5);

		String expected = "Gitz!: 1.0\nCurrent manifest: " + MANIFEST_5 +
				"\nDescription:      empty base folder";

		assertEquals("./", manifest.getBaseFolder());
	}

	@Test (expected = GitzException.class)
	public void testBadNoServer() {

		ModelLoader loader = new ModelLoader();
		Manifest manifest = loader.load(BAD_SERVER_PATH);

	}
}
