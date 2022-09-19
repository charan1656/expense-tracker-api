package com.saicharan.expensetrackerapi.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.saicharan.expensetrackerapi.customExceptions.ExpenseTrackerException;
import com.saicharan.expensetrackerapi.dao.ExpenseDao;
import com.saicharan.expensetrackerapi.dao.UserRepository;
import com.saicharan.expensetrackerapi.entity.ExpenseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExpenceServiceImple implements ExpenseService {
	
	@Autowired
	UserService service;
	
	@Autowired
	UserRepository repository;
	
	private ExpenseDao dao;

	public ExpenceServiceImple(ExpenseDao dao) {
		super();
		this.dao = dao;
	}
	
	
	


	




	@Override
	public ExpenseEntity findingById(long id) {
	Optional<ExpenseEntity> op=	dao.findById(id);
	
	if(op.isPresent())
	{
		return op.get();
	}
		
	throw new ExpenseTrackerException("unable to find the id"+id);
	}





	@Override
	public void deleteBasedonId(long id) {
		dao.deleteById(id);
		
	}





	@Override
	public ExpenseEntity addExpences(ExpenseEntity entity) {
		
		
		//System.out.println(principal.getName());
		
		if(entity==null) {
		
		throw new RuntimeException("entity should not be null");}
		else
		
		 entity.setEntity(service.getCurrentlyLoggedInUser());
	
		//Optional<UserEntity> us= repository. findByEmail(loggedInUser);
		
		
//		UserEntity userEntity;
//		if(us.isPresent())
//		{
//			
//			userEntity=us.get();
//			
//			
//			
//			entity.setEntity(userEntity);
//		}
			
		return dao.save(entity);
	}





	@Override
	public ExpenseEntity updateExpenseDetails(long id, ExpenseEntity expenseEntity) {
		ExpenseEntity expenseCheck=	dao.findById(id).orElseThrow(()-> new ExpenseTrackerException("id is not exists ..!!"+id));
		
		expenseCheck.setName(expenseEntity.getName() !=null ?  expenseEntity.getName():expenseCheck.getName());
		expenseCheck.setAmount( expenseEntity.getAmount()!=null ? expenseEntity.getAmount():expenseCheck.getAmount());
		expenseCheck.setCategory(expenseEntity.getCategory()!=null ? expenseEntity.getCategory():expenseCheck.getCategory());
		expenseCheck.setDate(expenseEntity.getDate() !=null ? expenseEntity.getDate():expenseCheck.getDate());
		expenseCheck.setDescription(expenseEntity.getDescription() !=null ?  expenseEntity.getDescription():expenseCheck.getDescription());
		
		return dao.save(expenseCheck);
	
		
	}










	@Override
	public Page<ExpenseEntity> findingAllExpense(Pageable pageable) {
		
	return	dao.findAll(pageable);
	}










	@Override
	public List<ExpenseEntity> findingByCategory(String category, Pageable pageable) {
		
		return dao.findByCategory(category, pageable).toList();
	
	}










	@Override
	public List<ExpenseEntity> findingByFilterName(String keyword, Pageable pageable) {
		
		
return 	dao.findByNameContaining(keyword, pageable).toList();
		
		
	}










	@Override
	public List<ExpenseEntity> fetchingByDates(Date startDate, Date endDate, Pageable pageable) {
		// TODO Auto-generated method stub
		if(startDate==null)
		{
		 startDate=new	Date(0);
		}
		if(endDate==null)
		{
			 endDate= new Date(System.currentTimeMillis());
		}
		
		return dao.findByDateBetween(startDate, endDate, pageable).toList();
	}










//	@Override
//	public ExpenseEntity findingByTwoId(long id) {
//		
//		
//		
//		Optional<ExpenseEntity> eu=	dao.findByUserIdAndId(service.getCurrentlyLoggedInUser().getId(), id);
//		
//		if(eu.isPresent())
//		{
//			return eu.get();
//		}
//		throw new ExpenseTrackerException("expense  not found for the id "+id);
//		
//	}
	
	

}
