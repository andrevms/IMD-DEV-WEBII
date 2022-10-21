package com.projeto3.projeto3.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.projeto3.projeto3.repository.ProdutoRepository;
import com.projeto3.projeto3.rest.dto.ProdutoDTO;
import com.projeto3.projeto3.service.ProdutoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {
    private final ProdutoRepository produtosRepository;

    @Override
    @Transactional
    public void atualizaProduto(ProdutoDTO produtoDTO) {
        produtosRepository.findById(produtoDTO.getId())
        .map( produto -> {
            if( produtoDTO.getDescricao() != null) {
                produto.setDescricao(produtoDTO.getDescricao());
            }

            if( produtoDTO.getPreco() != null) {
                produto.setPreco(produtoDTO.getPreco());
            }
            
            produtosRepository.save(produto);
            return produto;
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Produto não encontrado") );
    }

}
