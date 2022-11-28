package com.ty.crud.pojo;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRequest {

	@NotNull(message = "name cannot be null")
	@NotBlank(message = "name cannot be blank")
	private String name;
	@NotNull(message = "address cannot be null")
	@NotBlank(message = "address cannot be blank")
	private String address;
	@NotNull(message = "username cannot be null")
	@NotBlank(message = "username cannot be blank")
	private String username;
	@Min(value = 1000000000l, message = "mobile number cannot be less than 10 digits")
	@Max(value = 9999999999l, message = "mobile number cannot be greater than 10 digits")
	private Long mobile;
	@Email(message = "email must be a well-formed email address")
	private String email;
	@Past(message = "dob must be a past date")
	private Date dob;

}
