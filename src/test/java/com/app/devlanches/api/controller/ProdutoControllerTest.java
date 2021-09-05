package com.app.devlanches.api.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.server.ResponseStatusException;

import com.app.devlanches.api.configuration.security.auth.jwt.JwtService;
import com.app.devlanches.api.controller.mock.ClienteMock;
import com.app.devlanches.api.controller.mock.GestorMock;
import com.app.devlanches.api.controller.mock.ProdutoMock;
import com.app.devlanches.api.exception.EntityNotExist;
import com.app.devlanches.api.service.GestorService;
import com.app.devlanches.api.service.ProdutoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:/application-test.properties")
@TestInstance(Lifecycle.PER_CLASS)
class ProdutoControllerTest {

	private final static String URL_PRODUTO = "/produto";
	private final static String URL_PRODUTO_ID = "/produto/{id}";
	private final static String HEADER_PREFIX = "Authorization";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper object;

	@Autowired
	private JwtService jwtService;
	@MockBean
	private ProdutoService produtoService;
	@MockBean
	private GestorService gestorService;

	private String token;

	@BeforeAll
	public void setup() {
		when(produtoService.findById(ProdutoMock.getDataOne().getId())).thenReturn(ProdutoMock.getDataOne());
	}

	@Test
	@DisplayName(value = "Produto quando criado retorna sucesso")
	void createProdutoSuccess() throws JsonProcessingException, Exception {
		token = jwtService.gerarToken(GestorMock.allData());
		when(gestorService.findByEmail(GestorMock.allData().getEmail())).thenReturn(GestorMock.allData());
		mockMvc.perform(post(URL_PRODUTO).content(object.writeValueAsString(ProdutoMock.getDataOne()))
				.header(HEADER_PREFIX, "Bearer " + token).contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8))
				.andExpect(status().isCreated());
	}

	@Test
	@DisplayName(value = "Returna produto pelo codigo ID ")
	void returnProdutoById() throws Exception {
		token = jwtService.gerarToken(GestorMock.allData());
		when(gestorService.findByEmail(GestorMock.allData().getEmail())).thenReturn(GestorMock.allData());
		mockMvc.perform(get(URL_PRODUTO_ID, 1L).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).header(HEADER_PREFIX,
				"Bearer " + token)).andExpect(status().isOk());
	}
	@Test
	@DisplayName(value = "Detela um produto pelo seu cogido ID ")
	void deleteProdutoById() throws Exception {
		token = jwtService.gerarToken(GestorMock.allData());
		when(gestorService.findByEmail(GestorMock.allData().getEmail())).thenReturn(GestorMock.allData());
		mockMvc.perform(delete(URL_PRODUTO_ID, 1L).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).header(HEADER_PREFIX,
				"Bearer " + token)).andExpect(status().isNoContent());
	}

}
