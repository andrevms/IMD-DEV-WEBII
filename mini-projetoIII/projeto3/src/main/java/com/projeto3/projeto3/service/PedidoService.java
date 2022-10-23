package com.projeto3.projeto3.service;

import java.util.Optional;

import com.projeto3.projeto3.enums.StatusPedido;
import com.projeto3.projeto3.model.Pedido;
import com.projeto3.projeto3.rest.dto.PedidoDTO;

public interface PedidoService {
    
    Pedido salvar( PedidoDTO dto );
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
    void atualizaClientePedido(Integer id, Integer clienteId);
    void deletar(Integer id);
}
