package com.marcketbounty.marcketbounty.core.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "carrinhodecompras")
public class CarrinhoDeCompras {

    @Id
    @GeneratedValue
    private Integer id;
    private String nmpessoa;
    private String cpf;
    private double valorTotal; // Adicionando o campo para armazenar o valor total
    private Float valorPago;
    private Float valorDoTroco;


    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL)
    private List<ItemCarrinho> itens = new ArrayList<>();


}
