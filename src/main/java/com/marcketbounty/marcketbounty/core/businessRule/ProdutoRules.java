package com.marcketbounty.marcketbounty.core.businessRule;


import com.marcketbounty.marcketbounty.core.domain.Produto;
import com.marcketbounty.marcketbounty.core.dto.ProdutoDTO;
import com.marcketbounty.marcketbounty.infra.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoRules {


    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private S3BucketAwsService s3BucketAwsService;

    public Produto insert (ProdutoDTO productData, MultipartFile file){
        Produto product = new Produto(productData);
        product.setImagem(s3BucketAwsService.uploadImage(file));
        repository.save(product);
        return product;
    }

    public List<Produto> getAll(){
        return repository.findAll();
    }

    public Produto getOne(Integer id){
        Optional<Produto> optionalProduto = repository.findById(id);
        if(optionalProduto.isPresent()){
            return optionalProduto.get();
        }
        throw new RuntimeException("Produto não encontrado!");
    }

    public Produto getByCdProduto(String cdProduto){
        Optional<Produto> optionalProduto = repository.findBycdproduto(cdProduto);
        if(optionalProduto.isPresent()){
            return optionalProduto.get();
        }
        throw new RuntimeException("Produto não encontrado!");
    }

    public Produto update(Integer id, ProdutoDTO productData){
        Optional<Produto> optionalProduto = repository.findById(id);
        if(optionalProduto.isPresent()){
            Produto produto = optionalProduto.get();
            produto.UpdateProduto(productData);
            repository.save(produto);
            return produto;
        }
        throw new RuntimeException("Produto não encontrado!");
    }

    public Produto delete(Integer id){
        Optional<Produto> optionalProduto = repository.findById(id);
        if(optionalProduto.isPresent()){
            Produto produto = optionalProduto.get();
            repository.delete(produto);
            return produto;
        }
        throw new RuntimeException("Produto não encontrado!");
    }


}
