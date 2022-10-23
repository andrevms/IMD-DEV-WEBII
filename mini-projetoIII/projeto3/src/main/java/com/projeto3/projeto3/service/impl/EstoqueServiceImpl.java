package com.projeto3.projeto3.service.impl;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.projeto3.projeto3.exception.RegraNegocioException;
import com.projeto3.projeto3.model.Estoque;
import com.projeto3.projeto3.repository.EstoqueRepository;
import com.projeto3.projeto3.repository.ProdutoRepository;
import com.projeto3.projeto3.rest.dto.EstoqueDTO;
import com.projeto3.projeto3.service.EstoqueService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstoqueServiceImpl implements EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public Estoque salvar(EstoqueDTO dto) {
        Estoque estoque = new Estoque();
        estoque.setQuantidade(dto.getQuantidade());
        estoque.setProduto(
            produtoRepository.
                findById(dto.getProdutoId()).orElseThrow( () -> new RegraNegocioException("Código de produto inválido"))
            );
        estoqueRepository.save(estoque);
        return estoque;
    }

    @Override
    public Estoque obterEstoquePorProdutoId(Integer produtoId) {
        return estoqueRepository
        .findByProdutoId(produtoId)
        .orElseThrow(
            ()-> new RegraNegocioException("Estoque do produto não encontrado")
            );
    }

    @Override
    public Estoque obterEstoquePorDescricaoProduto(String descricao) {
        return estoqueRepository
        .findByProdutodescricao(descricao)
        .orElseThrow(
            ()-> new RegraNegocioException("Estoque do produto não encontrado")
            );
    }


    @Override
    public Estoque obterEstoque(Integer id) {
        return estoqueRepository
        .findById(id)
        .orElseThrow(
            ()-> new RegraNegocioException("Estoque não encontrado")
            );
    }

    @Override
    public void atualizaEstoque(EstoqueDTO dto) {
        estoqueRepository.findById(dto.getId())
        .map( estoque -> {
            if( dto.getQuantidade() != null) {
               estoque.setQuantidade(dto.getQuantidade());
            }
            
            estoqueRepository.save(estoque);
            return estoque;
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Estoque não encontrado") );
    }

    @Override
    public void deletar(Integer id) {
        estoqueRepository.findById(id)
        .map( estoque -> {
            estoqueRepository.delete(estoque);
            return estoque;
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Estoque não encontrado") );
    }
    
}
