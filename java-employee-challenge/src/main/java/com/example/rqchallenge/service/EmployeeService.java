package com.example.rqchallenge.service;

import java.util.List;
import java.util.Map;

import com.example.rqchallenge.dtos.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployeeList();

	Employee getEmployeeById(String id);

	List<Employee> getEmployeeBySearchName(String searchString);

	Integer getHighestSalaryOfEmployee();

	List<String> getTopTenHighestEarningEmployeeNames();

	Object createEmployee(Map<String, Object> employeeInput);

	Object deleteEmployee(String id);
	
}
