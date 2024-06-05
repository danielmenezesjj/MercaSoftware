package com.marcketbounty.marcketbounty.core.businessRule;


import com.marcketbounty.marcketbounty.core.domain.CarrinhoDeCompras;
import com.marcketbounty.marcketbounty.core.domain.ItemCarrinho;
import com.marcketbounty.marcketbounty.core.domain.Produto;
import com.marcketbounty.marcketbounty.core.dto.CarrinhoComprasDTO;
import com.marcketbounty.marcketbounty.core.dto.ItemCarrinhoDTO;
import com.marcketbounty.marcketbounty.core.dto.ProdutoDTO;
import com.marcketbounty.marcketbounty.infra.repository.CarrinhoComprasRepository;
import com.marcketbounty.marcketbounty.infra.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarrinhoComprasRules {

    private final CarrinhoComprasRepository carrinhoComprasRepository;

    private final ProdutoRepository produtoRepository;
    private final ModelMapper modelMapper;

    public CarrinhoComprasDTO criarCarrinho(CarrinhoComprasDTO dto) {
        CarrinhoDeCompras carrinhoDeCompras = modelMapper.map(dto, CarrinhoDeCompras.class);
        List<ItemCarrinhoDTO> itensDTO = dto.getItens();
        List<ItemCarrinho> itensCarrinho = new ArrayList<>();

        for (ItemCarrinhoDTO itemDTO : itensDTO) {
            String codigoProduto = itemDTO.getProdutoCodigo();


            Produto produto = produtoRepository.findBycdproduto(codigoProduto)
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado com o código: " + codigoProduto));

            retirarProdutoDoEstoqueComBaseNaCompra(codigoProduto, itemDTO.getQuantidadePedida());

            ItemCarrinho item = new ItemCarrinho();
            item.setProduto(produto);
            item.setQuantidadePedida(itemDTO.getQuantidadePedida());
            item.setCarrinho(carrinhoDeCompras);
            itensCarrinho.add(item);
        }

        carrinhoDeCompras.setItens(itensCarrinho);
        Float valorTotal = calcularValorTotal(itensCarrinho);
        carrinhoDeCompras.setValorTotal(valorTotal);
        if(dto.getValorPago() == 0 || dto.getValorPago() == null){
            CarrinhoDeCompras carrinhoSalvo = carrinhoComprasRepository.save(carrinhoDeCompras);
            return modelMapper.map(carrinhoSalvo, CarrinhoComprasDTO.class);
//            aqui
        }
        carrinhoDeCompras.setValorDoTroco(dto.getValorPago() - valorTotal);
        CarrinhoDeCompras carrinhoSalvo = carrinhoComprasRepository.save(carrinhoDeCompras);
        return modelMapper.map(carrinhoSalvo, CarrinhoComprasDTO.class);
    }

    public void retirarProdutoDoEstoqueComBaseNaCompra(String codigoProduto, Integer quantidadePedida){
        Produto produto = produtoRepository.findBycdproduto(codigoProduto).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        if(produto.getQuantidade() >= quantidadePedida){
            produto.setQuantidade(produto.getQuantidade() - quantidadePedida);
            produtoRepository.save(produto);
        }else{
            throw new RuntimeException("Quantidade solicitada do produto não está disponível");
        }
    }




    public Float calcularValorTotal(List<ItemCarrinho> itens) {
        Float valorTotal = 0.0f;
        for (ItemCarrinho item : itens) {
            Produto produto = produtoRepository.findBycdproduto(item.getProduto().getCdproduto()).orElse(null);
            if (produto != null) {
                valorTotal += produto.getVlunitario() * item.getQuantidadePedida();
            }
        }
        return valorTotal;
    }

    public List<CarrinhoDeCompras> getAll(){
        return carrinhoComprasRepository.findAll();
    }

    public CarrinhoDeCompras getOne(Integer id){
        Optional<CarrinhoDeCompras> optionalCarrinhoDeCompras = carrinhoComprasRepository.findById(id);
        if(optionalCarrinhoDeCompras.isPresent()){
            return optionalCarrinhoDeCompras.get();
        }
        throw new RuntimeException("Carrinho não encontrado!");
    }

}
