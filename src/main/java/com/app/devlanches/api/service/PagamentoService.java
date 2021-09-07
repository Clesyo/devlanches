package com.app.devlanches.api.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.devlanches.api.enums.FormaPagamento;
import com.app.devlanches.api.enums.StatusPedido;
import com.app.devlanches.api.exception.ApiException;
import com.app.devlanches.api.impl.IPagamentoService;
import com.app.devlanches.api.models.Pagamento;
import com.app.devlanches.api.models.Pedido;
import com.app.devlanches.api.models.dto.PagamentoDTO;
import com.app.devlanches.api.repository.PagamentoRepository;

@Service
public class PagamentoService implements IPagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private PedidoService pedidoService;

	@Override
	public Pagamento pagar(PagamentoDTO pagamento) {
		Pedido pedido = pedidoService.findById(pagamento.getPedido());

		if (pedido != null && pedido.getStatus() == StatusPedido.FINALIZADO) {
			pagamento.setTotal(pedido.getTotal());
			if (pagamento.getPagamento().equals(FormaPagamento.DINHEIRO)) {
				if (pagamento.getValorPago().doubleValue() >= pagamento.getTotal().doubleValue()) {
					pagamento.setTroco(BigDecimal
							.valueOf(pagamento.getValorPago().doubleValue() - pagamento.getTotal().doubleValue()));
				}

				if (pagamento.getDesconto().doubleValue() > 0) {
					pagamento.setTotal(BigDecimal
							.valueOf(pagamento.getTotal().doubleValue() - pagamento.getDesconto().doubleValue()));
				}
			}

			Pagamento pago = new Pagamento(FormaPagamento.valueOf(pagamento.getPagamento().name()),
					pagamento.getTroco(), pagamento.getValorPago(), pagamento.getDesconto(), pagamento.getTotal(),
					pedido);

			pedidoService.changeStatusPedido(pedido.getId(), StatusPedido.PAGO);

			return pagamentoRepository.save(pago);
		} else {
			throw new ApiException("Pedido não encontrado, ou ainda não foi finalizado.");
		}
	}

	@Override
	public List<Pagamento> pagarMais(List<PagamentoDTO> pagamentos) {

		List<Pagamento> pagos = pagamentos.stream().map(pagamento -> {
			Pedido pedido = pedidoService.findById(pagamento.getPedido());

			if (pedido != null && pedido.getStatus().equals(StatusPedido.FINALIZADO)) {
				if (pagamento.getPagamento().equals(FormaPagamento.DINHEIRO)) {
					if (pagamento.getValorPago().doubleValue() > pagamento.getTotal().doubleValue()) {
						pagamento.setTroco(BigDecimal
								.valueOf(pagamento.getValorPago().doubleValue() - pagamento.getTotal().doubleValue()));
					} else

					if (pagamento.getDesconto().doubleValue() > 0) {
						pagamento.setTotal(BigDecimal
								.valueOf(pagamento.getTotal().doubleValue() - pagamento.getDesconto().doubleValue()));
					}
				}

				pedidoService.changeStatusPedido(pedido.getId(), StatusPedido.PAGO);
				return new Pagamento(FormaPagamento.valueOf(pagamento.getPagamento().name()), pagamento.getTroco(),
						pagamento.getValorPago(), pagamento.getDesconto(), pagamento.getTotal(), pedido);
			} else {
				throw new ApiException("Pedido não encontrado, ou ainda não foi finalizado.");
			}

		}).collect(Collectors.toList());
		return pagamentoRepository.saveAll(pagos);
	}

}
