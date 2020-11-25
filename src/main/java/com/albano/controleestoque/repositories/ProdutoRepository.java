package com.albano.controleestoque.repositories;

import com.albano.controleestoque.enums.TipoProduto;
import com.albano.controleestoque.models.Produto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {

    Optional<Produto> findById(Integer id);
    List<Produto> findAllByTipo(TipoProduto tipo);
}
