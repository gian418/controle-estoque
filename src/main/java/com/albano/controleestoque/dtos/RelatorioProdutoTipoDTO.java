package com.albano.controleestoque.dtos;

import com.albano.controleestoque.enums.TipoProduto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RelatorioProdutoTipoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private TipoProduto tipoProduto;
    private List<ProdutoDTO> produtos = new ArrayList<>();

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public List<ProdutoDTO> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }
}
