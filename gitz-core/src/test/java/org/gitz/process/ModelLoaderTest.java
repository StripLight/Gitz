package org.gitz.process;

import org.gitz.Assert;
import org.gitz.interfaces.ExecutableTaskType;
import org.gitz.interfaces.ManifestEntryType;
import org.gitz.model.Manifest;
import org.gitz.model.ManifestEntry;
import org.gitz.model.PostCommandTask;
import org.gitz.model.PostCommandTaskEntry;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Test the loader
 */
public class ModelLoaderTest {

	@Test
	public void testExample1() {

		String[] repos = {"blynn/gitmagic", "rogerdudler/git-guide"};
		String[] targets = {"git-magic", "git-guide"};

		ModelLoader loader = new ModelLoader();
		Manifest manifest = loader.load("src/test/resources/manifests/example-1.xml");

		assertEquals("/temp/", manifest.getBaseFolder());
		assertEquals("https://github.com/", manifest.getDefaultServerPath());
		assertEquals("Manifest to use two git support projects.", manifest.getDescription());

		assertEquals(2, manifest.getManifestEntries().size());
		assertEntries(manifest.getManifestEntries(), repos, targets);

		ManifestEntry manifestEntry = manifest.getManifestEntries().get(0);
		assertEquals(manifestEntry.getTargetName(), "git-magic");

		assertEquals(1, manifest.getPostCommandTasks().size());

		PostCommandTask pct = manifest.getPostCommandTasks().get(0);
		assertEquals("clone", pct.getGitCommand());

		assertEquals(1, pct.getTaskEntries().size());

		PostCommandTaskEntry entry = pct.getTaskEntries().get(0);
		assertEquals("git config branch.master.rebase true", entry.getCommandLine());

		String bits[] = {"git", "config", "branch.master.rebase", "true"};
		Assert.assertArray(bits, entry.getCommand());

		assertEquals(0, entry.getArgs().length);
		assertEquals(false, entry.runsInBaseFolder());
		assertEquals("/temp/git-magic", entry.getRunFolder(manifestEntry));
	}

	@Test
	public void testExample2() {

		String[] repos = {"blynn/gitmagic", "rogerdudler/git-guide"};
		String[] targets = {"gitmagic", "git-guide"};

		ModelLoader loader = new ModelLoader();
		Manifest manifest = loader.load("src/test/resources/manifests/example-2.xml");

		assertEquals("/temp/", manifest.getBaseFolder());
		assertEquals("https://github.com/", manifest.getDefaultServerPath());
		assertEquals("Manifest to use two git support projects. (no command tasks or targets)", manifest.getDescription());

		assertEquals(2, manifest.getManifestEntries().size());
		assertEntries(manifest.getManifestEntries(), repos, targets);

		ExecutableTaskType ett = mock(ExecutableTaskType.class);
		when(ett.runsInBaseFolder()).thenReturn(true);

		ManifestEntry manifestEntry = manifest.getManifestEntries().get(0);
		assertEquals(manifestEntry.getTargetName(), "gitmagic");
		String path = manifestEntry.getPath(ett);

		assertEquals("/temp/", path);

		assertEntry(manifest.getManifestEntries().get(1), "git-guide", "/temp/", true);
		assertEntry(manifest.getManifestEntries().get(1), "git-guide", "/temp/git-guide", false);

		ExecutableTaskType ett2 = mock(ExecutableTaskType.class);
		when(ett2.runsInBaseFolder()).thenReturn(false);

		ManifestEntry manifestEntry2 = (ManifestEntry) manifest.getManifestEntries().get(1);
		assertEquals(manifestEntry2.getTargetName(), "git-guide");
		String path2 = manifestEntry2.getPath(ett2);

		assertEquals("/temp/git-guide", path2);


		assertEquals(0, manifest.getPostCommandTasks().size());
	}


	private void assertEntry(ManifestEntryType entry, String repo, String expectedPath, boolean b) {

		ExecutableTaskType ett = mock(ExecutableTaskType.class);
		when(ett.runsInBaseFolder()).thenReturn(b);

		assertEquals(entry.getTargetName(), repo);
		String path = entry.getPath(ett);

		assertEquals(expectedPath, path);
	}

	private void assertEntries(List<ManifestEntry> manifestEntries, String repos[], String targets[]) {


		String readRepos[] = new String[2];
		String readTargets[] = new String[2];

		int i = 0;
		for (ManifestEntryType entry : manifestEntries) {
			readRepos[i] = entry.getRepositoryName();
			readTargets[i] = entry.getTargetName();
			i++;
		}

		Assert.assertArray(repos, readRepos);
		Assert.assertArray(targets, readTargets);

	}
}
