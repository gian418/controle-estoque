package com.albano.controleestoque.dtos;

import com.albano.controleestoque.enums.TipoProduto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class AtualizarProdutoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Preenchimento obrigatorio")
    @Length(min = 3, max = 200, message = "O tamanho deve ser entre 3 e 200 caracteres")
    private String descricao;

    @NotNull(message = "Preenchimento obrigatorio")
    private TipoProduto tipo;

    @NotNull(message = "Preenchimento obrigatorio")
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
