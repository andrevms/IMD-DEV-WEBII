package com.projeto3.projeto3.rest.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
    private Integer id;
    private String descricao;
    private BigDecimal preco;
}
