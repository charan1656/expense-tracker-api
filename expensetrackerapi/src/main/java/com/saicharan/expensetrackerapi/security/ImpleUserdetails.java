package com.saicharan.expensetrackerapi.security;

import java.util.ArrayList;
import java.util.Optional;

import com.saicharan.expensetrackerapi.customExceptions.ExpenseTrackerException;
import com.saicharan.expensetrackerapi.dao.UserRepository;
import com.saicharan.expensetrackerapi.entity.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class ImpleUserdetails  implements UserDetailsService{

	@Autowired
	UserRepository repository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		
		UserEntity user= repository.findByEmail(email).orElseThrow(()->new ExpenseTrackerException("email doesnt exists !!!!! "+ email));
		
		
		return new User (user.getEmail(),user.getPassword(),new ArrayList());
	}

}
