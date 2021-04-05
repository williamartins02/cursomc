package com.williamartins.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.williamartins.cursomc.domain.Categoria;

/*Interface de operações - salvar, listar, deletar etc .. uma especie de DAO.
 * Busca tudo que pedir direto do banco.*/

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
