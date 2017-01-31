package com.jio.pe.app.exceptions;

public class BaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7576480237933026957L;

	private String key;
	private String errorCode;
	private int responseCode;
	
	public BaseException() {
		// TODO Auto-generated constructor stub
	}

	public BaseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BaseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String key, String errorCode, int responseCode) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
		
		this.key = key;
		this.errorCode = errorCode;
		this.responseCode = responseCode;
	}

	public BaseException(String message, Throwable cause, String key, String errorCode, int responseCode ) {
		super(message, cause);
		// TODO Auto-generated constructor stub
		this.key = key;
		this.errorCode = errorCode;
		this.responseCode = responseCode;

	}
	
	
}
