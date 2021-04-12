package com.williamartins.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.williamartins.cursomc.services.DBService;
import com.williamartins.cursomc.services.EmailService;
import com.williamartins.cursomc.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	// injetando a class de teste DBService
	@Autowired
	private DBService dbService;

	// metodo responsavel em intanciar meu profile de teste
	@Value("${spring.jpa.hibernate.ddl-auto}") // pegando o valor da chave
	private String strategy; // armazenando o valor da chave

	@Bean
	public boolean instantiateDatabase() throws ParseException {

		/*
		 * if para não replicar o banco de dados toda vezes que iniciar a aplicação, ao
		 * iniciar o aplicativo, o banco zera controle pra quando que eu devo instanciar
		 * "Recriar" a base de dado, no caso NÂO
		 */
		if (!"create".equals(strategy)) {
			return false;
		}

		dbService.instantiateTestDatabase();
		return true;
	}

	/*
	 * Criando um Bean para retorno um email SmtpEmailService, par funcionar o envio
	 * precisa desse Bean
	 */
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

}
