package com.marcketbounty.marcketbounty.core.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcketbounty.marcketbounty.core.dto.ProdutoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "produto")
public class Produto {

    @Id
    @GeneratedValue
    @Column(name = "id_produto")
    private Integer id;
    @Column(name = "nmProduto")
    private String nmproduto;
    private Boolean stativo;
    private Float vlunitario;
    private String cdproduto;

    private Integer quantidade;

    private String imagem;





    public Produto(ProdutoDTO productData){
        this.nmproduto = productData.nmproduto();
        this.stativo = productData.stativo();
        this.vlunitario = productData.vlunitario();
        this.cdproduto = productData.cdproduto();
        this.quantidade = productData.quantidade();
        this.imagem = productData.imagem();
    }

    public void UpdateProduto(ProdutoDTO productData){
        if(productData.nmproduto() != null){
            this.nmproduto = productData.nmproduto();
        }
        if(productData.stativo() != null){
            this.stativo = productData.stativo();
        }
        if(productData.vlunitario() != null){
            this.vlunitario = productData.vlunitario();
        }
        if(productData.cdproduto() != null){
            this.cdproduto = productData.cdproduto();
        }
        if(productData.quantidade() != null){
            this.quantidade = productData.quantidade();
        }
    }

}
