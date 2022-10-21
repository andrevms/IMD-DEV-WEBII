package com.projeto3.projeto3.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoClienteDTO {
    private Integer id;
    private BigDecimal total;
    private List<InformacaoItemPedidoDTO> itens;
}
