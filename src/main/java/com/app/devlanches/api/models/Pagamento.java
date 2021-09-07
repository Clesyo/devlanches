package com.app.devlanches.api.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.app.devlanches.api.enums.FormaPagamento;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "pagamento")
public class Pagamento {

	public Pagamento() {
	}
	
	public Pagamento(FormaPagamento formaPagamento, BigDecimal troco, BigDecimal valorPago,
			BigDecimal desconto,BigDecimal total, Pedido pedido) {
		this.formaPagamento = formaPagamento;
		this.troco = troco;
		this.valorPago = valorPago;
		this.desconto = desconto;
		this.total = total;
		this.pedido = pedido;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	@ApiModelProperty("Formas de pagamento: DINHEIRO, CARTAO_CREDITO, CARTAO_DEBIDO, PIX")
	private FormaPagamento formaPagamento;
	@Column(columnDefinition = "decimal(19,2) default 0.00")
	@ApiModelProperty("Caso o pagameto seja em dinheiro")
	private BigDecimal troco;
	@Column(name = "valor_pago", columnDefinition = "decimal(19,2) default 0.00")
	@ApiModelProperty("Valor pago caso seje me dinheiro")
	private BigDecimal valorPago;
	@Column(columnDefinition = "decimal(19,2) default 0.00")
	private BigDecimal desconto;
	@Column(columnDefinition = "decimal(19,2) default 0.00")
	private BigDecimal total;

	@ManyToOne
	@JoinColumn(name = "pedido")
	private Pedido pedido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public BigDecimal getTroco() {
		return troco;
	}

	public void setTroco(BigDecimal troco) {
		this.troco = troco;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

}
