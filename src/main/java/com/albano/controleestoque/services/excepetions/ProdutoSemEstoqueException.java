package com.albano.controleestoque.services.excepetions;

public class ProdutoSemEstoqueException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProdutoSemEstoqueException(String message) {
        super(message);
    }
}
