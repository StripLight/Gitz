package org.gitz.commands;

import org.gitz.commands.checker.SimpleStatusCommand;
import org.gitz.helper.CloneCommandLineBuilder;
import org.gitz.helper.CommandLineBuilder;
import org.gitz.interfaces.GitTaskType;
import org.gitz.model.Commands;
import org.gitz.runner.DefaultCLIRunner;
import org.gitz.writers.DefaultWriter;
import org.gitz.writers.SimpleBranchCommandWriter;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Simple tests for commands
 */
public class SimpleCommandTest extends AbstractCommandTest {

	@Test
	public void testDiff() {

		GitTaskType cmd = new CommandFactory(getManifest()).create(Commands.DIFF);
		assertTrue(cmd instanceof DiffCommand);

		assertEquals(cmd.getCommand()[0], "diff");
		assertEquals(cmd.argsAreOK(), true);
		assertEquals(cmd.needsExecutable(), true);
		assertEquals(cmd.runsInBaseFolder(), false);
		assertEquals(cmd.isQuiet(), false);

	}

	@Test
	public void testLog() {

		GitTaskType cmd = new CommandFactory(getManifest()).create(Commands.LOG);
		assertTrue(cmd instanceof LogCommand);

		assertEquals(cmd.getCommand()[0], "log");
		assertEquals(cmd.argsAreOK(), true);
		assertEquals(cmd.needsExecutable(), true);
		assertEquals(cmd.runsInBaseFolder(), false);
		assertEquals(cmd.isQuiet(), false);

	}

	@Test
	public void testRemote() {

		GitTaskType cmd = new CommandFactory(getManifest()).create(Commands.REMOTE);
		assertTrue(cmd instanceof RemoteCommand);

		assertEquals(cmd.getCommand()[0], "remote");
		assertEquals(cmd.argsAreOK(), true);
		assertEquals(cmd.needsExecutable(), true);
		assertEquals(cmd.runsInBaseFolder(), false);
		assertEquals(cmd.isQuiet(), false);

	}

	@Test
	public void testStatus() {

		GitTaskType cmd = new CommandFactory(getManifest()).create(Commands.STATUS);
		assertTrue(cmd instanceof StatusCommand);

		assertEquals(cmd.getCommand()[0], "status");
		assertEquals(cmd.argsAreOK(), true);
		assertEquals(cmd.needsExecutable(), true);
		assertEquals(cmd.runsInBaseFolder(), false);
		assertEquals(cmd.isQuiet(), false);

	}

	@Test
	public void testSimpleStatus() {

		GitTaskType cmd = new SimpleStatusCommand(getManifest());
		assertTrue(cmd instanceof SimpleStatusCommand);

		assertEquals(cmd.getCommand()[0], "status");
		assertEquals(cmd.getCommand()[1], "-s");
		assertEquals(cmd.argsAreOK(), true);
		assertEquals(cmd.needsExecutable(), true);
		assertEquals(cmd.runsInBaseFolder(), false);
		assertEquals(cmd.isQuiet(), true);

	}

	@Test
	public void testSimpleBranch() {

		GitTaskType cmd = new CommandFactory(getManifest()).create(Commands.SB);
		assertTrue(cmd instanceof SimpleBranchCommand);

		assertEquals(cmd.getCommand()[0], "status");
		assertEquals(cmd.getCommand()[1], "-b");
		assertEquals(cmd.argsAreOK(), true);
		assertEquals(cmd.hasArgs(), false);
		assertEquals(cmd.needsExecutable(), true);
		assertEquals(cmd.runsInBaseFolder(), false);
		assertEquals(cmd.isQuiet(), true);

		assertTrue(cmd.getWriter() instanceof SimpleBranchCommandWriter);


	}

	@Test
	public void testFetch() {

		GitTaskType cmd = new CommandFactory(getManifest()).create(Commands.FETCH);
		assertTrue(cmd instanceof FetchCommand);

		assertEquals(cmd.getCommand()[0], "fetch");
		assertEquals(cmd.argsAreOK(), true);
		assertEquals(cmd.needsExecutable(), true);
		assertEquals(cmd.runsInBaseFolder(), false);
		assertEquals(cmd.isQuiet(), false);

	}


	@Test
	public void testCommit() {

		GitTaskType cmd = new CommandFactory(getManifest()).create(Commands.COMMIT);
		assertTrue(cmd instanceof CommitCommand);

		CommitCommand cc = (CommitCommand) cmd;
		String args[] = {"-m"};

		cc.setArgs(args);
		assertEquals(cmd.getCommand()[0], "commit");

		assertEquals(cmd.argsAreOK(), true);
		assertEquals(cmd.needsExecutable(), true);
		assertEquals(cmd.runsInBaseFolder(), false);
		assertEquals(cmd.isQuiet(), false);

		assertTrue(cc.getRunner() instanceof DefaultCLIRunner);
		assertTrue(cc.getWriter() instanceof DefaultWriter);
		assertTrue(cc.getBuilder() instanceof CommandLineBuilder);

	}


