package com.williamartins.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.williamartins.cursomc.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	
	//injetando a class de teste DBService
	@Autowired 
	private DBService dbService;
	
	//metodo responsavel em intanciar meu profile de teste
	@Bean
	public boolean instantiateDatabase() throws ParseException{
		dbService.instantiateTestDatabase();
		return true;
	}

	
}
