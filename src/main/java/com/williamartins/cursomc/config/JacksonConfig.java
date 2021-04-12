package com.williamartins.cursomc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.williamartins.cursomc.domain.PagamentoComBoleto;
import com.williamartins.cursomc.domain.PagamentoComCartao;

/*Codigo padrao
 *  Criando uma classe de configuração com um método @Bean para registrar as subclasses "PagamentoComCartao 
 *  e PagamentoComBoleto.":
 *  */
@Configuration/*Anotação <=> classe de configuração, uma classe que vai ter algum metodo, que vai esta disponivel 
no sitema e sera cconfigurada no inicio da excução da aplicação */
public class JacksonConfig {
// https://stackoverflow.com/questions/41452598/overcome-can-not-construct-instance-ofinterfaceclass-without-hinting-the-pare
	
	//Metodo Jacson que instacia um pagamento conforme determinar "Cartao o boleto".
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PagamentoComCartao.class);//Subclasse registrada
				objectMapper.registerSubtypes(PagamentoComBoleto.class);//Subclasse registrada
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}