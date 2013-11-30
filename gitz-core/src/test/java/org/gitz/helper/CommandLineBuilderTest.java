package org.gitz.helper;

import org.gitz.Assert;
import org.gitz.commands.CloneCommand;
import org.gitz.commands.StatusCommand;
import org.gitz.commands.checker.SimpleStatusCommand;
import org.gitz.model.Manifest;
import org.gitz.model.ManifestEntry;
import org.gitz.process.ModelLoader;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Test class for command line builders
 */
public class CommandLineBuilderTest {

	private static Manifest manifest;

	private static ManifestEntry manifestEntry;

	@BeforeClass
	public static void setup() {

		ModelLoader loader = new ModelLoader();
		manifest = loader.load("src/test/resources/manifests/example-1.xml");

		manifestEntry = manifest.getManifestEntries().get(0);

	}

	@Test
	public void testStatus() {

		String toAdd[] = {"git", "status"};
		String expected[] = getExpected(toAdd);
		StatusCommand command = new StatusCommand(manifest);
		CommandLineBuilder commandLineBuilder = new CommandLineBuilder(command);

		commandLineBuilder.build(manifestEntry);
		String result[] = commandLineBuilder.getCommandLine();
		assertEquals(4, result.length);

		Assert.assertArray(expected, result);
	}


	@Test
	public void testSimpleStatus() {

		String toAdd[] = {"git", "status", "-s"};

		String expected[] = getExpected(toAdd);
		SimpleStatusCommand command = new SimpleStatusCommand(manifest);
		CommandLineBuilder commandLineBuilder = new CommandLineBuilder(command);

		commandLineBuilder.build(manifestEntry);
		String result[] = commandLineBuilder.getCommandLine();
		assertEquals(5, result.length);

		Assert.assertArray(expected, result);
	}


	@Test
	public void testClone() {

		String toAdd[] = {"git", "clone", "https://github.com/blynn/gitmagic", "git-magic"};
		String expected[] = getExpected(toAdd);
		CloneCommand command = new CloneCommand(manifest);
		CloneCommandLineBuilder commandLineBuilder = new CloneCommandLineBuilder(command);

		commandLineBuilder.build(manifestEntry);
		String result[] = commandLineBuilder.getCommandLine();
		assertEquals(6, result.length);

		Assert.assertArray(expected, result);
	}

	private String[] getExpected(String[] toAdd) {

		List<String> result = new ArrayList();

		int i = 0;
		if (Utility.isWindows()) {
			result.add("cmd");
			result.add("/c");
		} else {
			result.add("sh");
			result.add("-c");
		}

		for (int j = 0; j < toAdd.length; j++) {
			if (Utility.isEmpty(toAdd[j])) {
				continue;
			}
			result.add(toAdd[j]);
		}

		String answer[] = new String[result.size()];
		result.toArray(answer);
		return answer;


	}
}
