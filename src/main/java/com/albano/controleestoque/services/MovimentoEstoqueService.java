package com.albano.controleestoque.services;

import static com.albano.controleestoque.enums.TipoMovimentoEstoque.ENTRADA;

import com.albano.controleestoque.dtos.MovimentoEstoqueDTO;
import com.albano.controleestoque.models.MovimentoEstoque;
import com.albano.controleestoque.models.Produto;
import com.albano.controleestoque.repositories.MovimentoEstoqueRepository;
import com.albano.controleestoque.services.excepetions.ProdutoSemEstoqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimentoEstoqueService {

    @Autowired
    private MovimentoEstoqueRepository movimentoEstoqueRepository;

    @Autowired
    private ProdutoService produtoService;

    public MovimentoEstoque salvar(MovimentoEstoqueDTO dto) {
        Produto produto = produtoService.buscarPorId(dto.getProduto());
        MovimentoEstoque movimentoEstoque = dto.toMovimentoEstoque();
        return movimentarEstoque(movimentoEstoque, produto);
    }

    private MovimentoEstoque movimentarEstoque(MovimentoEstoque movimentoEstoque, Produto produto) {
        Integer novaQuantidade = 0;
        if(movimentoEstoque.getTipo().equals(ENTRADA)) {
            novaQuantidade = produto.getQuantidadeEstoque() + movimentoEstoque.getQuantidade();
        } else {
            this.validarEstoqueSaida(movimentoEstoque, produto);
            novaQuantidade = produto.getQuantidadeEstoque() - movimentoEstoque.getQuantidade();
        }

        return salvarMovimentoAtualizarProduto(movimentoEstoque, produto, novaQuantidade);
    }

    private void validarEstoqueSaida(MovimentoEstoque movimentoEstoque, Produto produto) {
        if (movimentoEstoque.getQuantidade() > produto.getQuantidadeEstoque()) {
           throw new ProdutoSemEstoqueException("O produto não tem estoque suficiente para este movimento");
        }
    }

    private MovimentoEstoque salvarMovimentoAtualizarProduto(MovimentoEstoque movimentoEstoque, Produto produto, Integer novaQuantidade) {
        produto.setQuantidadeEstoque(novaQuantidade);
        movimentoEstoque.setProduto(produto);
        return movimentoEstoqueRepository.save(movimentoEstoque);
    }


}
