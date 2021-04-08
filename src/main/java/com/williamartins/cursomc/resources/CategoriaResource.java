package com.williamartins.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.williamartins.cursomc.domain.Categoria;
import com.williamartins.cursomc.services.CategoriaService;

import javassist.tools.rmi.ObjectNotFoundException;

/*mapeando categoria e o ID, fazendo cosulta e trazendo por ID
 * END POINT*/
@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired /*instacia automaticamente esse objeto..*/
	private CategoriaService service;
	
	/*Metodo para busca o ID de uma categoria .
	 * 
	 * API REST pegar id*/
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) /*@PathVariable = pra mostra que a consulta vem do ID*/
			throws ObjectNotFoundException { 
		Categoria obj = service.buscar(id);/*indo no services pedindo para pegar a categoria que contem aquele ID*/
		return ResponseEntity.ok().body(obj);	
	}
	
	/*Metodo para receber uma categoria formato json, e inserir no banco de dados uma nova categoria. .
	 * 
	 * API REST usando codigo HTTP ResourceCreat201 com URI = pra pegar o novo recurso que foi inserido*/
	@RequestMapping(method = RequestMethod.POST)//mappeando dentro da class Categoria.
	public ResponseEntity<Void> insert(@RequestBody Categoria obj){//@RequestBody faz que o json seja convertido para o obj java auto
		obj = service.insert(obj);/*chamando um serviço que inseri essa nova categoria no banco de dados.*/
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()//
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();/*Gerando a resposta do codigo 201 created*/
	}
	
	
	
	
	
	
}
