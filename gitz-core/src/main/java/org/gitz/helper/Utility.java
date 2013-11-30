package org.gitz.helper;

import org.gitz.process.GitzException;

/**
 * static helper class, mostly path oriented tasks
 *
 * Includes some simple methods provided with StringUtils, replaced here to remove the dependency on that
 * module
 */
public final class Utility {

	private static final char SEPARATOR = '/';

	private static final String TOKEN_PREFIX = "${";
	private static final String TOKEN_SUFFIX = "}";

	private static final String OS_NAME_WINDOWS_PREFIX = "Windows";

	private Utility() {
	}


	/**
	 * Convert any windows path char to unix
	 *
	 * @param path String
	 * @return String
	 */
	public static String normalizePath(String path) {
		return Utility.replaceChars(path, '\\', SEPARATOR);
	}

	/**
	 * Combine a path and name
	 *
	 * @param basePath String
	 * @param name     String
	 * @return String
	 */
	public static String buildPath(String basePath, String name) {

		String workingPath = normalizePath(basePath);

		StringBuilder sb = new StringBuilder();

		if (Utility.endsWith(workingPath, SEPARATOR)) {
			sb.append(workingPath);
			sb.append(name);
		} else {
			sb.append(workingPath);
			sb.append(SEPARATOR);
			sb.append(name);
		}

		return sb.toString();

	}


	/**
	 * Answer true if the pathName has a path element
	 *
	 * @param pathName String
	 * @return boolean
	 */
	public static boolean hasEmbeddedPath(String pathName) {

		int index = pathName.indexOf(SEPARATOR);
		return index > -1;

	}


	/**
	 * Extract the base repo name from a path
	 *
	 * @param pathName String
	 * @return String
	 */
	public static String extractRepositoryName(String pathName) {

		if (!hasEmbeddedPath(pathName)) {
			return pathName;
		}

		int index = pathName.lastIndexOf(SEPARATOR);
		return pathName.substring(index + 1);

	}

	/**
	 * Answer true if the string is null or empty
	 *
	 * @param str String
	 * @return String
	 */
	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		}

		return str.isEmpty();
	}

	/**
	 * Answer true if the string is not empty
	 *
	 * @param str String
	 * @return String
	 */
	public static boolean isNotEmpty(String str) {
		return !Utility.isEmpty(str);
	}


	/**
	 * Answer true if the string ends with the given char value
	 *
	 * @param str    String
	 * @param suffix char
	 * @return String
	 */
	public static boolean endsWith(String str, char suffix) {
		if (str == null) {
			return false;
		}

		String endStr = String.valueOf(suffix);
		return str.endsWith(endStr);

	}

	/**
	 * @param str
	 * @param find
	 * @param replaceWith
	 * @return
	 */
	public static String replaceChars(String str, char find, char replaceWith) {

		if (str == null) {
			return null;
		}

		return str.replace(find, replaceWith);
	}

	public static boolean isWindows() {

		String osName = getSystemProperty("os.name");
		if (osName == null) {
			return false;
		}

		return osName.startsWith(OS_NAME_WINDOWS_PREFIX);
	}

	private static String getSystemProperty(String property) {
		try {
			return System.getProperty(property);
		} catch (SecurityException ex) {
			return null;
		}
	}


	public static String checkEndsWithSeparator(String str) {

		String workingStr = normalizePath(str);

		if (endsWith(workingStr, SEPARATOR)) {
			return workingStr;
		}

		return workingStr + SEPARATOR;
	}

	/**
	 * Answer a String with any token values in it resolved from the environment - typically
	 * this is to replace a user name from the system env.
	 *
	 * Variables must be in the form ${token} to be recognised and replaced
	 *
	 * @param str	String
	 * @return	String
	 */
	public static String resolveTokenisedValue(String str) {

		int startIndex = str.indexOf(TOKEN_PREFIX);
		if (startIndex == -1) {
			return str;
		}

		int lastIndex = str.indexOf(TOKEN_SUFFIX);

		String envToken = str.substring(startIndex + 2, lastIndex);
		String replacement = getValueForToken(envToken);

		StringBuilder sb = new StringBuilder();
		sb.append(str.substring(0, startIndex));
		sb.append(replacement);
		sb.append(str.substring(lastIndex + 1));

		return sb.toString();

	}

	/**
	 * Answer a token value form the environment
	 *
	 * This looks firstly in System, then in System.env. This supports a simple unit text of the behaviour
	 *
	 * @param token	String
	 * @return	String
	 */
	private static String getValueForToken(String token) {

		String value = null;

		if (Utility.isEmpty(token)) {
			throw new GitzException("No token found in a seemingly tokenised string!");
		}

		value = System.getProperty(token);
		if (value == null) {
			value = System.getenv(token);
		}

		if (Utility.isEmpty(value)) {
			throw new GitzException("No environment value found for token: " + token);
		}

		return value;
	}
}
