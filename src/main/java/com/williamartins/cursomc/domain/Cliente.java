package com.williamartins.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.williamartins.cursomc.domain.enums.Perfil;
import com.williamartins.cursomc.domain.enums.TipoCliente;

@Entity
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@Column(unique=true)
	private String email;
	private String cpfOuCnpj;
	private Integer tipo;
	
	@JsonIgnore
	private String senha;

	@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)//apagar registro em cascata "cascade = CascadeType.ALL"
	private List<Endereco> enderecos = new ArrayList<>();
	
	/* 1 º criando um conjunto de String evitando para ter repetição nessa coleção
	 * representando o telefone com um conjunto de String sem repetição*/
	/*2º A anotação @ElementCollection  é usada para mapear nossa entidade para uma lista de classes que não são 
	 * entidades. serão armazenadas em uma tabela separada*/
	@ElementCollection
	@CollectionTable(name="TELEFONE")/*criando a tabela separada para armazenamento do telefone.*/
	private Set<String> telefones = new HashSet<>();
	
	// atributo correspondente aos perfis do usuário a serem armazenados na base de dados
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")/*criando a tabela separada para armazenamento do telefone.*/
	private Set<Integer> perfis = new HashSet<>();
	
	/*QNDO HOUVER 1-PARA-MUITOS COM LISTA, USAR O @OneToMany*/
	@JsonIgnore /*Pedido dos cliente não sera serealizados*/
	@OneToMany(mappedBy="cliente")/* O @OneToMany é o oposto do que o @ManyToOne, ou seja é o 1-para-muitos.*/
	private List<Pedido> pedidos = new ArrayList<>(); /*Qndo for list NÃO colocar no construtor.*/
	
	/*Dentro do construtor vazio criar e incluir perfil padrão (CLIENTE) na instanciação de Cliente
	 * todos tem o perfil de clientes, ate definir oque cada um é*/
	public Cliente() {
		addPerfil(Perfil.CLIETE);
	}

	/*Construtor coma argumento NÂO é necessario, é feito para facilitar instacianção de objeto em uma linha só. 
	 * evitando processo de da setemail, setnome etc..*/
	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = (tipo==null) ? null : tipo.getCod();//operador ternario para atribuir o null ou o codigo
		this.senha = senha;
		addPerfil(Perfil.CLIETE);
	}

	
	

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	//Metodo para convertendo o numero inteiro para o PERFIL equivalente e retorna o perfil dos clientes.
	public Set<Perfil> getPerfis(){
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	//Metodo para adiconar os PERFIL..
	 public void addPerfil(Perfil perfil) {
		 perfis.add(perfil.getCod());
	 }

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
