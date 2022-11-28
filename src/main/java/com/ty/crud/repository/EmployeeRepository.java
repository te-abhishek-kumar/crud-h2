package com.ty.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.crud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Optional<Employee> findByUsername(String username);

	Optional<Employee> findByMobile(Long mobile);

}
