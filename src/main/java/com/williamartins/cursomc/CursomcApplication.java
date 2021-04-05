package com.williamartins.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.williamartins.cursomc.domain.Categoria;
import com.williamartins.cursomc.repositories.CategoriaRepository;

/*Classe prncipal para executar o projeto.*/
@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	/*metodo auxiliar que permiti executar alguma ação quando a aplicação é iniciada
	 * implements CommandLineRunner  
	 * inserindo categorias no banco de dados.*/
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));	
	}
}
