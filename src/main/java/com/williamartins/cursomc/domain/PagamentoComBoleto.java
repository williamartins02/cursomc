package com.williamartins.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.williamartins.cursomc.domain.enums.EstadoPagamento;

/*pagamentoComBoleto extends class Pagamento, 
 * esse class é uma subclasse, Herdando atributos da SuperClass "Pagamentos".*/
@Entity
@JsonTypeName("pagamentoComBoleto")//Subclasse que define um valor adicional.
public class PagamentoComBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyy") /*Formatando a data e hora*/
	private Date dataVencimento;
	
	@JsonFormat(pattern = "dd/MM/yyy") /*Formatando a data e hora*/
	private Date dataPagamento;

	public PagamentoComBoleto() {}

	/*PagamentoComBoleto por ser uma subClasse, cria o construtor 
	 * Generante Construtors from SuperClass*/
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento ) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	
	
	
}
