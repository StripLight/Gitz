package org.gitz.commands;

import org.gitz.interfaces.GitTaskType;
import org.gitz.interfaces.ManifestType;
import org.gitz.model.Commands;
import org.gitz.process.GitzException;

/**
 * Create command for required task
 */
public class CommandFactory {

	private ManifestType manifest;

	/**
	 * @param manifest ManifestType
	 */
	public CommandFactory(ManifestType manifest) {
		this.manifest = manifest;
	}


	/**
	 * Factory method create the git task
	 *
	 * @param cmd Commands
	 * @return GitTaskType
	 */
	public GitTaskType create(Commands cmd) {

		switch (cmd) {
			case STATUS:
				return new StatusCommand(manifest);
			case SB:
				return new SimpleBranchCommand(manifest);
			case DIFF:
				return new DiffCommand(manifest);
			case LOG:
				return new LogCommand(manifest);
			case FETCH:
				return new FetchCommand(manifest);
			case COMMIT:
				return new CommitCommand(manifest);
			case BRANCH:
				return new BranchCommand(manifest);
			case ADD:
				return new AddCommand(manifest);
			case CHECKOUT:
				return new CheckoutCommand(manifest);
			case PULL:
				return new PullCommand(manifest);
			case PUSH:
				return new PushCommand(manifest);
			case CLONE:
				return new CloneCommand(manifest);
			case REMOTE:
				return new RemoteCommand(manifest);
			case REBASE:
				return new RebaseCommand(manifest);
			default:
				// shouldn't actually get here....
				throw new GitzException("Invalid command found!");
		}

	}


}
