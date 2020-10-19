package com.g.test.services.exception;

public class ProgramNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ProgramNotFoundException(String msg) {
		super(msg);
	}
	
	public ProgramNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
