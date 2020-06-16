package br.com.anderson.acaiDelivey.repository;

import br.com.anderson.acaiDelivey.model.Complemento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComplementoRepository extends JpaRepository<Complemento, Long> {
    @Query("SELECT c FROM complemento c WHERE c.descricao = :descricaoComplemento")
    List<Complemento> findByDescricao(@Param("descricaoComplemento") String complemento);
}
