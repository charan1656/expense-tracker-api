package com.saicharan.expensetrackerapi.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ExpenseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@NotNull(message = "name should not be null")
	private String name;
	@NotNull(message = "description should not be null")
	private String description;
	@NotNull(message="amount should not be null")
	private BigDecimal amount ;
	@NotNull(message = "category should be there")
	private String category;
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;
	 @CreationTimestamp
	 @Column(name="created_At" ,nullable = false,updatable = false)
	 private Timestamp createdAt;
	 @UpdateTimestamp
	 private Timestamp updatedAt;

	 @ManyToOne()
	 private UserEntity entity;	 
	 
}
