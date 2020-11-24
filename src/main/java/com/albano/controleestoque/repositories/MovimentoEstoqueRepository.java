package com.albano.controleestoque.repositories;

import com.albano.controleestoque.models.MovimentoEstoque;
import org.springframework.data.repository.CrudRepository;

public interface MovimentoEstoqueRepository extends CrudRepository<MovimentoEstoque, Integer> {
}
