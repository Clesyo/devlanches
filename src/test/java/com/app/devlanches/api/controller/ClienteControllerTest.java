package com.app.devlanches.api.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;

import com.app.devlanches.api.configuration.security.auth.jwt.JwtService;
import com.app.devlanches.api.controller.mock.ClienteMock;
import com.app.devlanches.api.controller.mock.GestorMock;
import com.app.devlanches.api.repository.ClienteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:/application-test.properties")
@TestInstance(Lifecycle.PER_CLASS)
class ClienteControllerTest {
	private static final String URL = "/cliente";
	private static final String URL_PARAM = "/cliente/{id}";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper object;

	@Autowired
	private JwtService jwtService;

	private String token;

	@MockBean
	private ClienteRepository clienteRepository;

	@BeforeAll
	public void setup() {
		doReturn(ClienteMock.getData()).when(clienteRepository).findById(ClienteMock.getData().get().getId());
		token = jwtService.gerarToken(GestorMock.allData());
	}

	@Test
	@DisplayName(value = "Cliente quando criado deve retornar sucesso.")
	public void createClienteSuccess() throws JsonProcessingException, Exception {
		mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(object.writeValueAsString(ClienteMock.getData()))).andExpect(status().isCreated());
	}

	@Test
	@DisplayName(value = "Retorna um cliente através do seu codigo ID")
	public void returnClienteById() throws Exception {
		mockMvc.perform(get(URL_PARAM,"id","1").header("Authorization", "Bearer " + token)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
		
	}

}