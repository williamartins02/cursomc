package com.williamartins.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.williamartins.cursomc.domain.PagamentoComBoleto;
/*Criando um metodo pra acrescentar uma data de pagamento para vencer.*/
@Service
public class BoletoService {
    
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}
}
