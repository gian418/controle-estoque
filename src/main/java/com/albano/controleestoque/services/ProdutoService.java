package com.albano.controleestoque.services;

import com.albano.controleestoque.dtos.AtualizarProdutoDTO;
import com.albano.controleestoque.dtos.NovoProdutoDTO;
import com.albano.controleestoque.dtos.ProdutoDTO;
import com.albano.controleestoque.models.Produto;
import com.albano.controleestoque.repositories.ProdutoRepository;
import com.albano.controleestoque.services.excepetions.ProdutoIntegridadeException;
import com.albano.controleestoque.services.excepetions.ProdutoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        return produto.orElseThrow(
                () -> new ProdutoNaoEncontradoException("Produto não encontrado pela id " + idProduto)
        );
    }

    public void deletar(Integer idProduto) {
        consultarPorId(idProduto);
        try {
            produtoRepository.deleteById(idProduto);
        } catch (DataIntegrityViolationException e) {
            throw new ProdutoIntegridadeException("Não é possuivel deletar este produto, pois possui relação com outros registros");
        }
    }

    public Produto atualizar(Integer idProduto, AtualizarProdutoDTO dto) {
        Produto produto = consultarPorId(idProduto);
        produto.setValorFornecedor(dto.getValorFornecedor());
        produto.setTipo(dto.getTipo());
        produto.setDescricao(dto.getDescricao());
        return produtoRepository.save(produto);
    }

    public List<ProdutoDTO> buscarTodos() {
        List<Produto> produtos = (List<Produto>) produtoRepository.findAll();
        if(produtos.isEmpty()) return new ArrayList<>();
        return produtos.stream().map(produto -> ProdutoDTO.from(produto)).collect(Collectors.toList());
    }
}
