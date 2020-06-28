package com.amazonws.lambda.function.example.exception;

import java.io.Serializable;


/**
 * 
 * @author Andres Solorzano.
 * 
 */
public class DeviceSectorException extends Exception implements Serializable {

	/** The property serialVersionUID. */
	private static final long serialVersionUID = -2497389416820959668L;

	public DeviceSectorException() {
		super();
	}

	public DeviceSectorException(String message) {
		super(message);
	}
	

	public DeviceSectorException(String message, Throwable cause) {
		super(message, cause);
	}

}
