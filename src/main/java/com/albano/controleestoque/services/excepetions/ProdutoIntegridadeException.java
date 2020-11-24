package com.albano.controleestoque.services.excepetions;

public class ProdutoIntegridadeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProdutoIntegridadeException(String message) {
        super(message);
    }
}
