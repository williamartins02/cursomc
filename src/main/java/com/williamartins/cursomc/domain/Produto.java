package com.williamartins.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity /* <= Dizendo que a class é um dominio do jpa*/
public class Produto  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)/*<= Gerando auto increment no banco para o ID*/
	private Integer id;
	private String nome;
	private Double preco;
	
	/*mapeando o relacioanamentos das tabela Produto/Categoria. criando uma tabela auxiliar de muitos para muitos entre as tabelas.*/
	@JsonBackReference   /*Ele entende q do outro lado ja foi feito a associação do obj, ele não busca mais pra não da conflito.*/
	@ManyToMany  /*<= Qndo houver duas ou mais lista precisa usar o anotação @ManyToMany*/
	@JoinTable( name = "PRODUTO_CATEGORIA",/*<= "JoinTable"Essa anotação defini uma Tabela "auxiliar" q vai fazer "Ligação" de  Muitos pra muitos no bd relacional */
	   joinColumns = @JoinColumn(name= "produto_id"),/*<=> definindo o nome do campo da tabela correspondente ao codigo do produto ou seja "Chave estrangeira."   */
	   inverseJoinColumns = @JoinColumn(name = "categoria_id")/* inverseJoinColumns <=> Nome da outra chave estrangeira que vai referenciar a categoria. */
	 )
	private List<Categoria> categorias = new ArrayList<>();/*Criando associação da tabela "Catetgoria dentro da tabela 
	produto", para relacionamento.*/
	
	
	public Produto() {	
	}
	public Produto(Integer id, String nome, Double preco) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
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

	/*Criando Hascode só pra o ID na tabela "Produto" para comparar dois objeto pelo conteudo não pelo ponteiro.*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public List<Categoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	

}