	@Test
	public void testBranch() {

		GitTaskType cmd = new CommandFactory(getManifest()).create(Commands.BRANCH);
		assertTrue(cmd instanceof BranchCommand);

		BranchCommand bc = (BranchCommand) cmd;
		String args[] = {"-v"};

		bc.setArgs(args);
		assertEquals(cmd.getCommand()[0], "branch");

		assertEquals(cmd.argsAreOK(), true);
		assertEquals(cmd.needsExecutable(), true);
		assertEquals(cmd.runsInBaseFolder(), false);
		assertEquals(cmd.isQuiet(), false);

	}

	@Test
	public void testAdd() {

		GitTaskType cmd = new CommandFactory(getManifest()).create(Commands.ADD);
		assertTrue(cmd instanceof AddCommand);

		AddCommand ac = (AddCommand) cmd;
		String args[] = {"./"};

		ac.setArgs(args);
		assertEquals(cmd.getCommand()[0], "add");

		assertEquals(cmd.argsAreOK(), true);
		assertEquals(cmd.needsExecutable(), true);
		assertEquals(cmd.runsInBaseFolder(), false);
		assertEquals(cmd.isQuiet(), false);

	}

	@Test
	public void testCheckout() {

		GitTaskType cmd = new CommandFactory(getManifest()).create(Commands.CHECKOUT);
		assertTrue(cmd instanceof CheckoutCommand);

		CheckoutCommand cc = (CheckoutCommand) cmd;
		String args[] = {"master"};

		cc.setArgs(args);
		assertEquals(cmd.getCommand()[0], "checkout");

		assertEquals(cmd.argsAreOK(), true);
		assertEquals(cmd.needsExecutable(), true);
		assertEquals(cmd.runsInBaseFolder(), false);
		assertEquals(cmd.isQuiet(), false);

	}

	@Test
	public void testPull() {

		GitTaskType cmd = new CommandFactory(getManifest()).create(Commands.PULL);
		assertTrue(cmd instanceof PullCommand);

		PullCommand pc = (PullCommand) cmd;
		String args[] = {""};

		pc.setArgs(args);
		assertEquals(cmd.getCommand()[0], "pull");

		assertEquals(cmd.argsAreOK(), true);
		assertEquals(cmd.needsExecutable(), true);
		assertEquals(cmd.runsInBaseFolder(), false);
		assertEquals(cmd.isQuiet(), false);

	}

	@Test
	public void testPush() {

		GitTaskType cmd = new CommandFactory(getManifest()).create(Commands.PUSH);
		assertTrue(cmd instanceof PushCommand);

		PushCommand pc = (PushCommand) cmd;
		String args[] = {"origin", "HEAD:refs/for/master"};

		pc.setArgs(args);
		assertEquals(cmd.getCommand()[0], "push");

		assertEquals(cmd.argsAreOK(), true);
		assertEquals(cmd.needsExecutable(), true);
		assertEquals(cmd.runsInBaseFolder(), false);
		assertEquals(cmd.isQuiet(), false);

	}

	@Test
	public void testRebase() {

		GitTaskType cmd = new CommandFactory(getManifest()).create(Commands.REBASE);
		assertTrue(cmd instanceof RebaseCommand);

		RebaseCommand rc = (RebaseCommand) cmd;
		String args[] = {"origin", "next"};

		rc.setArgs(args);
		assertEquals(cmd.getCommand()[0], "rebase");

		assertEquals(cmd.argsAreOK(), true);
		assertEquals(cmd.needsExecutable(), true);
		assertEquals(cmd.runsInBaseFolder(), false);
		assertEquals(cmd.isQuiet(), false);

	}

	@Test
	public void testClone() {

		GitTaskType cmd = new CommandFactory(getManifest()).create(Commands.CLONE);
		assertTrue(cmd instanceof CloneCommand);

		CloneCommand rc = (CloneCommand) cmd;
		String args[] = {"ssh://me@some.where:12345/repo.git", "repo"};

		rc.setArgs(args);
		assertEquals(cmd.getCommand()[0], "clone");

		assertEquals(cmd.argsAreOK(), true);
		assertEquals(cmd.needsExecutable(), true);

		// clone runs in the base folder, not the repo folder
		assertEquals(cmd.runsInBaseFolder(), true);
		assertEquals(cmd.isQuiet(), false);
		assertEquals(cmd.hasArgs(), true);

		assertTrue(rc.getBuilder() instanceof CloneCommandLineBuilder);
		assertTrue(rc.getRunner() instanceof DefaultCLIRunner);
		assertTrue(rc.getWriter() instanceof DefaultWriter);

	}
}
