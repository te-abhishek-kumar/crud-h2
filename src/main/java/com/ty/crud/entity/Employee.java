package com.ty.crud.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "emp_details")
public class Employee {

	@Id
	@SequenceGenerator(name = "emp_seq", initialValue = 100, allocationSize = 3)
	@GeneratedValue(generator = "emp_seq")
	@Column(name = "emp_id", unique = true, nullable = false, precision = 10)
	private Integer empId;
	@Column(name = "emp_name")
	private String name;
	@Column(name = "emp_username")
	private String username;
	@Column(name = "emp_add")
	private String address;
	@Column(name = "emp_mob")
	private Long mobile;
	@Column(name = "emp_email")
	private String email;
	@Column(name = "emp_dob")
	@Temporal(TemporalType.DATE)
	private Date dob;

}
