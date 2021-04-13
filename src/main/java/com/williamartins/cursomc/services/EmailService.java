package com.williamartins.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.williamartins.cursomc.domain.Pedido;

/*interface de serviço de email/*/
public interface EmailService {
	
	//duas operaçoes basicas, para enviar email Texto plano
	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);
	
	//enviar email HTML
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	void sendHtmlEmail(MimeMessage msg);

}
