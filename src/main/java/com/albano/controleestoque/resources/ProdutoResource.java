package com.albano.controleestoque.resources;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produto")
public class ProdutoResource {

    @ApiOperation("Consulta um produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o informacoes de um produto"),
            @ApiResponse(code = 400, message = "Problema com algum atributo passado"),
            @ApiResponse(code = 500, message = "Ocorreu algum erro nos servidor"),
    })
    @RequestMapping(value = "/consultar/{id}", method = GET)
    public ResponseEntity consultarProduto(@PathVariable Integer idProduto) {

        return null;
    }

}
