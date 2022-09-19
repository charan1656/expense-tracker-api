package com.saicharan.expensetrackerapi.customExceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
	
	private String Jawttoken;

}
