package com.projeto3.projeto3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto3.projeto3.model.Produto;


public interface ProdutoRepository extends JpaRepository<Produto,Integer>{
    
}
