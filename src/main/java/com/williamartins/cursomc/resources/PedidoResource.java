package com.williamartins.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.williamartins.cursomc.domain.Categoria;
import com.williamartins.cursomc.domain.Pedido;
import com.williamartins.cursomc.services.PedidoService;

import javassist.tools.rmi.ObjectNotFoundException;

/*mapeando categoria e o ID, fazendo cosulta e trazendo por ID
 * END POINT*/
@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired /*instacia automaticamente esse objeto..*/
	private PedidoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) /*@PathVariable = pra mostra que a consulta vem do ID*/
			throws ObjectNotFoundException { 
		
		Pedido obj = service.find(id);/*indo no services pedindo para pegar a categoria que contem aquele ID*/
		return ResponseEntity.ok().body(obj);	
	}
}
