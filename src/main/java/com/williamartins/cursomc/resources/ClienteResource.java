package com.williamartins.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.williamartins.cursomc.domain.Cliente;
import com.williamartins.cursomc.dto.ClienteDTO;
import com.williamartins.cursomc.services.ClienteService;

import javassist.tools.rmi.ObjectNotFoundException;

/*BEM VINDO AO PACOTE RESOURCE "CONTROLLER" VALIDA AS INFORMAÇÕES ENCIMA DA REQUISIÇÃO DO USUARIOS
 * PRÉ VALIDAÇÃO E PRÉ ANOTAÇÕES PARA O CONTROLLER..*/
		
/*Pacote Resource tem a funcionalidade de um Controller onde valida as requisições do usuario*/

/*mapeando Cliente e o ID
 * END POINT*/
@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired /*instacia automaticamente esse objeto..*/
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) 
			throws ObjectNotFoundException { 
		
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);	
	}
	
	/*Metodo para Atualização*/
	@RequestMapping(value="/{id}", method= RequestMethod.PUT)/*PUT normalmente é usado para atualizar algo*/
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) throws ObjectNotFoundException{//Passando a categoria e o id que sera feito o update.
		Cliente obj = service.fromDTO(objDto);//pegando o metodo DTO pra trazer objeto.
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	/*Metodo para Deletar*/
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void>delte(@PathVariable Integer id) throws ObjectNotFoundException{
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	/*Metodo Listar todas categoria do banco e converter para DTO */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<Cliente> list = service.findAll();//buscando a lista de categoria do banco
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());//convertendo uma lista para um lista
		return ResponseEntity.ok().body(listDto);
	}
	
	/*Metodo Paginação */
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	
	
}
