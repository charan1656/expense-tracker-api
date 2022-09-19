package com.saicharan.expensetrackerapi.customExceptions;

import java.util.Date;

import lombok.Data;

@Data
public class ExpensePayload {

	
	private String errorMessage;
	private Integer statusCode;
	private Date timeStamp;
	
	
}
