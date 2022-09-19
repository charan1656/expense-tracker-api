package com.saicharan.expensetrackerapi.controller;

import javax.validation.Valid;

import com.saicharan.expensetrackerapi.customExceptions.AlreadyExistsException;
import com.saicharan.expensetrackerapi.customExceptions.JWTUtility;
import com.saicharan.expensetrackerapi.customExceptions.JwtResponse;
import com.saicharan.expensetrackerapi.customExceptions.Login;
import com.saicharan.expensetrackerapi.dao.UserRepository;
import com.saicharan.expensetrackerapi.entity.UserEntity;
import com.saicharan.expensetrackerapi.security.ImpleUserdetails;
import com.saicharan.expensetrackerapi.security.MySecurityConfig;
import com.saicharan.expensetrackerapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	JWTUtility jwtUtility;
	@Autowired
	ImpleUserdetails mySec;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserRepository repository;

	@Autowired
	UserService service;
	
	
	@PostMapping("/login")
	public JwtResponse getLoggedInUser(@RequestBody Login entity) throws Exception
	{
		
		try {
	Authentication Ae=	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(entity.getEmail(), entity.getPassword()));
		
		}
		catch(BadCredentialsException e)
		{
			throw new Exception("Bad credentials "+e);
		}
		
	UserDetails  us=	mySec.loadUserByUsername(entity.getEmail());
	
	String token= jwtUtility.generateToken(us);
		
		
		return new JwtResponse(token);
	}
	

	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody @Valid UserEntity payload) {

		if(repository.existsByEmail(payload.getEmail()))
		{
			throw new AlreadyExistsException("email already exists "+payload.getEmail());
		}
		payload.setPassword(bCryptPasswordEncoder.encode(payload.getPassword()));
		repository.save(payload);
		 //service.addUserToDao(payload);
		 return new ResponseEntity<>(HttpStatus.OK);

	}
	
	@GetMapping("findUser/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public UserEntity findUserByid(long id)
	{
		
	return 	service.findUser(id);
		
	}
	
	
	@DeleteMapping("/deleteUsers/{id}")
	public ResponseEntity<UserEntity> deleteUser(long id)
	{
		
		UserEntity entity= service.delete(id);
		return new ResponseEntity<UserEntity>(entity,HttpStatus.NO_CONTENT);
		
	}
	
	@PutMapping("/updateUser")
	public  ResponseEntity<UserEntity> updateUserById(long id, UserEntity entity)
	{
		UserEntity us=	service.updateTheUser(id, entity);
		return new ResponseEntity<UserEntity>(us,HttpStatus.OK);
	}

}
