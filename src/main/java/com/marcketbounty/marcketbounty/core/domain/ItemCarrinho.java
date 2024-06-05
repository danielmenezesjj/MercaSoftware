package com.marcketbounty.marcketbounty.core.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ItemCarrinho")
public class ItemCarrinho {

    @Id
    @GeneratedValue
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private CarrinhoDeCompras carrinho;


    @ManyToOne
    @JoinColumn(name = "produto_cd")
    private Produto produto;
    private Integer quantidadePedida;


}
