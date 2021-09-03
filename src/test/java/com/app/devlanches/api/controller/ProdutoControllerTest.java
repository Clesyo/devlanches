package com.app.devlanches.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.app.devlanches.api.controller.mock.ProdutoMock;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
class ProdutoControllerTest {

	private final static String URL = "/produto";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper object;

	@BeforeAll
	public void setup() {
		
	}
	@Test
	@DisplayName(value = "Produto quando criado retorna sucesso")
	void createProdutoSuccess() throws JsonProcessingException, Exception {
		mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(object.writeValueAsString(ProdutoMock.getData()))).andExpect(status().isCreated());
	}

	

}
