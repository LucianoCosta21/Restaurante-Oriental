package br.org.serratec.RestauranteOriental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.RestauranteOriental.model.Pedido;


public interface PedidoRopository extends JpaRepository<Pedido, Long>{
	
	public List<Pedido> findByClienteContainingIgnoreCase(String cliente);
	public List<Pedido> findAllByOrderByValorAsc();
	public List<Pedido> findByValorLessThan(double valor);
}
