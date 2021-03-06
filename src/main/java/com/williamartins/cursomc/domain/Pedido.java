package com.williamartins.cursomc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;


 @Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyy HH:mm") /*Formatando a data e hora*/
	private Date instante;
	
	/* Mapeando 1 pra 1, ,*/
	@OneToOne(cascade=CascadeType.ALL, mappedBy="pedido")/*essa anotação é necessario pra Ñ da erro de entidade 
	transiente na hora de salvar pedido e pagamento, e esta sendo mapeado pelo pedido.*/
	private Pagamento pagamento;
	
	
	@ManyToOne /*Muitos pedido tem 1 cliente*/
	@JoinColumn(name="Cliente_id")/*Criiando a tabela auxiliar para transição*/
	private Cliente cliente;
	
	@ManyToOne/*Muitos pedido tem 1 cliente*/
	@JoinColumn(name="endereco_de_entrega_id")/*criando a tabela auxiliar*/
	private Endereco enderecoDeEntrega;
	
	/*Essa classe tera um conjunto de ItemPedido associado a ele.*/
	@OneToMany(mappedBy = "id.pedido")//fazendo associação inversa/mostrando porque foi mapeado
	 private Set<ItemPedido> itens = new HashSet<>();
	 
	public Pedido(){}//construtor vazio

	/*Construtor com informações*/
	public Pedido(Integer id, Date instante,  Cliente cliente, Endereco enderecoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}
	
	/*Metodo calcular ValorTotal dos pedido*/
  public double getValorTotal() {
		double soma = 0.0;
		for (ItemPedido ip : itens) {//Uum For para percorre uma lista  e somar o total dos pedidos
			soma = soma + ip.getSubTotal();
		}
		return soma;
  }	
	
	

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	/*crinado o totsString "Pedido" pra auxiliar na impressão.*/
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));//formatando dinheiro por local
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");//formatado data e horas local.
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido número: ");
		builder.append(getId());
		builder.append(", Data do pedido: ");
		builder.append(sdf.format(getInstante()));
		builder.append(", Cliente: ");
		builder.append(getCliente().getNome());
		builder.append(", Situação do pagamento: ");
		builder.append(getPagamento().getEstado().getDescricao());
		builder.append("\nDetalhes da compra: \n");
		//for pra percorrer os pedido solicitado
		for(ItemPedido intemPd : getItens()) {
			builder.append(intemPd.toString());	
		}
		builder.append("Valor total: ");
		builder.append(nf.format(getValorTotal()));
		return builder.toString();
	}


	
}
