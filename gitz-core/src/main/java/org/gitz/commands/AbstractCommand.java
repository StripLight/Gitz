package org.gitz.commands;

import org.apache.log4j.Logger;
import org.gitz.helper.CommandLineBuilder;
import org.gitz.interfaces.*;
import org.gitz.model.Manifest;
import org.gitz.model.ManifestEntry;
import org.gitz.runner.DefaultCLIRunner;
import org.gitz.writers.DefaultWriter;

import java.util.Arrays;

/**
 * Abstract class for all command types. Holds default behaviour & common code
 */
public abstract class AbstractCommand implements GitTaskType {

	private static final Logger LOG = Logger.getLogger(AbstractCommand.class);

	private static final String EXECUTABLE = "git";

	private Manifest manifest;

	private String args[];

	private String errorMessage = "";

	private OutputWriterType defaultWriter = new DefaultWriter();

	private CLIRunnerType runner = new DefaultCLIRunner();

	public AbstractCommand(ManifestType manifest) {
		this.manifest = (Manifest) manifest;
	}

	public abstract String[] getCommand();


	/**
	 * Entry point
	 * <p/>
	 * Execute the task
	 *
	 * @return ReturnCode
	 */
	public ReturnCode execute(String givenArgs[]) {

		setArgs(givenArgs);

		if (!argsAreOK()) {
			errorMessage = errorMessage + ". Was running '"+ getCommandName() +"' Fix the problem (or give up) - args are: "
								+ Arrays.toString(args);

			LOG.error(errorMessage);
			return ReturnCode.INVALID_ARGS;
		}

		return execute();
	}


	private ReturnCode execute() {

		try {
			return executeCommands();
		} catch (Exception e) {
			errorMessage = errorMessage + "Gitz!:  Something screwed up, but some tasks may have run. Sponsors message: "
								+ e.getLocalizedMessage();
			LOG.error(errorMessage);

			return ReturnCode.UNSPECIFIED_ERROR;
		}

	}


	private ReturnCode executeCommands() {

		for (ManifestEntry manifestEntry : manifest.getManifestEntries()) {
			boolean hasWork = hasWorkTodo(manifestEntry);

			if (LOG.isDebugEnabled()) {
				LOG.debug("Command '" +getCommandName()+ "' has work to do? " + hasWork);
			}

			if (hasWork) {
				getRunner().execute(this, manifestEntry);
			}
		}

		return ReturnCode.OK;
	}


	@Override
	public OutputWriterType getWriter() {
		return defaultWriter;
	}


	@Override
	public String getCommandName() {
		return getCommand()[0];
	}

	@Override
	public boolean argsAreOK() {
		return true;
	}


	@Override
	public boolean isQuiet() {
		return false;
	}

	@Override
	public String getRunFolder(ManifestEntryType manifestEntry) {
		return manifestEntry.getPath(this);
	}

	@Override
	public boolean hasWorkTodo(ManifestEntryType e) {
		return true;
	}

	@Override
	public boolean runsInBaseFolder() {
		return false;
	}

	/**
	 * Answer the runner instance
	 *
	 * @return CLIRunnerType
	 */
	public CLIRunnerType getRunner() {
		return runner;
	}

	public void setRunner(CLIRunnerType runner) {
		this.runner = runner;
	}

	@Override
	public String[] getArgs() {
		return args;
	}

	public void setArgs(String args[]) {
		this.args = args;
	}


	@Override
	public String getExecutable() {
		return EXECUTABLE;
	}

	@Override
	public boolean needsExecutable() {
		return true;
	}

	@Override
	public boolean hasArgs() {

		if (args == null) {
			return false;
		}

		return args.length > 0;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public CommandLineBuilderType getBuilder() {
		return new CommandLineBuilder(this);
	}

}
