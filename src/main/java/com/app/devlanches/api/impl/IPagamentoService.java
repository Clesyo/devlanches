package com.app.devlanches.api.impl;

import java.util.List;

import com.app.devlanches.api.models.Pagamento;
import com.app.devlanches.api.models.dto.PagamentoDTO;

public interface IPagamentoService {

	Pagamento pagar(PagamentoDTO pagamento);
	List<Pagamento> pagarMais(List<PagamentoDTO> pagamentos); 
}
