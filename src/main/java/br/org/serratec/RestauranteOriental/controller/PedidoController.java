package br.org.serratec.RestauranteOriental.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.RestauranteOriental.dto.PedidoDto;
import br.org.serratec.RestauranteOriental.service.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("pedidos")
public class PedidoController {
	
	@Autowired
	public PedidoService service;
	
	@GetMapping()
	public ResponseEntity<List<PedidoDto>> listarTodosPedidos(){
		return ResponseEntity.ok(service.listarPedido());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PedidoDto> lsitarPorId(@PathVariable Long id){
		Optional<PedidoDto> pedido = service.listarPorId(id);
		
		if(pedido.isPresent()) {
			return ResponseEntity.ok(pedido.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/cliente")
		public ResponseEntity<List<PedidoDto>> listarPorCliente(@RequestBody String cliente){
			return ResponseEntity.ok(service.listarCliente(cliente));
		}
	
	@GetMapping("/valor-crescente")
	public ResponseEntity<List<PedidoDto>> listarPorValorCrescente(){
		return ResponseEntity.ok(service.obterValorCrescente());
	}
	
	@GetMapping("/menor-valor")
	public ResponseEntity<List<PedidoDto>> listarPorMenorValor(@RequestBody String valor){
		return ResponseEntity.ok(service.obterMenorValor(Double.valueOf(valor)));
	}
	
	
	@PostMapping
	public ResponseEntity<PedidoDto> cadastrarPedido(@Valid @RequestBody PedidoDto pedido){
		return new ResponseEntity<PedidoDto>(service.cadastrarPedido(pedido), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PedidoDto> alterarPedido(@PathVariable Long id, @RequestBody PedidoDto pedidoAlterado){
		Optional<PedidoDto> pedido = service.alterarPedido(id, pedidoAlterado);
		
		if(pedido.isPresent()) {
			return ResponseEntity.ok(pedido.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	
	public ResponseEntity<Void> excluirPedido(@PathVariable Long id){
		if(!service.excluirPedido(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

}
