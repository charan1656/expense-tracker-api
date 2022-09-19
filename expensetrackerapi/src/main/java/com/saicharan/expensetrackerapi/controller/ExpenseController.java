package com.saicharan.expensetrackerapi.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.saicharan.expensetrackerapi.entity.ExpenseEntity;
import com.saicharan.expensetrackerapi.service.ExpenseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExpenseController {
	
	
	@Autowired
	ExpenseService expenceService;
	
	
	
	
	
	
	
	@RequestMapping("/")
	public String home()
	{
		return " working fine dude";
	}
	
	@GetMapping("/expense/findingByDate")
	public ResponseEntity<List<ExpenseEntity>> getByDate(@RequestParam Date startDate, Date endDate,  Pageable pageable)
	{
	List<ExpenseEntity> en=	expenceService.fetchingByDates( startDate, endDate,pageable);
	return new ResponseEntity<List<ExpenseEntity>>(en,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/expense/categoryByname")
	public ResponseEntity<List<ExpenseEntity>> getByFilteringName(@RequestParam String keyword,Pageable pageable)
	{
	List<ExpenseEntity> en=	expenceService.findingByFilterName(keyword,pageable);
	return new ResponseEntity<List<ExpenseEntity>>(en,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/expense/category")
	public ResponseEntity<List<ExpenseEntity>> getByCategory(@RequestParam String category,Pageable pageable)
	{
	List<ExpenseEntity> en=	expenceService.findingByCategory(category,pageable);
	return new ResponseEntity<List<ExpenseEntity>>(en,HttpStatus.ACCEPTED);
		
	}
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping("/addExpenses")
	public ExpenseEntity addExpenses(@RequestBody  @Valid
			ExpenseEntity entity,Principal principal )
	{
		
		ExpenseEntity en=	 expenceService.addExpences(entity );
		return en ;
		
		
	}
	
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@PutMapping("/{id}/expense")
    public ResponseEntity<ExpenseEntity> updateEmployee(@PathVariable long id,@RequestBody ExpenseEntity expenseEntity) {
		
	ExpenseEntity em=	expenceService.updateExpenseDetails(id,expenseEntity);
	return ResponseEntity.ok(em);
	}
	
	@GetMapping("/expenses")
	@ResponseStatus(value = HttpStatus.OK)
	public List<ExpenseEntity> fetchAll(Pageable pageable)
	{
		return expenceService.findingAllExpense(pageable).toList();
	}
	
	@GetMapping("/expense/{id}")
	public ExpenseEntity getExpenseById(@PathVariable("id") long id)
	{
	
		return expenceService.findingById(id);}
		
	
	
	@DeleteMapping("/delete")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public ResponseEntity<String> deleteId(@RequestParam("id") long id)
	{
		
		expenceService.deleteBasedonId(id);
		return new ResponseEntity<String >("deleted id successfully",HttpStatus.ACCEPTED);
	}

}
