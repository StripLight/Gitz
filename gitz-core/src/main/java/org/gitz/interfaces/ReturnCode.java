package org.gitz.interfaces;

/**
 * Enum for errors generated in a call
 */
public enum ReturnCode {

	OK(0), INVALID_ARGS(-1), UNSPECIFIED_ERROR(-2);

	private ReturnCode(int code) {
		this.code = code;
	}

	private int code;

	public int getCode() {
		return code;
	}
}
