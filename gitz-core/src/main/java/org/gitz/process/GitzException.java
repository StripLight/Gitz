package org.gitz.process;

/**
 * Common exception class for Gitz exceptions
 */
public class GitzException extends RuntimeException {

	public GitzException(Throwable cause) {
		super(cause);
	}

	public GitzException(String message) {
		super(message);
	}

	public GitzException(String message, Throwable cause) {
		super(message, cause);
	}

}
