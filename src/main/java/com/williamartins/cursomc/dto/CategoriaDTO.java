package com.williamartins.cursomc.dto;

import java.io.Serializable;

import com.williamartins.cursomc.domain.Categoria;

/*Metodo que defini os dados que quero trafegar, quando for fazer operaçoes basica de categoria
 * DTO <=> Objeto de Transferência de Dados, é um padrão de projeto de software usado para transferir dados
 *  entre subsistemas de um software. é um objeto que vai ter somente os dados que eu preciso para algumas 
 *  operações no sistemas
 *  */

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public CategoriaDTO() {}
	
	//Construtor que recebe as entidade de dominio, sera responsavale de instaciar meu DTO atraves do objeto Categoria
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		nome = obj.getNome();
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
