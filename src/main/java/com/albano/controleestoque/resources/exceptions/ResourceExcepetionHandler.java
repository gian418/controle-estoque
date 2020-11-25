package com.albano.controleestoque.resources.exceptions;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.albano.controleestoque.services.exceptions.ProdutoIntegridadeException;
import com.albano.controleestoque.services.exceptions.ProdutoNaoEncontradoException;
import com.albano.controleestoque.services.exceptions.ProdutoSemEstoqueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExcepetionHandler {

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<StandardError> produtoNaoEncontrado(ProdutoNaoEncontradoException e, HttpServletRequest request) {
        return construirRespostaErro(e, request, NOT_FOUND);
    }

    @ExceptionHandler(ProdutoIntegridadeException.class)
    public ResponseEntity<StandardError> integridadeProduto(ProdutoIntegridadeException e, HttpServletRequest request) {
        return construirRespostaErro(e, request, BAD_REQUEST);
    }

    @ExceptionHandler(ProdutoSemEstoqueException.class)
    public ResponseEntity<StandardError> produtoSemEstoque(ProdutoSemEstoqueException e, HttpServletRequest request) {
        return construirRespostaErro(e, request, BAD_REQUEST);
    }

    private ResponseEntity<StandardError> construirRespostaErro(RuntimeException e, HttpServletRequest request, HttpStatus httpStatus) {
        StandardError err = new StandardError(httpStatus.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(httpStatus).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validacao", System.currentTimeMillis());

        for (FieldError x : e.getBindingResult().getFieldErrors()) {
            err.addError(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

}
