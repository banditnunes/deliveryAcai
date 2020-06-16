package br.com.anderson.acaiDelivey.repository;

import br.com.anderson.acaiDelivey.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {

}
