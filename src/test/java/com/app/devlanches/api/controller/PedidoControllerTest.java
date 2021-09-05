package com.app.devlanches.api.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.app.devlanches.api.controller.mock.ClienteMock;
import com.app.devlanches.api.controller.mock.PedidoMock;
import com.app.devlanches.api.controller.mock.ProdutoMock;
import com.app.devlanches.api.exception.ApiException;
import com.app.devlanches.api.models.Cliente;
import com.app.devlanches.api.models.Produto;
import com.app.devlanches.api.repository.PedidoRepository;
import com.app.devlanches.api.service.ClienteService;
import com.app.devlanches.api.service.ProdutoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class PedidoControllerTest {

	private final static String URL = "/pedido";
	private final static String URL_CANCEL_PEDIDO = "/pedido/{id}/cancelar";
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper object;

	@MockBean
	private PedidoRepository pedidoRepository;

	@MockBean
	private ProdutoService produtoService;
	@MockBean
	private ClienteService clienteService;

	@BeforeEach
	public void setup() {
	}

	@Test
	void createPedidoSucesso() throws JsonProcessingException, Exception {
		Produto produto = ProdutoMock.getDataTwo();
		Optional<Cliente> cliente = ClienteMock.getData();
		when(produtoService.findById(produto.getId())).thenReturn(produto);
		when(clienteService.findById(cliente.get().getId())).thenReturn(ClienteMock.getData().get());
		mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(object.writeValueAsString(PedidoMock.getDataDTO()))).andExpect(status().isCreated());

	}

	@Test
	@DisplayName(value = "Restorna exceção com pedido sem items")
	public void failCreatePedidoWithoutItens() throws JsonProcessingException, Exception {
		Produto produto = ProdutoMock.getDataOne();
		Optional<Cliente> cliente = ClienteMock.getData();
		when(produtoService.findById(produto.getId())).thenReturn(produto);
		when(clienteService.findById(cliente.get().getId())).thenReturn(
				ClienteMock.getData().get());
		mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(object.writeValueAsString(PedidoMock.getDataWithoutItems())))
				.andExpect(status().isBadRequest())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ApiException)).andExpect(
						result -> assertTrue(result.getResolvedException().getMessage().contains("pedido sem itens")));

	}

	@Test
	@DisplayName(value = "Retorna exceção caso tente cancalar pedido cancelado ou finalizado.")
	public void failCancelPedidoAsPendente() throws Exception {
		when(pedidoRepository.findById(1L)).thenReturn(PedidoMock.getData());

		mockMvc.perform(patch(URL_CANCEL_PEDIDO, 1L)).andExpect(status().isBadRequest())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ApiException));
	}

}
