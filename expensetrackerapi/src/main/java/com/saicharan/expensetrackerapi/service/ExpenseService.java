package com.saicharan.expensetrackerapi.service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import com.saicharan.expensetrackerapi.entity.ExpenseEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpenseService {
	
	
 Page<ExpenseEntity> findingAllExpense(Pageable pageable);

ExpenseEntity findingById(long id);

void deleteBasedonId(long id);

ExpenseEntity addExpences(ExpenseEntity entity);

//ExpenseEntity findingByTwoId(long id);

ExpenseEntity updateExpenseDetails(long id, ExpenseEntity expenseEntity);

List<ExpenseEntity> findingByCategory(String category, Pageable pageable);

List<ExpenseEntity> findingByFilterName(String keyword, Pageable pageable);

List<ExpenseEntity> fetchingByDates(Date startDate, Date endDate, Pageable pageable);

}
