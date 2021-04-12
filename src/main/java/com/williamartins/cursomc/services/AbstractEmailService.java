package com.williamartins.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.williamartins.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;//passando o email padrao
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	/*Protected por ser acessivel por subClasses
	 *Gerando um "SimpleEmailMessage" apartir de um pedido */
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();//instanciando SimpleEmailMessage, vazio
		sm.setTo(obj.getCliente().getEmail());//definindo pra quem sera a MSG.
		sm.setFrom(sender);//quem vai ser o remetente, sera o email padrão da aplicação
		sm.setSubject("Pedido confirmado! Código: " + obj.getId());//confirmando o e-mail
		sm.setSentDate(new Date(System.currentTimeMillis()));//definindo a data e hora do email, atraves do meu servidor.
		sm.setText(obj.toString());//pegando o corpo do email, dentro do metodo Pedido
		return sm;
	}
}
