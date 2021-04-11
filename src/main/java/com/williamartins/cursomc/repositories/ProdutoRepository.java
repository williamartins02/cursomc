package com.williamartins.cursomc.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.williamartins.cursomc.domain.Categoria;
import com.williamartins.cursomc.domain.Produto;

/*Interface de operações acessar dados pode ser - busca salvar, deletar etc .. uma especie de DAO.
 * Busca tudo que pedir direto do banco.*/

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	/*Consulta JPQL do spring data, Função dentro do repository*/
	@Transactional(readOnly = true)//é apenas uma consulta não é necessario fazer uma transação
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Produto> search(@Param("nome")String nome, @Param("categorias")List<Categoria> categorias, Pageable pageRequest);

}
