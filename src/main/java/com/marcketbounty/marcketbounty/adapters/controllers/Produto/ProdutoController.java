package com.marcketbounty.marcketbounty.adapters.controllers.Produto;


import com.marcketbounty.marcketbounty.core.businessRule.ProdutoRules;
import com.marcketbounty.marcketbounty.core.domain.Produto;
import com.marcketbounty.marcketbounty.core.dto.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRules rules;

    @GetMapping
    public ResponseEntity<List<Produto>> gettAll(){
        List<Produto> produtos = rules.getAll();
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getOne(@PathVariable Integer id){
       var Produto = rules.getOne(id);
        return ResponseEntity.ok().body(Produto);
    }

    @GetMapping("/cdproduto/{cdproduto}")
    public ResponseEntity<Produto> getByCdproduto(@PathVariable String cdproduto){
        var Produto = rules.getByCdProduto(cdproduto);
        return ResponseEntity.ok().body(Produto);
    }

    @PostMapping
    public ResponseEntity<Produto> insert(@ModelAttribute  ProdutoDTO productData, @RequestParam("file") MultipartFile file){
        Produto produto = rules.insert(productData, file);
        return ResponseEntity.ok().body(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Integer id, @RequestBody ProdutoDTO productData){
        rules.update(id, productData);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> delete(@PathVariable Integer id){
        rules.delete(id);
        return ResponseEntity.noContent().build();
    }

}
