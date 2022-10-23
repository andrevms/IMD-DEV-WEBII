package com.projeto3.projeto3.service;

import com.projeto3.projeto3.model.Estoque;
import com.projeto3.projeto3.rest.dto.EstoqueDTO;

public interface EstoqueService {
    Estoque salvar( EstoqueDTO dto );
    Estoque obterEstoquePorProdutoId(Integer produtoId);
    Estoque obterEstoquePorDescricaoProduto(String descricao);
    Estoque obterEstoque(Integer id);
    void atualizaEstoque(EstoqueDTO dto);
    void deletar(Integer id);
}
