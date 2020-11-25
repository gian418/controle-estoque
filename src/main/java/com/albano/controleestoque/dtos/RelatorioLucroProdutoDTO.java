package com.albano.controleestoque.dtos;

import com.albano.controleestoque.enums.TipoProduto;

import java.io.Serializable;
import java.math.BigDecimal;

public class RelatorioLucroProdutoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private TipoProduto tipo;
    private Integer quantidadeSaida;
    private BigDecimal valorLucro;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidadeSaida() {
        return quantidadeSaida;
    }

    public void setQuantidadeSaida(Integer quantidadeSaida) {
        this.quantidadeSaida = quantidadeSaida;
    }

    public BigDecimal getValorLucro() {
        return valorLucro;
    }

    public void setValorLucro(BigDecimal valorLucro) {
        this.valorLucro = valorLucro;
    }
}
