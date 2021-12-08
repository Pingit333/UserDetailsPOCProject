package com.mypoc.userdetailtracker.user.exception;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5340646690062358393L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
