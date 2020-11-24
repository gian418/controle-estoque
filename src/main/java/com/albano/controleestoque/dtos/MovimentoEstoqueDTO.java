package com.albano.controleestoque.dtos;

import com.albano.controleestoque.enums.TipoMovimentoEstoque;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class MovimentoEstoqueDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer produto;
    private TipoMovimentoEstoque tipo;
    private BigDecimal valorVenda;
    private LocalDate dataVenda;
    private Integer quantidade;

    public Integer getProduto() {
        return produto;
    }

    public void setProduto(Integer produto) {
        this.produto = produto;
    }

    public TipoMovimentoEstoque getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentoEstoque tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
