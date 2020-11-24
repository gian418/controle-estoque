package com.albano.controleestoque.services;

import com.albano.controleestoque.dtos.AtualizarProdutoDTO;
import com.albano.controleestoque.dtos.NovoProdutoDTO;
import com.albano.controleestoque.dtos.ProdutoDTO;
import com.albano.controleestoque.models.Produto;
import com.albano.controleestoque.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvar(NovoProdutoDTO dto) {
        Produto novoProduto = dto.toProduto();
        return produtoRepository.save(novoProduto);
    }

    public Produto consultarPorId(Integer idProduto) {
        Optional<Produto> produto = produtoRepository.findById(idProduto);
        return produto.orElseThrow(); //TODO criar erro personalizado
    }

    public void deletar(Integer idProduto) {
        consultarPorId(idProduto);

        try {
            produtoRepository.deleteById(idProduto);
        } catch (Exception e) {
            //TODO criar um erro personalizado, nao deixar deletar se tem movimento de estoque
        }

    }

    public Produto atualizar(Integer idProduto, AtualizarProdutoDTO dto) {
        Produto produdo = consultarPorId(idProduto);
        produdo.setValorFornecedor(dto.getValorFornecedor());
        produdo.setTipo(dto.getTipo());
        produdo.setDescricao(dto.getDescricao());
        return produtoRepository.save(produdo);
    }

    public List<ProdutoDTO> buscarTodos() {
        List<Produto> produtos = (List<Produto>) produtoRepository.findAll();

        if(produtos.isEmpty()) return new ArrayList<>();

        return produtos.stream().map(produto -> ProdutoDTO.from(produto)).collect(Collectors.toList());
    }
}
