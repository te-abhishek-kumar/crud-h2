package com.ty.crud.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeInfo {
	
	private String name;
	private String address;
	private Long mobile;
	private String email;
	private Date dob;

}
