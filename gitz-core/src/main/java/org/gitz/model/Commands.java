package org.gitz.model;

/**
 * Supported command sub set
 */
public enum Commands {
	STATUS("STATUS", ""),
	BRANCH("BRANCH", "[-D, -M not supported]"),
	SB("SB", "Simple Branch, shows a summary view for all repositories"),
	LOG("LOG", ""),
	DIFF("DIFF", ""),
	FETCH("FETCH", ""),
	ADD("ADD", "[-i, -p, -e not supported]"),
	COMMIT("COMMIT", "[Only -m, -q, -a -v, and equivalents. -m is mandatory]"),
	REBASE("REBASE", "[Only -q, -v, --stat and equivalents]"),
	PULL("PULL", "[-e, --edit not supported]"),
	PUSH("PUSH", ""),
	REMOTE("REMOTE", ""),
	CHECKOUT("CHECKOUT", "[-f, -p not supported]"),
	CLONE("CLONE", "[-s, --reference not supported]");


	private String value;
	private String description;

	Commands(String value, String description) {
		this.value = value;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String toDisplayString() {
		return String.format("%-10s %s", value, description);
	}

}
