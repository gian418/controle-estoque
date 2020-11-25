package com.albano.controleestoque.services;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.albano.controleestoque.dtos.AtualizarProdutoDTO;
import com.albano.controleestoque.dtos.NovoProdutoDTO;
import com.albano.controleestoque.enums.TipoProduto;
import com.albano.controleestoque.models.Produto;
import com.albano.controleestoque.services.excepetions.ProdutoNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class ProdutoServiceTest {

    @Autowired
    private ProdutoService produtoService;

    @Test
    public void deveInserirUmProduto() {
        NovoProdutoDTO dto = new NovoProdutoDTO();
        dto.setDescricao("teste insercao");
        dto.setTipo(TipoProduto.ELETRONICO);
        dto.setValorFornecedor(BigDecimal.TEN);
        Produto produto = produtoService.salvar(dto);

        assertTrue(produto.getDescricao().contains(dto.getDescricao()));
    }

    @Test
    public void deveAtualizarUmProduto() {
        AtualizarProdutoDTO dto = new AtualizarProdutoDTO();
        dto.setDescricao("teste atualizacao");
        dto.setTipo(TipoProduto.ELETRONICO);
        dto.setValorFornecedor(BigDecimal.TEN);
        Integer idProduto = 1;
        Produto produto = produtoService.atualizar(1, dto);

        assertTrue(produto.getDescricao().contains(dto.getDescricao()));
    }

    @Test
    public void deveDeletarUmProduto() {
        Integer idProduto = 2;
        Produto consultaPreDelecao = produtoService.buscarPorId(idProduto);

        produtoService.deletar(idProduto);

        Exception ex = assertThrows(ProdutoNaoEncontradoException.class, () -> {
            produtoService.buscarPorId(idProduto);
        });

        String mensagemEsperada = "Produto não encontrado pela id " + idProduto;
        String mensagemAtual = ex.getMessage();

        assertTrue(consultaPreDelecao != null);
        assertTrue(mensagemEsperada.contains(mensagemAtual));
    }

    @Test
    public void deveBuscarPorId() {
        Integer idProduto = 3;
        Produto produto = produtoService.buscarPorId(idProduto);
        assertTrue(produto.getId().equals(idProduto));
    }

    @Test
    public void naoDeveEncontrarProduto() {
        Integer idProduto = 500;
        Exception ex = assertThrows(ProdutoNaoEncontradoException.class, () -> {
            produtoService.buscarPorId(idProduto);
        });

        String mensagemEsperada = "Produto não encontrado pela id " + idProduto;
        String mensagemAtual = ex.getMessage();

        assertTrue(mensagemEsperada.contains(mensagemAtual));
    }

}
