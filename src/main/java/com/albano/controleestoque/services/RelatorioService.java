package com.albano.controleestoque.services;

import com.albano.controleestoque.dtos.ProdutoDTO;
import com.albano.controleestoque.dtos.RelatorioLucroProdutoDTO;
import com.albano.controleestoque.dtos.RelatorioProdutoTipoDTO;
import com.albano.controleestoque.enums.TipoProduto;
import com.albano.controleestoque.models.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private ProdutoService produtoService;

    public RelatorioProdutoTipoDTO relatorioPorTipoProduto(TipoProduto tipoProduto) {
        List<Produto> produtos = produtoService.buscarProdutosPorTipo(tipoProduto);
        return contruirRelatorioProdutoTipoDTO(tipoProduto, produtos);
    }

    private RelatorioProdutoTipoDTO contruirRelatorioProdutoTipoDTO(TipoProduto tipoProduto, List<Produto> produtos) {
        RelatorioProdutoTipoDTO dto = new RelatorioProdutoTipoDTO();
        dto.setTipoProduto(tipoProduto);
        List<ProdutoDTO> produtoDTOList = produtos.stream().map(produto -> ProdutoDTO.from(produto)).collect(Collectors.toList());
        dto.setProdutos(produtoDTOList);
        return dto;
    }

    public RelatorioLucroProdutoDTO relatorioLucroPorProduto(Integer idProduto) {
        Produto produto = produtoService.buscarPorId(idProduto);
        RelatorioLucroProdutoDTO relatorioDto = new RelatorioLucroProdutoDTO();
        relatorioDto.setId(produto.getId());
        relatorioDto.setDescricao(produto.getDescricao());
        relatorioDto.setTipo(produto.getTipo());
        relatorioDto.setQuantidadeSaida(produto.getQuantidadeSaida());
        relatorioDto.setValorLucro(calcularLucro(produto));

        return relatorioDto;
    }

    private BigDecimal obterValorVenda(Produto produto) {
        if (produto.getMovimentos().isEmpty()) return BigDecimal.ZERO;
        return produto.getMovimentos().stream().findFirst().get().getValorVenda();
    }

    private BigDecimal calcularLucro(Produto produto) {
        BigDecimal valorVenda = obterValorVenda(produto);
        BigDecimal valorLucro = valorVenda.min(produto.getValorFornecedor());
        return valorLucro.compareTo(BigDecimal.ZERO) > 0 ? valorLucro : BigDecimal.ZERO;
    }


}
