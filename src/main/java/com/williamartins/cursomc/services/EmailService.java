package com.williamartins.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.williamartins.cursomc.domain.Pedido;

/*interface de serviço de email/*/
public interface EmailService {
	
	//duas operaçoes basicas
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
