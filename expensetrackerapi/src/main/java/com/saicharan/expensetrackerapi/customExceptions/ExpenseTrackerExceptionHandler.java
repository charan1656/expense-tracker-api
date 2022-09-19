package com.saicharan.expensetrackerapi.customExceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExpenseTrackerExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<ExpensePayload> handleAlreadyExists(AlreadyExistsException error,WebRequest status)
	{
		
		ExpensePayload expensePayload=new ExpensePayload();
		
		expensePayload.setErrorMessage(error.getMessage());
		expensePayload.setStatusCode(HttpStatus.CONFLICT.value());
		expensePayload.setTimeStamp(new Date());
		
		return new ResponseEntity<ExpensePayload>(expensePayload,HttpStatus.CONFLICT);
		
		
	}
	
	
	
	@ExceptionHandler(ExpenseTrackerException.class)
	public ResponseEntity<ExpensePayload> handleException(ExpenseTrackerException expenseTrackerException, WebRequest request)
	{
		
		ExpensePayload expensePayload=new ExpensePayload();
		
		expensePayload.setErrorMessage(expenseTrackerException.getMessage());
		expensePayload.setStatusCode(HttpStatus.NOT_FOUND.value());
		expensePayload.setTimeStamp(new Date());
		
		return new ResponseEntity<ExpensePayload>(expensePayload,HttpStatus.NOT_FOUND);
		
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String,String> map= new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error)->{
			
			String fieldName= ((FieldError)error).getField();
			String message=error.getDefaultMessage();
			map.put(fieldName, message);
		});
		return new ResponseEntity<Object>(map,HttpStatus.BAD_REQUEST);
	}

}
