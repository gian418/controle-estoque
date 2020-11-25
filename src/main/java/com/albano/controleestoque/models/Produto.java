package com.albano.controleestoque.models;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.EnumType.STRING;

import com.albano.controleestoque.enums.TipoMovimentoEstoque;
import com.albano.controleestoque.enums.TipoProduto;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Enumerated(STRING)
    @Column(name = "tipo", nullable = false)
    private TipoProduto tipo;

    @Column(name = "valor_fornecedor", nullable = false)
    private BigDecimal valorFornecedor;

    @Column(name = "quantidade_estoque")
    private Integer quantidadeEstoque;

    @OneToMany(mappedBy = "produto")
    private List<MovimentoEstoque> movimentos = new ArrayList<>();

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

    public List<MovimentoEstoque> getMovimentos() {
        return movimentos;
    }

    public Integer getQuantidadeSaida() {
        if(movimentos.isEmpty()) return 0;

        return movimentos.stream()
                .filter(movimento -> movimento.getTipo().equals(TipoMovimentoEstoque.SAIDA))
                .mapToInt(MovimentoEstoque::getQuantidade)
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) &&
                Objects.equals(descricao, produto.descricao) &&
                tipo == produto.tipo &&
                Objects.equals(valorFornecedor, produto.valorFornecedor) &&
                Objects.equals(quantidadeEstoque, produto.quantidadeEstoque);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, tipo, valorFornecedor, quantidadeEstoque);
    }
}
