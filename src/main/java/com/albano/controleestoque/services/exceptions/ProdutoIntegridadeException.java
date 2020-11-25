package com.albano.controleestoque.services.exceptions;

public class ProdutoIntegridadeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProdutoIntegridadeException(String message) {
        super(message);
    }
}
