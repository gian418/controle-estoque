package com.albano.controleestoque.resources;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;

import com.albano.controleestoque.dtos.AtualizarProdutoDTO;
import com.albano.controleestoque.dtos.NovoProdutoDTO;
import com.albano.controleestoque.dtos.ProdutoDTO;
import com.albano.controleestoque.models.Produto;
import com.albano.controleestoque.services.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @ApiOperation("Consultar um produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna informacoes de um produto"),
            @ApiResponse(code = 400, message = "Problema com algum atributo passado"),
            @ApiResponse(code = 500, message = "Ocorreu algum erro nos servidor"),
    })
    @RequestMapping(value = "/{idProduto}", method = GET)
    public ResponseEntity consultar(@PathVariable Integer idProduto) {
        Produto produto = produtoService.buscarPorId(idProduto);
        return ResponseEntity.ok().body(ProdutoDTO.from(produto));
    }

    @ApiOperation("Buscar todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna informacoes de todos os produtos"),
            @ApiResponse(code = 400, message = "Problema com algum atributo passado"),
            @ApiResponse(code = 500, message = "Ocorreu algum erro nos servidor"),
    })
    @RequestMapping(method = GET)
    public ResponseEntity buscarTodos() {
        List<ProdutoDTO> dtoList = produtoService.buscarTodos();
        return ResponseEntity.ok().body(dtoList);
    }


    @ApiOperation("Inserir um produto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Produto inserido"),
            @ApiResponse(code = 400, message = "Problema com algum atributo passado"),
            @ApiResponse(code = 500, message = "Ocorreu algum erro nos servidor"),
    })
    @RequestMapping(method = POST)
    public ResponseEntity inserir(@RequestBody @Valid NovoProdutoDTO dto) {
        Produto produto = produtoService.salvar(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idProduto}")
                .buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(produto);
    }

    @ApiOperation("Atualizar um produto")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Produto atualizado"),
            @ApiResponse(code = 400, message = "Problema com algum atributo passado"),
            @ApiResponse(code = 500, message = "Ocorreu algum erro nos servidor"),
    })
    @RequestMapping(value = "/{idProduto}", method = PUT)
    public ResponseEntity atualizar(@PathVariable Integer idProduto, @RequestBody @Valid AtualizarProdutoDTO dto) {
        produtoService.atualizar(idProduto, dto);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Deletar um produto")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Produto deletado"),
            @ApiResponse(code = 400, message = "Problema com algum atributo passado"),
            @ApiResponse(code = 500, message = "Ocorreu algum erro nos servidor"),
    })
    @RequestMapping(value = "/{idProduto}", method = DELETE)
    public ResponseEntity delete(@PathVariable Integer idProduto) {
        produtoService.deletar(idProduto);
        return ResponseEntity.noContent().build();
    }

}
