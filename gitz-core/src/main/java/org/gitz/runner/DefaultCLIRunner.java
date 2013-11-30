package org.gitz.runner;

import org.apache.log4j.Logger;
import org.gitz.interfaces.CLIRunnerType;
import org.gitz.interfaces.CommandLineBuilderType;
import org.gitz.interfaces.GitTaskType;
import org.gitz.interfaces.ManifestEntryType;
import org.gitz.model.Manifest;
import org.gitz.model.PostCommandTaskEntry;
import org.gitz.process.GitzException;

import java.io.*;
import java.util.Arrays;

/**
 * Default command line interface runner.
 * <p/>
 * Used to execute a single git command for a given manifest entry - eg to run git branch against repo 1
 * <p/>
 * It is also used to execute any child tasks for the command - eg a curl script for clone.
 */
public class DefaultCLIRunner implements CLIRunnerType {

	private static final Logger LOG = Logger.getLogger(DefaultCLIRunner.class);

	@Override
	public String execute(GitTaskType commandToRun, ManifestEntryType manifestEntry) {

		CommandLineBuilderType cliBuilder = commandToRun.getBuilder();
		cliBuilder.build(manifestEntry);

		String runPath = commandToRun.getRunFolder(manifestEntry);
		String result = executeCommand(cliBuilder.getCommandLine(), runPath);

		commandToRun.getWriter().print(result, manifestEntry.getRepositoryName(), commandToRun);

		executePostCommands(commandToRun, manifestEntry);

		return result;
	}

	private String executeCommand(String[] commandLine, String inPath) {

		StringBuilder out = new StringBuilder();

		if (LOG.isDebugEnabled()) {
			LOG.debug("Executing command line: " + Arrays.toString(commandLine) +
					" in folder: " + inPath);
		}

		ProcessBuilder processBuilder = new ProcessBuilder(commandLine);
		processBuilder.redirectErrorStream(true);
		processBuilder.directory(new File(inPath));

		int processExitValue = executeProcess(processBuilder, out);

		if (processExitValue != 0) {
			String message = buildErrorMessage(out, processExitValue);
			LOG.error(message);
			throw new GitzException(message);
		}

		return out.toString();
	}

	private int executeProcess(ProcessBuilder processBuilder, StringBuilder outBuffer) {

		int processExitValue = 0;
		Process process = null;
		try {
			process = processBuilder.start();
			readOutput(process.getInputStream(), outBuffer);

			processExitValue = process.waitFor();

		} catch (Exception e) {
			LOG.error("Failed to execute process call..." + e.getLocalizedMessage());
			throw new GitzException(e);
		}

		return processExitValue;
	}

	private void readOutput(InputStream is, StringBuilder out) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(is));

		String line;
		while ((line = in.readLine()) != null) {
			out.append(line);
			out.append("\n");
		}

	}

	private String buildErrorMessage(StringBuilder out, int processExitValue) {

		StringBuilder sb = new StringBuilder("Invocation failed: output is '");

		sb.append(out.toString());
		sb.append("'. Return code: ");
		sb.append(processExitValue);

		return sb.toString();

	}


	private void executePostCommands(GitTaskType commandToRun, ManifestEntryType manifestEntry) {

		Manifest manifest = (Manifest) manifestEntry.getParentManifest();

		for (PostCommandTaskEntry taskEntry : manifest.collectPostCommandTaskEntries(commandToRun.getCommandName())) {

			CommandLineBuilderType cliBuilder = taskEntry.getBuilder();
			cliBuilder.build(manifestEntry);
			String pathToUse = taskEntry.getRunFolder(manifestEntry);

			String result = executeCommand(cliBuilder.getCommandLine(), pathToUse);
			LOG.info("Executed post command for " +  commandToRun.getCommandName());
			commandToRun.getWriter().print(result, manifestEntry.getRepositoryName(), commandToRun);

		}
	}

}
