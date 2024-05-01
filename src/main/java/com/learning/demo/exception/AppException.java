package com.learning.demo.exception;

/**
 * Application Level Root Exception
 *
 */
public class AppException extends RuntimeException {

	private static final long serialVersionUID = 1037181482607198710L;

	public AppException() {
		super();
	}

	public AppException(String message) {
		super(message);
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

}
