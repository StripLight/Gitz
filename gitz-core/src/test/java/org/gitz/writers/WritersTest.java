package org.gitz.writers;


import org.gitz.commands.StatusCommand;
import org.gitz.interfaces.ManifestEntryType;
import org.gitz.model.Manifest;
import org.gitz.process.ModelLoader;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Test the writer for the SimpleBranchCommand
 *
 */
public class WritersTest {

	private static final String STATUS = "# On branch master\nnothing to commit (working directory clean)";


	@Test
	public void testSimpleBranchCommandWriter() {

		SimpleBranchCommandWriter writer = new SimpleBranchCommandWriter();

		String output = writer.buildOutput(STATUS, "local");
		assertEquals("local -> master", output);

	}

	@Test
	public void testDefaultWriter() {

		String expected = "### Gitz!: repository: local\n# On branch master\nnothing to commit (working directory clean)";

		DefaultWriter writer = new DefaultWriter();

		ModelLoader loader = new ModelLoader();
		Manifest manifest = loader.load("src/test/resources/manifests/example-1.xml");

		String output = writer.buildOutput(STATUS, "local", new StatusCommand(manifest));
		assertEquals(expected, output);

	}


}
