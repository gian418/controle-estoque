package com.albano.controleestoque.models;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.CascadeType.MERGE;

import com.albano.controleestoque.enums.TipoMovimentoEstoque;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "movimento_estoque")
public class MovimentoEstoque {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = MERGE)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Enumerated(STRING)
    @Column(name = "tipo", nullable = false)
    private TipoMovimentoEstoque tipo;

    @Column(name = "valor_venda", nullable = false)
    private BigDecimal valorVenda;

    @Column(name = "data_venda")
    private LocalDate dataVenda;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovimentoEstoque that = (MovimentoEstoque) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(produto, that.produto) &&
                tipo == that.tipo &&
                Objects.equals(valorVenda, that.valorVenda) &&
                Objects.equals(dataVenda, that.dataVenda) &&
                Objects.equals(quantidade, that.quantidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, produto, tipo, valorVenda, dataVenda, quantidade);
    }
}
