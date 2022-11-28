package com.ty.crud.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.crud.pojo.EmployeeRequest;
import com.ty.crud.pojo.EmployeeResponse;
import com.ty.crud.pojo.Response;
import com.ty.crud.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService service;

	@PostMapping("/add-employee")
	public ResponseEntity<EmployeeResponse> addEmployee(@NotNull @Valid @RequestBody EmployeeRequest request) {
		return ResponseEntity.ok(service.addEmployee(request));
	}

	@GetMapping("/find-employee")
	public ResponseEntity<Response> findEmployee(@NotNull @RequestHeader String username) {
		return ResponseEntity.ok(service.findEmployee(username));
	}

	@GetMapping("/find-all-employees")
	public ResponseEntity<Response> findAllEmployee() {
		return ResponseEntity.ok(service.findAllEmployees());
	}

}
