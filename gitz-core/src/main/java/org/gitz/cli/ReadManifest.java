package org.gitz.cli;

import org.gitz.helper.Utility;
import org.gitz.model.Constants;
import org.gitz.model.Manifest;
import org.gitz.process.ModelLoader;

import java.io.File;
import java.util.Map;

/**
 * Command line interface class for manifest usage.
 *
 * Used to display the current manifest for verification.
 */
public class ReadManifest {

	private String runOutput = "";

	private Map runEnvironment = System.getenv();

	public static void main(String args[]) {
		ReadManifest readManifest = new ReadManifest();
		readManifest.run();
	}


	public void run() {

		String manifestFile = (String) getRunEnvironment().get(Constants.MANIFEST_FILE_PATH);

		if (isValid(manifestFile)) {
			ModelLoader loader = new ModelLoader();
			Manifest manifest = loader.load(manifestFile);

			runOutput = manifest.toDisplayString(manifestFile);
			System.out.println(runOutput);
		}
	}

	public String getRunOutput() {
		return runOutput;
	}

	private boolean isValid(String manifestFile) {

		if (Utility.isEmpty(manifestFile)) {
			println("Gitz!: No manifest file defined. Exiting!");
			return false;
		}

		if (!new File(manifestFile).exists()) {
			println("Gitz!: Can't find file " + manifestFile + ". Bugging out!");
			return false;
		}
		return true;

	}

	public Map getRunEnvironment() {
		return runEnvironment;
	}

	public void setRunEnvironment(Map runEnvironment) {
		this.runEnvironment = runEnvironment;
	}

	private void println(String text) {
		System.out.println(text);
	}
}
