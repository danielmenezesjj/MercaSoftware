package com.marcketbounty.marcketbounty.core.dto;


import com.marcketbounty.marcketbounty.core.domain.ItemCarrinho;
import com.marcketbounty.marcketbounty.core.domain.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarrinhoComprasDTO {
    private Integer id;
    private String nmpessoa;
    private String cpf;
    private Float valorTotal; // Adicionando o campo para o valor total
    private List<ItemCarrinhoDTO> itens = new ArrayList<>();
    private Float valorPago;
    private Float valorDoTroco;

}
