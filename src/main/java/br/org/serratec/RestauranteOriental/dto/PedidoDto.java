package br.org.serratec.RestauranteOriental.dto;

import br.org.serratec.RestauranteOriental.model.Pedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PedidoDto(Long id,
		@NotBlank(message = "Informe o nome do cliente")
		String cliente,
		@NotBlank(message = "Informe o prato que deseja")
		String prato, 
		@NotNull()
		@Positive(message = " valor tem que ser maior que 0")
		double valor) {

	public Pedido toEntity() {
		return new Pedido(this.id, this.cliente,this.prato, this.valor);
	}
}
