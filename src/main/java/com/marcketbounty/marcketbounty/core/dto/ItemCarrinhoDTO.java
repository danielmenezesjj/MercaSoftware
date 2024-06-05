package com.marcketbounty.marcketbounty.core.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCarrinhoDTO {
    private Integer id;
    private String produtoCodigo; // Você pode usar o ID do produto em vez de passar o objeto Produto diretamente
    private Integer quantidadePedida;
}
