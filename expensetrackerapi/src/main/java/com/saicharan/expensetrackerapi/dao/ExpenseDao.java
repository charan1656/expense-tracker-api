package com.saicharan.expensetrackerapi.dao;

import java.util.Date;
import java.util.Optional;

import com.saicharan.expensetrackerapi.entity.ExpenseEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ExpenseDao extends PagingAndSortingRepository<ExpenseEntity, Long> {
	
	Page<ExpenseEntity> findByCategory(String category,Pageable pageable);
	
	Page<ExpenseEntity> findByNameContaining(String keyword,Pageable pageable);
	
	Page<ExpenseEntity> findByDateBetween(Date startDatec, Date endDate,Pageable pageable);
	
	
	//Optional<ExpenseEntity> findByUserIdAndId(Long userid,long id);

}
