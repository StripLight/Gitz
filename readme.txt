GITZ
----


Introduction
------------
Gitz is a cross platform command line tool for Git.

It is designed to handle the common BAU tasks - such as clone, status, add etc  across a set of local Git repositories.

It uses an XML manifest file to define the repository set to work with. The location of the file is exported to the
environment, typically in a command line session.

Gitz supports the execution of post command tasks after a Git command has executed. For example, after a clone, some
config settings can be fixed via a post command execution.

As yet Gitz has only been validated on Windows platforms. The code should be cross platform, but access to unix
environments to test has not yet been possible.


XML file
--------

The XML file has the following bits:

The base folder where you want your local repositories placed

	<baseFolder>/temp/</baseFolder>

A description. This is output when setting and checking the manifest file - so make this useful

	<description>Manifest to use two git support projects.</description>

A default path to the remote server where the repo is stored. used when cloning only

	<defaultServerPath>git@github.com:</defaultServerPath>

Some post command tasks. Here, after the clone command has run, a config setting is fixed.

	<postCommandTasks>
		<postCommandTask>
			<command>clone</command>
			<postCommandTaskEntries>
				<postCommandTaskEntry>
					<commandLine>git config branch.master.rebase true</commandLine>
				</postCommandTaskEntry>
			</postCommandTaskEntries>
		</postCommandTask>
	</postCommandTasks>

Some repository definitions. Here are two repositories in the set. The target name is the local folder name of the
repository, this can be omitted if required

	<manifestEntries>
		<entry>
			<repository>blynn/gitmagic</repository>
			<targetName>git-magic</targetName>
		</entry>
		<entry>
			<repository>rogerdudler/git-guide</repository>
			<targetName>git-guide</targetName>
		</entry>
	</manifestEntries>


Setting the manifest
--------------------
Run the manifest.bat (or .sh) file passing the full path of the manifest file to load.

eg, 

> manifest src/test/resources/manifests/local-repo.xml

(This is actually from one of the tests)

This will run and report:

Gitz!: 1.0
Current manifest: src/test/resources/manifests/local-repo.xml
Description:      Manifest for local and scruffy

Just run manifest without any arguments to see the current manifest in use.


Running Gitz
------------

Once the manifest has been set, then run some git commands as follows:

> gitz status

May well show:

### Gitz!: repository: local
# On branch master
nothing to commit (working directory clean)

### Gitz!: repository: scruffy
# On branch master
# Untracked files:
#   (use "git add <file>..." to include in what will be committed)
#
#	b.txt
nothing added to commit but untracked files present (use "git add" to track)

that is, the current status of local repositories local and scruffy (latter being so named as it has uncommitted work)

Run gitz sb

which may say:

> gitz sb
local -> master
scruffy -> master

or try 'remote'

> gitz remote -v
### Gitz!: repository: local
origin  C:/temp/work/server (fetch)
origin  C:/temp/work/server (push)

### Gitz!: repository: scruffy
origin  C:/temp/work/server (fetch)
origin  C:/temp/work/server (push)
