package com.projeto3.projeto3.rest.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientePedidoDTO {
    private String nome;
    private String cpf;
    private List<PedidoClienteDTO> pedidos;
}
