package com.g.test.services.exception;

public class RepeatedProgram extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public RepeatedProgram(String msg) {
		super(msg);
	}
	
	public RepeatedProgram(String msg, Throwable cause) {
		super(msg, cause);
	}
}
