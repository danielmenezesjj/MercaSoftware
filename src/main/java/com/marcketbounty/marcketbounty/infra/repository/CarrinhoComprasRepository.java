package com.marcketbounty.marcketbounty.infra.repository;

import com.marcketbounty.marcketbounty.core.domain.CarrinhoDeCompras;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoComprasRepository extends JpaRepository<CarrinhoDeCompras, Integer> {
}
