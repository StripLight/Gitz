package org.gitz.helper;

import org.gitz.process.GitzException;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for utilty helper class
 */
public class UtilityTest {

	@Test
	public void testEmbeddedPath() {

		String p1 = Utility.normalizePath("/mine/reponame");
		assertEquals(true, Utility.hasEmbeddedPath(p1));

		String p2 = Utility.normalizePath("\\mine\\reponame");
		assertEquals(true, Utility.hasEmbeddedPath(p2));

		String p3 = Utility.normalizePath("reponame");
		assertEquals(false, Utility.hasEmbeddedPath(p3));

	}


	@Test
	public void testExtractRepositoryName() {

		String p1 = Utility.normalizePath("/mine/reponame");
		assertEquals("reponame", Utility.extractRepositoryName(p1));

		String p2 = Utility.normalizePath("\\mine\\reponame");
		assertEquals("reponame", Utility.extractRepositoryName(p2));

		String p3 = Utility.normalizePath("reponame");
		assertEquals("reponame", Utility.extractRepositoryName(p3));

	}

	@Test
	public void testBuildPath() {

		String p1 = "/mine/";
		String result = Utility.buildPath(p1, "reponame");
		assertEquals("/mine/reponame", result);


		p1 = "/mine";
		result = Utility.buildPath(p1, "reponame");
		assertEquals("/mine/reponame", result);

		p1 = "\\mine";
		result = Utility.buildPath(p1, "reponame");
		assertEquals("/mine/reponame", result);

		p1 = "\\mine\\";
		result = Utility.buildPath(p1, "reponame");
		assertEquals("/mine/reponame", result);

		p1 = "";
		result = Utility.buildPath(p1, "reponame");
		assertEquals("/reponame", result);
	}


	@Test
	public void testCopy() {

		String source1[] = {"git", "status"};
		String newArgs1[] = Arrays.copyOfRange(source1, 1, source1.length);
		assertEquals(newArgs1.length, 1);
		assertEquals(newArgs1[0], "status");

		String source2[] = {"git", "status", "-s"};
		String newArgs2[] = Arrays.copyOfRange(source2, 1, source2.length);
		assertEquals(newArgs2.length, 2);
		assertEquals(newArgs2[0], "status");
		assertEquals(newArgs2[1], "-s");

	}


	@Test
	public void testCheckEndsWithSeparator() {

		String result;
		result = Utility.checkEndsWithSeparator(Utility.normalizePath("\\temp/"));
		assertEquals("/temp/", result);

		result = Utility.checkEndsWithSeparator(Utility.normalizePath("\\temp"));
		assertEquals("/temp/", result);

	}

	@Test
	public void testEndsWith() {

		boolean result;
		result = Utility.endsWith("hello", 'o');
		assertTrue(result);

		result = Utility.endsWith("hello", 'w');
		assertFalse(result);

	}

	@Test
	public void testReplaceChars() {

		String result;
		result = Utility.replaceChars("hello", 'o', 'w');
		assertEquals("hellw", result);

		result = Utility.replaceChars("the cat jumped over the moon", 'e', 'z');
		assertEquals("thz cat jumpzd ovzr thz moon", result);

		result = Utility.replaceChars(null, 'e', 'z');
		assertEquals(null, result);
	}


	@Test
	public void testResolveTokenisedValue() {

		System.setProperty("USERNAME", "Beryl");
		String result = Utility.resolveTokenisedValue("the ${USERNAME} jumped over the moon");
		assertEquals("the Beryl jumped over the moon", result);

	}

	@Test (expected = GitzException.class)
	public void testResolveTokenisedValueError() {

		String result = Utility.resolveTokenisedValue("the ${zMISSINGz} jumped over the moon");
		assertEquals("the Beryl jumped over the moon", result);

	}
}
