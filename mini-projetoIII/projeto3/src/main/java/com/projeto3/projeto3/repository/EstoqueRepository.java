package com.projeto3.projeto3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projeto3.projeto3.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {

    @Query(value = "select * from estoque where estoque.produto_id = produto.id", nativeQuery= true )
    Optional<Estoque> findByProdutoId(Integer produtoId);

    @Query(value = "select * from estoque left join produto on estoque.produto_id = produto.id where produto.descricao = produtoDescricao", nativeQuery= true )
    Optional<Estoque> findByProdutodescricao(String produtoDescricao);
    
}
