package com.williamartins.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.williamartins.cursomc.domain.Produto;

/*Interface de operações acessar dados pode ser - busca salvar, deletar etc .. uma especie de DAO.
 * Busca tudo que pedir direto do banco.*/

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
