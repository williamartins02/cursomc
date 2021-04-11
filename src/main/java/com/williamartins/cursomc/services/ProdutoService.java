package com.williamartins.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.williamartins.cursomc.domain.Categoria;
import com.williamartins.cursomc.domain.Produto;
import com.williamartins.cursomc.repositories.CategoriaRepository;
import com.williamartins.cursomc.repositories.ProdutoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

/*EndPoint Service*/
/*serviço que oferece operação de consulta de categorias com auxulio do repository.  */
@Service
public class ProdutoService {

//instanciado o repository ProdutoRepository usando a anotação @Autowired
	@Autowired
	private ProdutoRepository repo; 
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	/*Operação para fazer uma busca no banco de dados atraves do ID da Produto e 
	 mostra uma execessão caso o ID não existir..
	 ObjectNotFoundException = lança um em todo de execessão casoo id não exista*/
	public Produto find(Integer id) 
			 throws ObjectNotFoundException { 
		 Optional<Produto> obj = repo.findById(id); /*findById = faz operação de busca de dados, com base no ID.*/
		return obj.orElseThrow(() -> new ObjectNotFoundException( "Objeto não encontrado! Id: " + id + ", Tipo: " 
		     + Produto.class.getName())); 
		}
	
	//Operação para fazer busca de produto paginada.
	public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer linesPerPage,String orderBy, 
			String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.search(nome, categorias, pageRequest);
	}
	
	
	
	
	
}
