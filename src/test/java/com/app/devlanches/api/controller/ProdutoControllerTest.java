package com.app.devlanches.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
class ProdutoControllerTest {

	private final static String URL = "/produto";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper object;

	@Test
	@DisplayName(value = "Produto quando criado retorna sucesso")
	void createProdutoSuccess() throws JsonProcessingException, Exception {
		mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(object.writeValueAsString(ProdutoMock.getData()))).andExpect(status().isCreated());
	}

	@Test
	public void returnClienteById() throws Exception {
		mockMvc.perform(get(URL).param("id", "1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id").value("1"));
	}

}
