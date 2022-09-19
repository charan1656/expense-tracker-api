package com.saicharan.expensetrackerapi.service;

import com.saicharan.expensetrackerapi.entity.UserEntity;

public interface UserService {
	
	//public UserEntity addUserToDao(UserEntity entity);

	public UserEntity findUser(long id);

	public UserEntity updateTheUser(long id, UserEntity entity);

	public UserEntity delete(long id);
	public UserEntity getCurrentlyLoggedInUser();

}
