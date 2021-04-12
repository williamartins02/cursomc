package com.williamartins.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/*extendendo o MailSender para class "SmtpEmailService"
 * instanciado todos os dados de SMTP do "application-dev-properties" pra essa classe, sera um objeto pronto para 
 * enviar email com os dados informado.*/
public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;//framework intanciando os dados do "application-dev-properties", todas informações de envio de email
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Enviando email...");
		mailSender.send(msg);
		LOG.info("Email enviado");
	}
  
	
}
