package com.g.test.services.exception;

public class StockNotAvailable extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public StockNotAvailable(String msg) {
		super(msg);
	}
	
	public StockNotAvailable(String msg, Throwable cause) {
		super(msg, cause);
	}
}
