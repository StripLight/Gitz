package org.gitz.commands;

import org.gitz.model.Manifest;
import org.gitz.process.ModelLoader;
import org.junit.BeforeClass;

/**
 * Common parent test class for commands
 */
public class AbstractCommandTest {

	private static Manifest manifest;

	@BeforeClass
	public static void setup() {

		ModelLoader loader = new ModelLoader();
		manifest = loader.load("src/test/resources/manifests/example-1.xml");

	}

	protected static Manifest getManifest() {
		return manifest;
	}

}
