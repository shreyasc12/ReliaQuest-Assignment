package com.example.rqchallenge.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.rqchallenge.constants.ApiConstants;
import com.example.rqchallenge.dtos.Employee;
import com.example.rqchallenge.dtos.EmployeeByIdResponseDTO;
import com.example.rqchallenge.dtos.EmployeeListResponseDTO;
import com.example.rqchallenge.service.EmployeeService;

import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	public WebClient webClient;

	private final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	public List<Employee> getAllEmployeeList() {
		logger.debug("EmployeeService|getAllEmployeeList|Entry");
		Mono<EmployeeListResponseDTO> employeeMono = webClient.get().uri(ApiConstants.REST_API_URI_GET_ALL_EMPLOYEES)
				.retrieve().bodyToMono(EmployeeListResponseDTO.class);

		logger.debug("EmployeeService|getAllEmployeeList|Exit");
		return employeeMono.block().getData();
	}

	public Employee getEmployeeById(String id) {
		logger.debug("EmployeeService|getEmployeeById|Entry");

		Mono<EmployeeByIdResponseDTO> employeeMono = webClient.get()
				.uri(ApiConstants.REST_API_URI_GET_EMPLOYEE_BY_ID + id).retrieve()
				.bodyToMono(EmployeeByIdResponseDTO.class);

		logger.debug("EmployeeService|getEmployeeById|Exit");

		return employeeMono.block().getData();
	}

	public List<Employee> getEmployeeBySearchName(String searchString) {
		logger.debug("EmployeeService|getEmployeeBySearchName|Entry");

		List<Employee> employeeList = getAllEmployeeList();

		logger.debug("EmployeeService|getEmployeeBySearchName|Exit");

		return employeeList.stream().filter(emp -> emp.getEmployeeName().toLowerCase().contains(searchString.toLowerCase()))
				.collect(Collectors.toList());
	}

	public Integer getHighestSalaryOfEmployee() {
		logger.debug("EmployeeService|getHighestSalaryOfEmployee|Entry");

		List<Employee> employeeList = getAllEmployeeList();
		logger.debug("EmployeeService|getHighestSalaryOfEmployee|Exit");

		return employeeList.stream().map(emp -> emp.getEmployeeSalary()).mapToInt(Integer::parseInt).max().getAsInt();
	}

	public List<String> getTopTenHighestEarningEmployeeNames() {
		logger.debug("EmployeeService|getTopTenHighestEarningEmployeeNames|Entry");

		List<Employee> employeeList = getAllEmployeeList();

		logger.debug("EmployeeService|getTopTenHighestEarningEmployeeNames|Exit");

		return employeeList.stream().sorted().map(emp -> emp.getEmployeeName()).limit(10).collect(Collectors.toList());
	}

	public Object createEmployee(Map<String, Object> employeeInput) {
		logger.debug("EmployeeService|createEmployee|Entry");

		Object employeeMono = webClient.post().uri(ApiConstants.REST_API_URI_CREATE_EMPLOYEE)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(BodyInserters.fromValue(employeeInput)).retrieve().bodyToMono(Map.class).block();

		logger.debug("EmployeeService|createEmployee|Exit");

		return employeeMono;
	}

	public Object deleteEmployee(String id) {
		logger.debug("EmployeeService|deleteEmployee|Entry");

		Object employeeMono = webClient.delete().uri(ApiConstants.REST_API_URI_DELETE_EMPLOYEE + id)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).retrieve().bodyToMono(Object.class)
				.block();

		logger.debug("EmployeeService|deleteEmployee|Exit");

		return employeeMono;
	}

}
