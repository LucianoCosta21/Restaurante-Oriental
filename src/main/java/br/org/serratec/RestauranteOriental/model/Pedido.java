package br.org.serratec.RestauranteOriental.model;

import br.org.serratec.RestauranteOriental.dto.PedidoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cliente;
	private String prato;
	private double valor;
	
	
	public Pedido() {
		
	}
	
	

	public Pedido(Long id, String cliente, String prato, double valor) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.prato = prato;
		this.valor = valor;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getPrato() {
		return prato;
	}

	public void setPrato(String prato) {
		this.prato = prato;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public PedidoDto toDto() {
		return new PedidoDto(this.id, this.cliente, this.prato, this.valor);
	}

}
