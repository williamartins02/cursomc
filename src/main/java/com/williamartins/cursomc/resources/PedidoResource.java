package com.williamartins.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.williamartins.cursomc.domain.Pedido;
import com.williamartins.cursomc.services.PedidoService;

import javassist.tools.rmi.ObjectNotFoundException;

/*BEM VINDO AO PACOTE RESOURCE "CONTROLLER" VALIDA AS INFORMAÇÕES ENCIMA DA REQUISIÇÃO DO USUARIOS
 * PRÉ VALIDAÇÃO E PRÉ ANOTAÇÕES PARA O CONTROLLER..*/

/*mapeando categoria e o ID, fazendo cosulta e trazendo por ID
 * END POINT*/
@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired /*instacia automaticamente esse objeto..*/
	private PedidoService service;
	//EndPoint para criar busca por ID
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) /*@PathVariable = pra mostra que a consulta vem do ID*/
			throws ObjectNotFoundException { 
		
		Pedido obj = service.find(id);/*indo no services pedindo para pegar a categoria que contem aquele ID*/
		return ResponseEntity.ok().body(obj);	
	}
	
	/*Metodo instanciar um novo pedido a ser inserido*/
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) throws ObjectNotFoundException{
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
