package com.saicharan.expensetrackerapi.service;

import java.util.Optional;

import com.saicharan.expensetrackerapi.customExceptions.AlreadyExistsException;
import com.saicharan.expensetrackerapi.customExceptions.ExpenseTrackerException;
import com.saicharan.expensetrackerapi.dao.UserRepository;
import com.saicharan.expensetrackerapi.entity.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImple  implements UserService{
	
	@Autowired
	UserRepository repo;



	@Override
	public UserEntity findUser(long id) {
		// TODO Auto-generated method stub
		
		return repo.findById(id).orElseThrow(()->new ExpenseTrackerException("User id not found"));
		
		
	}



	@Override
	public UserEntity updateTheUser(long id, UserEntity entity) {
		
		
		
		UserEntity user= repo.findById(id).orElseThrow(()->new ExpenseTrackerException("User id not found "));
		
		user.setName(entity.getName() !=null ? entity.getName():user.getName() );
		return repo.save(user);
		
	
	}



	@Override
	public UserEntity delete(long id) {
		
		
		return repo.findById(id).orElseThrow(()->new ExpenseTrackerException("User id not found "));
		
	}



	@Override
	public UserEntity getCurrentlyLoggedInUser() {
		
	Authentication a=	SecurityContextHolder.getContext().getAuthentication();
		
		String email= a.getName();
		return repo.findByEmail(email).orElseThrow(()->new ExpenseTrackerException("login user unable to find "+email));
	}



//	@Override
//	public UserEntity addUserToDao(UserEntity entity) {
//		// TODO Auto-generated method stub
////UserEntity entity2= new UserEntity();
//		
//		if(repo.existsByEmail(entity.getEmail()));
//		{
//			throw new AlreadyExistsException("user name already exists"+entity.getEmail());
//		}
//		
//		 return repo.save(entity);
//		
//		
////		ModelMapper mapper = new ModelMapper();
////		mapper.map(entity, entity2);
//		
//		
//		//BeanUtils.copyProperties(entity, entity2);
//
//	}

}
