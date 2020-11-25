package com.albano.controleestoque.dtos;

import com.albano.controleestoque.enums.TipoProduto;
import com.albano.controleestoque.models.Produto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class ProdutoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private TipoProduto tipo;
    private BigDecimal valorFornecedor;
    private Integer quantidadeEstoque;
    private Integer quantidadeSaida;

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

    public BigDecimal getValorFornecedor() {
        return valorFornecedor;
    }

    public void setValorFornecedor(BigDecimal valorFornecedor) {
        this.valorFornecedor = valorFornecedor;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Integer getQuantidadeSaida() {
        return quantidadeSaida;
    }

    public void setQuantidadeSaida(Integer quantidadeSaida) {
        this.quantidadeSaida = quantidadeSaida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoDTO that = (ProdutoDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(descricao, that.descricao) &&
                tipo == that.tipo &&
                Objects.equals(valorFornecedor, that.valorFornecedor) &&
                Objects.equals(quantidadeEstoque, that.quantidadeEstoque);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, tipo, valorFornecedor, quantidadeEstoque);
    }

    public static ProdutoDTO from(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setDescricao(produto.getDescricao());
        dto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        dto.setTipo(produto.getTipo());
        dto.setValorFornecedor(produto.getValorFornecedor());
        dto.setQuantidadeSaida(produto.getQuantidadeSaida());
        return dto;
    }

    public Produto toProduto(){
        Produto produto = new Produto();
        produto.setId(id);
        produto.setDescricao(descricao);
        produto.setTipo(tipo);
        produto.setQuantidadeEstoque(quantidadeEstoque);
        produto.setValorFornecedor(valorFornecedor);
        return produto;
    }
}
