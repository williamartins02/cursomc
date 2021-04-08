package com.williamartins.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.williamartins.cursomc.domain.Pedido;
import com.williamartins.cursomc.repositories.PedidoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

/*EndPoint Service*/
/*serviço que oferece operação de consulta de categorias com auxulio do repository.  */
@Service
public class PedidoService {

//instanciado o repository PedidoRepository usando a anotação @Autowired
	@Autowired
	private PedidoRepository repo; 

	/*Operação para fazer uma busca no banco de dados atraves do ID da Pedido e 
	 mostra uma execessão caso o ID não existir..
	 ObjectNotFoundException = lança um em todo de execessão casoo id não exista*/
	public Pedido find(Integer id) 
			 throws ObjectNotFoundException { 
		
		 Optional<Pedido> obj = repo.findById(id); /*findById = faz operação de busca de dados, com base no ID.*/
		return obj.orElseThrow(() -> new ObjectNotFoundException( "Objeto não encontrado! Id: " + id + ", Tipo: " 
		     + Pedido.class.getName())); 
		}
}
