package com.williamartins.cursomc.config;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;



/*Configuração para PERMISSÔES pra definir acessos via url */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;
	
	/* Definindo atraves do verto quais os caminhos publicos por padrão, estara liberados */
	private static final String[] PUBLIC_MATCHERS = { 
			"/h2-console/**"
			};
	
	/* Definindo atraves do verto caminhos só para recuperar dados, tirando a permissão de 
	 * DELET, INSERT, UPDATE." */
	private static final String[] PUBLIC_MATCHERS_GET = { 
			"/produtos/**", 
			"/categorias/**",
			"/clientes/**" 
			};

	// todos caminhos que tiver no vetor, permitir o acesso.
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		//pegando Profile ativos no meu projeto, pára acessar o "H2" quando tiver no teste
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();//comando para liberar o acesso.
		}
		
		//configurações 
		http.cors().and().csrf().disable(); 
		http.authorizeRequests()
		    .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.anyRequest().authenticated();//para todos resto exige autenticação
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//assegurando que o Bacnkend NÂO vai configurara sessao de usuario
	}
	
	/*Configurações basicos pra permitir acesso pelo cors*/
       @Bean
       CorsConfigurationSource corsConfigurationSource() {
    	   final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	   source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return (CorsConfigurationSource) source;
    	   
       }
       
       /*Adicionando senha ao cliente, e criptografando */
       @Bean
       public BCryptPasswordEncoder bCryptPasswordEncoder() {
    	   return new BCryptPasswordEncoder();
       }
       
       
       
}
