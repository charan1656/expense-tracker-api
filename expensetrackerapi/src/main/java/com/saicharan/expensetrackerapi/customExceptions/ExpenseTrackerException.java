package com.saicharan.expensetrackerapi.customExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ExpenseTrackerException  extends RuntimeException{

	public ExpenseTrackerException(String message) {
		super(message);
		//TODO Auto-generated constructor stub
	}
	
	
	

}
