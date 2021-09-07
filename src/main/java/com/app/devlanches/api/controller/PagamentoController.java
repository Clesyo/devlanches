package com.app.devlanches.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.devlanches.api.impl.IPagamentoService;
import com.app.devlanches.api.models.Pagamento;
import com.app.devlanches.api.models.dto.PagamentoDTO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/caixa", produces = MediaType.APPLICATION_JSON_VALUE)
public class PagamentoController {

	@Autowired
	private IPagamentoService iPagamentoService;

	@PostMapping("/pagar")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Realiza o pagamento de um pedido")
	@ApiResponses({@ApiResponse(code = 201, message = "Pedido do pago com sucesso")})
	public Pagamento pagar(@RequestBody PagamentoDTO pagamento) {
		return iPagamentoService.pagar(pagamento);
	}

	@PostMapping("/pagarMais")
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiOperation("Realiza o pagamento de um pedido com mais de uma forma de pagamento")
	@ApiResponses({@ApiResponse(code = 201, message = "Pedido do pago com sucesso")})
	public List<Pagamento> pagaMais(@RequestBody List<PagamentoDTO> pagamentos) {
		return iPagamentoService.pagarMais(pagamentos);
	}
}
