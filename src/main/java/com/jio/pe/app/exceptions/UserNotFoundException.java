package com.jio.pe.app.exceptions;

public class UserNotFoundException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1424490732813662895L;

	public UserNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
			String key, String errorCode, int responseCode) {
		super(message, cause, enableSuppression, writableStackTrace, key, errorCode, responseCode);
		// TODO Auto-generated constructor stub
	}

	public UserNotFoundException(String message, Throwable cause, String key, String errorCode, int responseCode) {
		super(message, cause, key, errorCode, responseCode);
		// TODO Auto-generated constructor stub
	}

}
