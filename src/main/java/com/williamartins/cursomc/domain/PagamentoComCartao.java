package com.williamartins.cursomc.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.williamartins.cursomc.domain.enums.EstadoPagamento;


/*pagamentoComCartao extends class Pagamento, 
esse class é uma subclasse, Herdando atributos da SuperClass "Pagamentos"
*/
@Entity
@JsonTypeName("pagamentoComCartao")//Subclasse que define um valor adicional.
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	private Integer NumeroDeParcelas;
	
	public PagamentoComCartao() {}
	
	/*PagamentoComCartao por ser uma subClasse, cria o construtor 
	 * Generante Construtors from SuperClass*/
	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas ) {
		super(id, estado, pedido);
		this.NumeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return NumeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		NumeroDeParcelas = numeroDeParcelas;
	}
	
	
}
