package com.albano.controleestoque.dtos;

import com.albano.controleestoque.enums.TipoProduto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class AtualizarProdutoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String descricao;
    private TipoProduto tipo;
    private BigDecimal valorFornecedor;

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

    public BigDecimal getValorFornecedor() {
        return valorFornecedor;
    }

    public void setValorFornecedor(BigDecimal valorFornecedor) {
        this.valorFornecedor = valorFornecedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtualizarProdutoDTO that = (AtualizarProdutoDTO) o;
        return Objects.equals(descricao, that.descricao) &&
                tipo == that.tipo &&
                Objects.equals(valorFornecedor, that.valorFornecedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, tipo, valorFornecedor);
    }
}
