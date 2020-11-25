package com.albano.controleestoque.resources;

import com.albano.controleestoque.dtos.RelatorioLucroProdutoDTO;
import com.albano.controleestoque.dtos.RelatorioProdutoTipoDTO;
import com.albano.controleestoque.enums.TipoProduto;
import com.albano.controleestoque.services.RelatorioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api/relatorio")
public class RelatorioResource {

    @Autowired
    private RelatorioService relatorioService;

    @ApiOperation("Relatorio de produtos por tipo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna informacoes de produtos filtrados por tipo"),
            @ApiResponse(code = 400, message = "Problema com algum atributo passado"),
            @ApiResponse(code = 500, message = "Ocorreu algum erro nos servidor"),
    })
    @RequestMapping(value = "/consulta-tipo-produto/{tipoProduto}", method = GET)
    public ResponseEntity consultarPorTipoProduto(@PathVariable TipoProduto tipoProduto) {
        RelatorioProdutoTipoDTO dto = relatorioService.relatorioPorTipoProduto(tipoProduto);
        return ResponseEntity.ok().body(dto);
    }

    @ApiOperation("Relatorio de lucro por produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna informacoes de lucro por produto"),
            @ApiResponse(code = 400, message = "Problema com algum atributo passado"),
            @ApiResponse(code = 500, message = "Ocorreu algum erro nos servidor"),
    })
    @RequestMapping(value = "/produto-lucro/{idProduto}", method = GET)
    public ResponseEntity consultarLucroProduto(@PathVariable Integer idProduto) {
        RelatorioLucroProdutoDTO dto = relatorioService.relatorioLucroPorProduto(idProduto);
        return ResponseEntity.ok().body(dto);
    }
}
