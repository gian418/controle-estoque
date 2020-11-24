package com.albano.controleestoque.resources;

import com.albano.controleestoque.dtos.MovimentoEstoqueDTO;
import com.albano.controleestoque.services.MovimentoEstoqueService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/movimento-estoque")
public class MovimentoEstoqueResource {

    @Autowired
    private MovimentoEstoqueService movimentoEstoqueService;

    @ApiOperation("Movimentar estoque do produto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Movimento de estoque realizado"),
            @ApiResponse(code = 400, message = "Problema com algum atributo passado"),
            @ApiResponse(code = 500, message = "Ocorreu algum erro nos servidor"),
    })
    @RequestMapping(method = POST)
    public ResponseEntity movimentar(@RequestBody @Valid MovimentoEstoqueDTO dto) {
        movimentoEstoqueService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
