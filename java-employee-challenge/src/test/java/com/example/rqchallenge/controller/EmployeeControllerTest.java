package com.example.rqchallenge.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.rqchallenge.RqChallengeApplicationTests;
import com.example.rqchallenge.constants.ApiConstants;
import com.example.rqchallenge.dtos.EmployeeByIdResponseDTO;
import com.example.rqchallenge.dtos.EmployeeListResponseDTO;
import com.example.rqchallenge.service.impl.EmployeeServiceImpl;
import com.example.rqchallenge.utils.MockDataGenerator;

import reactor.core.publisher.Mono;

public class EmployeeControllerTest extends RqChallengeApplicationTests {
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	@MockBean
	private WebClient webClient;
	@Mock
	private WebClient.Builder webClientBuilder;
	@Mock
	private WebClient.RequestHeadersUriSpec RequestHeadersUriSpec;
	@Mock
	private WebClient.RequestHeadersSpec requestHeaderSpec;
	@Mock
	private WebClient.RequestBodyUriSpec requestBodyUriSpec;
	@Mock
	private WebClient.RequestBodySpec requestBodySpec;
	@Mock
	private WebClient.ResponseSpec responseSpec;

	@Test
	public void testGetAllEmployeeListGetApi2XXSuccess() throws Exception {
		when(webClient.get()).thenReturn(RequestHeadersUriSpec);
		when(RequestHeadersUriSpec.uri(anyString())).thenReturn(requestHeaderSpec);
		when(requestHeaderSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(EmployeeListResponseDTO.class))
				.thenReturn(Mono.just(MockDataGenerator.getEmployeeListResponseDTO()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testGetAllEmployeeListGetApi5XXFailure() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().is5xxServerError());
	}

	@Test
	public void testGetEmployeesByNameSearchGetApi2XXSuccess() throws Exception {
		when(webClient.get()).thenReturn(RequestHeadersUriSpec);
		when(RequestHeadersUriSpec.uri(anyString())).thenReturn(requestHeaderSpec);
		when(requestHeaderSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(EmployeeListResponseDTO.class))
				.thenReturn(Mono.just(MockDataGenerator.getEmployeeListResponseDTO()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(ApiConstants.REST_API_URI_GET_EMPLOYEES_BY_NAME, "Shreyas")
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testGetEmployeesByNameSearchGetApi5XXFailure() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get(ApiConstants.REST_API_URI_GET_EMPLOYEES_BY_NAME, "Shreyas")
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().is5xxServerError());
	}

	@Test
	public void testGetEmployeeByIdGetApi2XXSuccess() throws Exception {
		when(webClient.get()).thenReturn(RequestHeadersUriSpec);
		when(RequestHeadersUriSpec.uri(anyString())).thenReturn(requestHeaderSpec);
		when(requestHeaderSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(EmployeeByIdResponseDTO.class))
				.thenReturn(Mono.just(MockDataGenerator.getEmployeeByIdResponseDTO()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiConstants.REST_API_URI_EMPLOYEE_ID, "id")
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testGetEmployeeByIdGetApi5XXFailure() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiConstants.REST_API_URI_EMPLOYEE_ID, "id")
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().is5xxServerError());
	}

	@Test
	public void testGetHighestSalaryOfEmployeesGetApi2XXSuccess() throws Exception {
		when(webClient.get()).thenReturn(RequestHeadersUriSpec);
		when(RequestHeadersUriSpec.uri(anyString())).thenReturn(requestHeaderSpec);
		when(requestHeaderSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(EmployeeListResponseDTO.class))
				.thenReturn(Mono.just(MockDataGenerator.getEmployeeListResponseDTO()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiConstants.REST_API_URI_GET_HIGHEST_SALARY)
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testGetHighestSalaryOfEmployeesGetApi5XXFailure() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiConstants.REST_API_URI_GET_HIGHEST_SALARY)
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().is5xxServerError());
	}

	@Test
	public void testGetTopTenHighestEarningEmployeeNamesGetApi2XXSuccess() throws Exception {
		when(webClient.get()).thenReturn(RequestHeadersUriSpec);
		when(RequestHeadersUriSpec.uri(anyString())).thenReturn(requestHeaderSpec);
		when(requestHeaderSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(EmployeeListResponseDTO.class))
				.thenReturn(Mono.just(MockDataGenerator.getEmployeeListResponseDTO()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiConstants.REST_API_URI_GET_TOP_TEN_EMPLOYEE_NAMES)
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testGetTopTenHighestEarningEmployeeNamesGetApi5XXFailure() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(ApiConstants.REST_API_URI_GET_TOP_TEN_EMPLOYEE_NAMES)
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().is5xxServerError());
	}

	@Test
	public void testCreateEmployeePostApiApi2XXSuccess() throws Exception {
		Map<String, Object> map = new HashMap<>();
		when(webClient.post()).thenReturn(requestBodyUriSpec);
		when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
		when(requestBodySpec.header(any(), any())).thenReturn(requestBodySpec);
		when(requestBodySpec.body(any())).thenReturn(requestHeaderSpec);
		when(requestHeaderSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(Map.class)).thenReturn(Mono.just(map));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/").content("{}")
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testCreateEmployeePostApi5XXFailure() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/").content("{}")
				.contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().is5xxServerError());
	}

	@Test
	public void testdeleteEmployeeByIdDeleteApiApi2XXSuccess() throws Exception {
		when(webClient.delete()).thenReturn(RequestHeadersUriSpec);
		when(RequestHeadersUriSpec.uri(anyString())).thenReturn(requestHeaderSpec);
		when(requestHeaderSpec.header(any(), any())).thenReturn(requestHeaderSpec);
		when(requestHeaderSpec.retrieve()).thenReturn(responseSpec);
		when(responseSpec.bodyToMono(Object.class)).thenReturn(Mono.just(MockDataGenerator.getEmployee()));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(ApiConstants.REST_API_URI_EMPLOYEE_ID, "id")
				.content("{}").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testdeleteEmployeeByIdDeleteApi5XXFailure() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(ApiConstants.REST_API_URI_EMPLOYEE_ID, "1")
				.content("{}").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().is5xxServerError());
	}
}
