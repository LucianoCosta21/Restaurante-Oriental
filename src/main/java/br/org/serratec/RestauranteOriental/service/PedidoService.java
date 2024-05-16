package br.org.serratec.RestauranteOriental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.RestauranteOriental.dto.PedidoDto;
import br.org.serratec.RestauranteOriental.model.Pedido;
import br.org.serratec.RestauranteOriental.repository.PedidoRopository;
@Service
public class PedidoService {

	@Autowired
	public PedidoRopository pedidoRepositorio;
	
	public List<PedidoDto> listarPedido(){
		return pedidoRepositorio.findAll().stream()
				.map(p -> new PedidoDto(p.getId(), p.getCliente(), p.getPrato(), p.getValor())).toList();
	}
	
	public Optional<PedidoDto> listarPorId(Long id) {
		Optional<Pedido> pedido = pedidoRepositorio.findById(id);
		if(pedido.isPresent()) {
			return Optional.of(pedido.get().toDto());
		}
		return Optional.empty();
	}
	
	public PedidoDto cadastrarPedido(PedidoDto pedido) {
		Pedido pedidoEntity = pedidoRepositorio.save(pedido.toEntity());
		return pedidoEntity.toDto();
		
	}
	
	public Optional<PedidoDto> alterarPedido(Long id,PedidoDto pedido) {
		Pedido pedidoEntity = pedido.toEntity();
		
		if(pedidoRepositorio.existsById(id)) {
			pedidoEntity.setId(id);
			pedidoRepositorio.save(pedidoEntity);
			return Optional.of(pedidoEntity.toDto());
		}
		return Optional.empty();
	}
	
	public boolean excluirPedido(Long id) {
		if(!pedidoRepositorio.existsById(id)) {
			return false;
		}
		pedidoRepositorio.deleteById(id);
		return true;
	}
	
	public List<PedidoDto> listarCliente(String cliente) {
		return pedidoRepositorio.findByClienteContainingIgnoreCase(cliente).stream()
				.map(c -> new PedidoDto(c.getId(), c.getCliente(),c.getPrato(),c.getValor())).toList();
	}
	public List<PedidoDto> obterValorCrescente(){
	 return pedidoRepositorio.findAllByOrderByValorAsc().stream()
			 .map(vc -> new PedidoDto(vc.getId(), vc.getCliente(),vc.getPrato(), vc.getValor())).toList();
	}
	public List<PedidoDto> obterMenorValor(double valor){
		return pedidoRepositorio.findByValorLessThan(valor).stream()
				.map(mv -> new PedidoDto(mv.getId(), mv.getCliente(),mv.getPrato(), mv.getValor())).toList();
	}
}
