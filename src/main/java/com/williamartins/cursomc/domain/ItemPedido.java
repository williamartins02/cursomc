package com.williamartins.cursomc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;


 @Entity
public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@JsonIgnore /*ignorando pra não precisar fazer serealização por ser chave composta*/
	/*Dizendo pra essa classe que ela tera como id um objeto do tipo ItemPedidoPk */
	@EmbeddedId /*Dizendo que o (id) é um ID embutido no auxiliar ItemPedidoPK*/
	private ItemPedidoPK id = new ItemPedidoPK();
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	public ItemPedido(){
		
	}

	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	/*Metodo para calcular subtotal  dos pedidos*/
	public double getSubTotal() {
		return (preco - desconto) * quantidade;
	}
	
	/*CRiando os set do Produto e Pedido.
	 * para ter acesso a esses atributos fora da classe ItemPedido*/
	@JsonIgnore /*tudo que começa com "get" tem que ignorar para não fazer serialização ciclica*/
	public Pedido getPedido() {
		return id.getPedido();
	}
	/*Metodo para criar as associação de pedido/isntancia novo intem pedido e assoaciar esse novo intem 
	de pedido*/
	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}
	public Produto getProduto() {
		return id.getProduto();
	}
	/*Metodo para criar as associação dos produtos/isntancia novo intem pedido e assoaciar esse novo intem 
	de pedido, tem que implemntar o setProduto*/
	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}
	
	
	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
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
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/*crinado o totsString "itensPedido" pra auxiliar na impressão.*/
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));//formatando dinheiro por local
		StringBuilder builder = new StringBuilder();
		builder.append(getProduto().getNome());
		builder.append(", Quantidade: ");
		builder.append(getQuantidade());
		builder.append(", Preço unitário: ");
		builder.append(nf.format(getPreco()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}
	
	
}
