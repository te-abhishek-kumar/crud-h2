package com.ty.crud.service;

import static com.ty.crud.pojo.ConstantData.ALL_EMPLOYEES_FETCHED;
import static com.ty.crud.pojo.ConstantData.EMPLOYEE_ADDED_SUCCESSFULLY;
import static com.ty.crud.pojo.ConstantData.EMPLOYEE_FETCHED_SUCCESSFULLY;
import static com.ty.crud.pojo.ConstantData.EMPLOYEE_NOT_FOUND;
import static com.ty.crud.pojo.ConstantData.PLEASE_ENTER_DETAILS;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ty.crud.entity.Employee;
import com.ty.crud.exception.EmployeeException;
import com.ty.crud.pojo.EmployeeRequest;
import com.ty.crud.pojo.EmployeeResponse;
import com.ty.crud.pojo.Response;
import com.ty.crud.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository repository;

	public EmployeeResponse addEmployee(EmployeeRequest request) {

		if (request != null) {
			Employee employee = Employee.builder().name(request.getName()).address(request.getAddress())
					.email(request.getEmail()).username(request.getUsername()).dob(request.getDob())
					.mobile(request.getMobile()).build();
			repository.save(employee);
			return EmployeeResponse.builder().isError(false).message(EMPLOYEE_ADDED_SUCCESSFULLY).build();
		}
		throw new EmployeeException(PLEASE_ENTER_DETAILS);
	}

	public Response findAllEmployees() {
		List<Employee> employees = repository.findAll();
		if (employees.isEmpty())
			throw new EmployeeException(EMPLOYEE_NOT_FOUND);
		return Response.builder().isError(false).message(ALL_EMPLOYEES_FETCHED).data(employees).build();
	}

	public Response findEmployee(String username) {
		if (username.isEmpty())
			throw new EmployeeException("Please enter username");
		return repository
				.findByUsername(username).map(employee -> Response.builder().isError(false)
						.message(EMPLOYEE_FETCHED_SUCCESSFULLY).data(employee).build())
				.orElseThrow(() -> new EmployeeException(EMPLOYEE_NOT_FOUND));
	}

}
