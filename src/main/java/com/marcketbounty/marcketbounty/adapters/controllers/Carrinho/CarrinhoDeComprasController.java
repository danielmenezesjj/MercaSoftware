package com.marcketbounty.marcketbounty.adapters.controllers.Carrinho;


import com.marcketbounty.marcketbounty.core.businessRule.CarrinhoComprasRules;
import com.marcketbounty.marcketbounty.core.domain.CarrinhoDeCompras;
import com.marcketbounty.marcketbounty.core.domain.Produto;
import com.marcketbounty.marcketbounty.core.dto.CarrinhoComprasDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrinho")
@RequiredArgsConstructor
public class CarrinhoDeComprasController {

    @Autowired
    private CarrinhoComprasRules carrinhoComprasRules;

    @Autowired
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<CarrinhoComprasDTO> criarCarrinho(@RequestBody CarrinhoComprasDTO carrinhoComprasDTO){
        CarrinhoComprasDTO carrinhoCompras = carrinhoComprasRules.criarCarrinho(carrinhoComprasDTO);
        return new ResponseEntity<>(carrinhoCompras, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CarrinhoDeCompras>> gettAll(){
        List<CarrinhoDeCompras> carrinhoDeCompras = carrinhoComprasRules.getAll();
        return ResponseEntity.ok().body(carrinhoDeCompras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarrinhoDeCompras> getOne(@PathVariable Integer id){
        var carrinhoDeCompras = carrinhoComprasRules.getOne(id);
        return ResponseEntity.ok().body(carrinhoDeCompras);
    }


}
