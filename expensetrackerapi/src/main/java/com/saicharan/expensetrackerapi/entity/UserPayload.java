package com.saicharan.expensetrackerapi.entity;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class UserPayload {
	
	@NotNull(message="name should not be null")
	@Column(unique =true)
	private String name;
	
	@NotBlank(message = "email should not be blank")
	@Email(message="please enter a valid email")
	private String email;
	@Size(min=5, message = "minimum it should have 3 characters ...")
	
	private String password;
	@Max( value = 88)
	private long age;
	

}
