package com.cs385.app;

/**
 * Provides a custom exception (DataAccessException) 
 * for use in the DataAccess class.
 */
public class DataAccessException extends Exception {
	private static final long serialVersionUID = -4287245645002940503L;

	public DataAccessException() {
		super();
	}
	
	public DataAccessException(String message) {
		super(message);
	}
	
	public DataAccessException(String message, Throwable cause) {
		super(message,cause);
	}
	
	public DataAccessException(Throwable cause) {
		super(cause);
	}
}
