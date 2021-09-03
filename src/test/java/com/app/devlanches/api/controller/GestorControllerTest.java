package com.app.devlanches.api.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;

import com.app.devlanches.api.controller.mock.CredentialMock;
import com.app.devlanches.api.controller.mock.GestorMock;
import com.app.devlanches.api.models.Gestor;
import com.app.devlanches.api.models.dto.CredencialDTO;
import com.app.devlanches.api.service.GestorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class GestorControllerTest {

	private static final String URL = "/gestor";
	private static final String URL_AUTH = "/gestor/auth";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper object;

	@MockBean
	private GestorService gestorService;

	@Test
	@DisplayName(value = "Gestor quando criado deve retorna sucesso.")
	public void deveRetornarSucesso() throws Exception {
		Gestor gestor = GestorMock.allData();
		mockMvc.perform(
				post(URL).contentType(MediaType.APPLICATION_JSON_VALUE).content(object.writeValueAsString(gestor)))
				.andExpect(status().isCreated());
	}

	@Test
	public void authenticateGestor() throws JsonProcessingException, Exception {
		CredencialDTO credential = CredentialMock.credential();
		when(gestorService.findByEmail(credential.getLogin())).thenReturn(GestorMock.allData());
		mockMvc.perform(post(URL_AUTH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(object.writeValueAsString(credential))).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.login").value(credential.getLogin()));

	}

	@Test
	public void authenticateInvalidPassword() throws JsonProcessingException, Exception {

		CredencialDTO credentialWithoutPassword = CredentialMock.credentialWithoutPassword();
		when(gestorService.findByEmail(credentialWithoutPassword.getLogin())).thenReturn(GestorMock.allData());

		mockMvc.perform(post(URL_AUTH).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(object.writeValueAsString(credentialWithoutPassword))).andExpect(status().isUnauthorized())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
				.andExpect(result -> assertTrue(result.getResolvedException().getMessage().contains("Invalid")));
	}

}
