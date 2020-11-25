package com.albano.controleestoque.services;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.albano.controleestoque.dtos.MovimentoEstoqueDTO;
import com.albano.controleestoque.enums.TipoMovimentoEstoque;
import com.albano.controleestoque.models.MovimentoEstoque;
import com.albano.controleestoque.services.excepetions.ProdutoSemEstoqueException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class MovimentoEstoqueServiceTest {

    @Autowired
    private MovimentoEstoqueService movimentoEstoqueService;

    @Test
    public void deveInserirMovimentoEntrada() {
        MovimentoEstoqueDTO dto = new MovimentoEstoqueDTO();
        dto.setTipo(TipoMovimentoEstoque.ENTRADA);
        dto.setProduto(3);
        dto.setValorVenda(BigDecimal.valueOf(20));
        dto.setQuantidade(5);

        MovimentoEstoque movimento = movimentoEstoqueService.salvar(dto);
        assertTrue(movimento.getProduto().getId().equals(dto.getProduto()));
    }

    @Test
    public void naoDeveInserirMovimentoSaidaComEstoqueInsuficiente() {
        MovimentoEstoqueDTO dto = new MovimentoEstoqueDTO();
        dto.setTipo(TipoMovimentoEstoque.SAIDA);
        dto.setProduto(4);
        dto.setValorVenda(BigDecimal.valueOf(20));
        dto.setQuantidade(5);
        dto.setDataVenda(LocalDate.of(2020, 11, 10));

        Exception ex = assertThrows(ProdutoSemEstoqueException.class, () -> {
           movimentoEstoqueService.salvar(dto);
        });

        String mensagemEsperada = "O produto não tem estoque suficiente para este movimento";
        String mensagemAtual = ex.getMessage();

        assertTrue(mensagemEsperada.contains(mensagemAtual));
    }

    @Test
    public void deveInserirMovimentoSaida() {
        MovimentoEstoqueDTO dto = new MovimentoEstoqueDTO();
        dto.setTipo(TipoMovimentoEstoque.SAIDA);
        dto.setProduto(5);
        dto.setValorVenda(BigDecimal.valueOf(50));
        dto.setQuantidade(10);
        dto.setDataVenda(LocalDate.of(2020, 11, 10));

        MovimentoEstoque movimento = movimentoEstoqueService.salvar(dto);
        assertTrue(movimento.getProduto().getId().equals(dto.getProduto()));
    }

}
