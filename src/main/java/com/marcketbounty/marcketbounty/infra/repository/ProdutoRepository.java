package com.marcketbounty.marcketbounty.infra.repository;

import com.marcketbounty.marcketbounty.core.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {


    Optional<Produto> findBycdproduto(String cdProduto);
}
