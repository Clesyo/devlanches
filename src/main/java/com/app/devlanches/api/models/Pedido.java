package com.app.devlanches.api.models;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.app.devlanches.api.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull(message = "Status do pedido não definido.")
	@ApiModelProperty("Status do pedido: PENDENTE, ATENDIMENTO, FINALIZADO, CANCELADO, PAGO")
	private StatusPedido status;
	@Column(nullable = false)
	@NotNull(message = "Total do pedido não calculado.")
	private BigDecimal total;
	@Column(nullable = false)
	private boolean pago;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@JsonIgnore
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itemPedidos;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pedido")
	private List<Pagamento> pagamentos;

	public Pedido() {
	}

	public Pedido(StatusPedido status, BigDecimal total) {
		super();
		this.status = status;
		this.total = total;
	}

	public Pedido(StatusPedido status, BigDecimal total, Cliente cliente) {
		super();
		this.status = status;
		this.total = total;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItemPedidos() {
		return itemPedidos;
	}

	public void setItemPedidos(List<ItemPedido> itemPedidos) {
		this.itemPedidos = itemPedidos;
	}

}
