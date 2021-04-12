package com.williamartins.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.williamartins.cursomc.domain.Produto;
import com.williamartins.cursomc.dto.ProdutoDTO;
import com.williamartins.cursomc.resources.utils.URL;
import com.williamartins.cursomc.services.ProdutoService;

import javassist.tools.rmi.ObjectNotFoundException;

/*BEM VINDO AO PACOTE RESOURCE "CONTROLLER" VALIDA AS INFORMAÇÕES ENCIMA DA REQUISIÇÃO DO USUARIOS
 * PRÉ VALIDAÇÃO E PRÉ ANOTAÇÕES PARA O CONTROLLER..*/

/*mapeando categoria e o ID, fazendo cosulta e trazendo por ID
 * END POINT*/
@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired /*instacia automaticamente esse objeto..*/
	private ProdutoService service;
	
	//EndPoint para criar busca por ID
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) /*@PathVariable = pra mostra que a consulta vem do ID*/
			throws ObjectNotFoundException { 
		Produto obj = service.find(id);/*indo no services pedindo para pegar a categoria que contem aquele ID*/
		return ResponseEntity.ok().body(obj);	
	}
	
	/*Metodo Paginação que faz busca por Produto 
	 *  O sistema informa os nomes de todas Produto ordenadamente, dados ja gravado no banco
	 *  esse metodo faz um GET*/
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			//parametros que passa na URL
			@RequestParam(value="nome", defaultValue="") String nome, 
			@RequestParam(value="categorias", defaultValue="") String categorias, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		
		String nomeDecoded = URL.decodeParam(nome);//usando o metodo decode.
		
		List<Integer> ids = URL.decodeIntList(categorias);//chamando a lista de ids da categoria.
		
		Page<Produto> list = service.search(nomeDecoded,ids,page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
}
