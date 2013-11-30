package org.gitz.cli;

import org.apache.log4j.Logger;
import org.gitz.commands.CommandFactory;
import org.gitz.helper.Utility;
import org.gitz.interfaces.GitTaskType;
import org.gitz.interfaces.ReturnCode;
import org.gitz.model.Commands;
import org.gitz.model.Constants;
import org.gitz.model.Manifest;
import org.gitz.process.GitzException;
import org.gitz.process.ModelLoader;

import java.util.Arrays;
import java.util.Map;

/**
 * Main class for tool
 */
public class Gitz {

	private static final Logger LOG = Logger.getLogger(Gitz.class);

	private Manifest manifest = null;

	private GitTaskType cmd;

	private String[] args;

	public static void main(String args[]) {
		Gitz gitz = new Gitz();
		gitz.run(args);
	}


	/**
	 * Entry point
	 *
	 * @param givenArgs	String[]
	 */
	public int run(String givenArgs[]) {

		try {
			init(givenArgs);

			ReturnCode runResult = run();

			if (runResult == ReturnCode.OK) {
				return 0;
			}

			// we hit an error, display and exit
			println(cmd.getErrorMessage());
			return -1;

		} catch (Exception e) {
			usage(e.getLocalizedMessage());
			return -1;
		}

	}

	private void init(String givenArgs[]) {

		if (givenArgs.length < 1) {
			throw new GitzException("Insufficient arguments - must include at least the Git command to run.");
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("Original args passed in are: " +  Arrays.toString(givenArgs));
		}
		String workingCommand = givenArgs[0].toUpperCase();

		this.args = Arrays.copyOfRange(givenArgs, 1, givenArgs.length);

		loadManifest();

		CommandFactory factory = new CommandFactory(manifest);
		cmd = factory.create(Commands.valueOf(workingCommand));
	}

	/**
	 * Load the manifest. Ignore if this has already been loaded - eg in a test
	 */
	private void loadManifest() {

		if (manifest != null) {
			LOG.debug("ReadManifest was pre-loaded. '" +  manifest.getDescription() +"'.");
			return;
		}

		Map workingEnv = System.getenv();

		String manifestFile = (String) workingEnv.get(Constants.MANIFEST_FILE_PATH);
		if (Utility.isEmpty(manifestFile)) {
			throw new GitzException("Failed to load the manifest file." + manifestFile);
		}

		ModelLoader loader = new ModelLoader();
		manifest = loader.load(manifestFile);
	}

	private ReturnCode run() {
		return cmd.execute(args);
	}

	private void usage(String errorMessage) {

		StringBuilder sb = new StringBuilder("Gitz: ");
		sb.append(Manifest.VERSION);


		sb.append(". Supported git commands are:\n");
		sb.append("Command    Limitations\n");

		for (Commands workingCommand : Commands.values()) {
			sb.append(workingCommand.toDisplayString());
			sb.append("\n");
		}

		sb.append("\nError message:");
		sb.append(errorMessage);

		println(sb.toString());
	}


	public Manifest getManifest() {
		return manifest;
	}

	public void setManifest(Manifest manifest) {
		this.manifest = manifest;
	}

	private void println(String errorText) {
		System.out.println(errorText);
	}

}
