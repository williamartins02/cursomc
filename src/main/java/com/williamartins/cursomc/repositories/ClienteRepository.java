package com.williamartins.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.williamartins.cursomc.domain.Cliente;

/*Interface de operações acessar dados pode ser - busca salvar, deletar etc .. uma especie de DAO.
 * Busca tudo que pedir direto do banco.*/

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	/*1º Fazendo uma busca por email*/
	/*2º A anotação  "@Transactional(readOnly = true)" não necessita ser envolvida com uma transação de banco de dados
    faz que que fique mais rapida e diminiu o loc
    no gereciamneto de transação no banco de dados.*/
    @Transactional(readOnly = true)
     Cliente findByEmail(String email);

}
