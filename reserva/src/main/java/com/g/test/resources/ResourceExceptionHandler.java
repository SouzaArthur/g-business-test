package com.g.test.resources;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.g.test.resources.exception.StandardError;
import com.g.test.services.exception.ProgramNotFoundException;
import com.g.test.services.exception.RepeatedProgram;
import com.g.test.services.exception.StockNotAvailable;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ProgramNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ProgramNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(StockNotAvailable.class)
	public ResponseEntity<StandardError> objectNotFound(StockNotAvailable e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(RepeatedProgram.class)
	public ResponseEntity<StandardError> repeatedProgram(RepeatedProgram e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}
