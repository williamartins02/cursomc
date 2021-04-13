package com.williamartins.cursomc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.williamartins.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;//passando o email padrao
	
	@Autowired
	private TemplateEngine templateEngine;//injetando para poder usar o templante
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	//metodo de envio de texto plano
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	/*Protected por ser acessivel por subClasses
	 *Gerando um "SimpleEmailMessage" apartir de um pedido, para enviar um email do dados do pedidos */
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();//instanciando SimpleEmailMessage, vazio
		sm.setTo(obj.getCliente().getEmail());//definindo pra quem sera a MSG.
		sm.setFrom(sender);//quem vai ser o remetente, sera o email padrão da aplicação
		sm.setSubject("Pedido confirmado! Código: " + obj.getId());//confirmando o e-mail
		sm.setSentDate(new Date(System.currentTimeMillis()));//definindo a data e hora do email, atraves do meu servidor.
		sm.setText(obj.toString());//pegando o corpo do email, dentro do metodo Pedido
		return sm;
	}
	/*  incluindo o seguinte método, que será responsável por retornar o HTML preenchido com 
        os dados de um pedido, a partir do template Thymeleaf*/
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();//intanciando objetvo para acessar o templante "ConfrimacaoPedido"
		context.setVariable("pedido", obj);//comando que defini para meu templante utilizar obj com nome de pedido
		return templateEngine.process("email/confirmacaoPedido", context);//processando o caminho do templante
	}
	
	//metodo de envio de email com templante
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		try {//mandando o email normal do HTML
			MimeMessage mm = prepareMimeMessageFromPedido(obj);
			sendHtmlEmail(mm);
			
		}catch(MessagingException e) {//se der errado manda pelo metodo email plano
			sendOrderConfirmationEmail(obj);
		}
	}
	
	protected MimeMessage  prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
		 MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		 MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		 mmh.setTo(obj.getCliente().getEmail());//enviando o email para o destinatario
		 mmh.setFrom(sender);//remetente do email
		 mmh.setSubject("Pedido confirmado! Código: "+ obj.getId());//conformacao do email
		 mmh.setSentDate(new Date(System.currentTimeMillis()));//hora do email
		 mmh.setText(htmlFromTemplatePedido(obj),true);//corpo do email
		 
		 
		return mimeMessage;
		
	}
}
