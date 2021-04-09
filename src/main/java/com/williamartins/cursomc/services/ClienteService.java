package com.williamartins.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.williamartins.cursomc.domain.Cliente;
import com.williamartins.cursomc.dto.ClienteDTO;
import com.williamartins.cursomc.repositories.ClienteRepository;
import com.williamartins.cursomc.services.exception.DataIntegrityException;

import javassist.tools.rmi.ObjectNotFoundException;

/*BEM VINDO AO PACOTE SERVICE "DAO" ATRAVES DA REQUISIÇÃO DO USUARIO, ELE FAZ AS AÇÕES DENTRO DO BANCO DE DADOS.
 * PERSISTENCIA DE DADOS COM O BANCO DE DADOS.*/

/*serviço que oferece operação de consulta de categorias com axulio do repository.  */
@Service
public class ClienteService {

//instanciado o repository ClienteRepository usando a anotação @Autowired
	@Autowired
	private ClienteRepository repo; 

	/*Operação para fazer uma busca no banco de dados atraves do ID da Cliente e 
	 mostra uma execessão caso o ID não existir..
	 ObjectNotFoundException = lança um em todo de execessão casoo id não exista*/
	public Cliente find(Integer id) throws ObjectNotFoundException { 
		 Optional<Cliente> obj = repo.findById(id); /*findById = faz operação de busca de dados, com base no ID.*/
		   return obj.orElseThrow(() -> new ObjectNotFoundException( "Objeto não encontrado! Id: " + id + ", Tipo: " 
		        + Cliente.class.getName())); 
		}
	
	/*Metodo para inserir uma categoria usando o Repository
	 * tem objetivo de retorna o repo e salvar*/
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	
	
	/*Metodo para atualizar categoria usando o Repository
	 * e ataulizar clinte apartir do banco de dados.*/
	public Cliente update(Cliente obj) throws ObjectNotFoundException  {
		Cliente newObj = find(obj.getId());//find busca o obj no banco, caso NÃO ache, ele da uma exceção/usando o find do buscar por ID
		updateData(newObj, obj);//metodo auxiliar/ou seja att os dados desse novo objteo com base como veio como arguemtno
		return repo.save(newObj);// e da um save no novo objeto atualizado
	}
	/*Metodo "Atualizar" q busca todos os dados no BD com "newObj" existente 
	 * se caso for atualizar novos valores fornecido, da um update nos dados para o BD com "obj" */
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	

	/*Metodo para Deletar para categoria usando o Repository*/
	public void delete(Integer id) throws ObjectNotFoundException {
		find(id);//find busca o obj no banco, caso NÃO ache, ele da uma exceção/usando o find do buscar por ID
		
		try {
		 repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacioandas!");//importando execeção personalizada "minha"	
		}
	}
	
	
	/*Metodo para Listar categoria usando o Repository*/
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	
	/*Metodo para Paginar categoria usando o Repository
	 * qunatidade de paginas, de linhas e qual atruibuto ordenar "ID ou nome etc..."*/
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), 
				orderBy);
		return repo.findAll(pageRequest);
	}
	
	
	
	/*Metodo auxiliar que instancia uma Cliente apartir de um DTO,
	 * ele não instancia do banco de dados, intacia um cliente colocando nulo onde que tiver que colocar*/
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	
	
}
