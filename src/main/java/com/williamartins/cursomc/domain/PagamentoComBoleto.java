package com.williamartins.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.williamartins.cursomc.domain.enums.EstadoPagamento;

/*pagamentoComBoleto extends class Pagamento, 
 * esse class é uma subclasse, Herdando atributos da SuperClass "Pagamentos".*/
@Entity
public class PagamentoComBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	private Date dataVencimento;
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
