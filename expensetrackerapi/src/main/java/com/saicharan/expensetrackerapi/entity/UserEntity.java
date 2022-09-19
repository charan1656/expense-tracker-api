package com.saicharan.expensetrackerapi.entity;



import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull(message = "name should not be null")
	private String name;
	
	@NotBlank(message = "email should not be blank")
	@Column(unique =true)
	private String email;
	@Size(min=3, message = "minimum it should have 3 characters ...")
	
	private String password;
	@Max( value = 88)
	private long age;
	
	
	@CreationTimestamp
	private Timestamp createdAt;
	
	@UpdateTimestamp
	private Timestamp updatedAt;
	@OneToMany(mappedBy = "entity",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private List<ExpenseEntity> expenseEntity;

}
