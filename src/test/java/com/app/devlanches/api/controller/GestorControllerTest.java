package com.app.devlanches.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.app.devlanches.api.controller.mock.GestorMock;
import com.app.devlanches.api.models.Gestor;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest
public class GestorControllerTest {

	private static final String URL = "/gestor";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper object;

	@Test
	@DisplayName(value = "Gestor quando criado deve retorna sucesso.")
	public void deveRetornarSucesso() throws Exception {
		Gestor gestor = GestorMock.allData();
		mockMvc.perform(
				post(URL).contentType(MediaType.APPLICATION_JSON_VALUE).content(object.writeValueAsString(gestor)))
				.andExpect(status().isCreated());
	}
}
