package com.albano.controleestoque.dtos;

import com.albano.controleestoque.enums.TipoMovimentoEstoque;
import com.albano.controleestoque.models.MovimentoEstoque;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class MovimentoEstoqueDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Preenchimento obrigatorio")
    private Integer produto;

    @NotNull(message = "Preenchimento obrigaotorio")
    private TipoMovimentoEstoque tipo;

    @NotNull(message = "Preenchimento obrigatorio")
    private BigDecimal valorVenda;

    private LocalDate dataVenda;

    @NotNull(message = "Preenchimento obrigatorio")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovimentoEstoqueDTO that = (MovimentoEstoqueDTO) o;
        return Objects.equals(produto, that.produto) &&
                tipo == that.tipo &&
                Objects.equals(valorVenda, that.valorVenda) &&
                Objects.equals(dataVenda, that.dataVenda) &&
                Objects.equals(quantidade, that.quantidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, tipo, valorVenda, dataVenda, quantidade);
    }

    public static MovimentoEstoqueDTO from(MovimentoEstoque movimentoEstoque) {
        MovimentoEstoqueDTO dto = new MovimentoEstoqueDTO();
        dto.setDataVenda(movimentoEstoque.getDataVenda());
        dto.setProduto(movimentoEstoque.getProduto().getId());
        dto.setQuantidade(movimentoEstoque.getQuantidade());
        dto.setValorVenda(movimentoEstoque.getValorVenda());
        dto.setTipo(movimentoEstoque.getTipo());
        return dto;
    }

    public MovimentoEstoque toMovimentoEstoque() {
        MovimentoEstoque movimentoEstoque = new MovimentoEstoque();
        movimentoEstoque.setDataVenda(dataVenda);
        movimentoEstoque.setQuantidade(quantidade);
        movimentoEstoque.setValorVenda(valorVenda);
        movimentoEstoque.setTipo(tipo);
        //adicionar o produto apos chamar esse metodo
        return movimentoEstoque;
    }
}
