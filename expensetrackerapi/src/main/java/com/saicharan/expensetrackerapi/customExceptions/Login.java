package com.saicharan.expensetrackerapi.customExceptions;

import lombok.Data;

@Data
public class Login {
	private String email;
	
	private String password;
}
