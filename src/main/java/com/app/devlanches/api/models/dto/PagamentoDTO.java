package com.app.devlanches.api.models.dto;

import java.math.BigDecimal;

import com.app.devlanches.api.enums.FormaPagamento;

public class PagamentoDTO {

	public PagamentoDTO() {
	}
	
	private Long pedido;
	private FormaPagamento pagamento;
	private BigDecimal troco;
	private BigDecimal valorPago;
	private BigDecimal desconto;
	private BigDecimal total;
	
	public Long getPedido() {
		return pedido;
	}
	public void setPedido(Long pedido) {
		this.pedido = pedido;
	}
	public FormaPagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(FormaPagamento formaPagamento) {
		this.pagamento = formaPagamento;
	}
	public BigDecimal getTroco() {
		return troco;
	}
	public void setTroco(BigDecimal troco) {
		this.troco = troco;
	}
	public BigDecimal getValorPago() {
		return valorPago;
	}
	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}
	public BigDecimal getDesconto() {
		return desconto;
	}
	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	
}
