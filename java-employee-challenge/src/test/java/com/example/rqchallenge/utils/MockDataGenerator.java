package com.example.rqchallenge.utils;

import java.util.ArrayList;
import java.util.List;

import com.example.rqchallenge.dtos.Employee;
import com.example.rqchallenge.dtos.EmployeeByIdResponseDTO;
import com.example.rqchallenge.dtos.EmployeeListResponseDTO;

public class MockDataGenerator {

	public static EmployeeListResponseDTO getEmployeeListResponseDTO() {
		EmployeeListResponseDTO responseDTO = new EmployeeListResponseDTO();
		List<Employee> data = new ArrayList<>();
		data.add(getEmployee());

		responseDTO.setData(data);
		return responseDTO;
	}

	public static EmployeeByIdResponseDTO getEmployeeByIdResponseDTO() {
		EmployeeByIdResponseDTO responseDTO = new EmployeeByIdResponseDTO();
		responseDTO.setData(getEmployee());
		responseDTO.setStatus("Success");
		return responseDTO;
	}

	public static Employee getEmployee() {
		Employee emp = new Employee();
		emp.setId("1");
		emp.setEmployeeName("testName");
		emp.setEmployeeAge("26");
		emp.setEmployeeSalary("1234");
		emp.setProfileImage("");

		return emp;

	}
}
