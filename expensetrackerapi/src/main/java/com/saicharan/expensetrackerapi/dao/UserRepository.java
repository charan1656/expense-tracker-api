package com.saicharan.expensetrackerapi.dao;

import java.util.Optional;

import com.saicharan.expensetrackerapi.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository  extends JpaRepository<UserEntity, Long>{
	
	boolean existsByEmail(String name);

	Optional<UserEntity> findByEmail(String email );
	
	//xUserEntity findByEmail(String email );
	

//	void findbyEmail();

}
