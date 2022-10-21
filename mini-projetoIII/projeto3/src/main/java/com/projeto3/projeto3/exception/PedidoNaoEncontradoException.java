package com.projeto3.projeto3.exception;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException() {
        super("Pedido não encontrado.");
    }
}
