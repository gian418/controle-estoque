package com.albano.controleestoque.dtos;

import com.albano.controleestoque.enums.TipoProduto;
import com.albano.controleestoque.models.Produto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class NovoProdutoDTO implements Serializable {
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
        NovoProdutoDTO that = (NovoProdutoDTO) o;
        return Objects.equals(descricao, that.descricao) &&
                tipo == that.tipo &&
                Objects.equals(valorFornecedor, that.valorFornecedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, tipo, valorFornecedor);
    }

    public static NovoProdutoDTO from(Produto produto) {
        NovoProdutoDTO dto = new NovoProdutoDTO();
        dto.setDescricao(produto.getDescricao());
        dto.setTipo(produto.getTipo());
        dto.setValorFornecedor(produto.getValorFornecedor());
        return dto;
    }

    public Produto toProduto(){
        Produto produto = new Produto();
        produto.setDescricao(descricao);
        produto.setTipo(tipo);
        produto.setQuantidadeEstoque(0);
        produto.setValorFornecedor(valorFornecedor);
        return produto;
    }


}
