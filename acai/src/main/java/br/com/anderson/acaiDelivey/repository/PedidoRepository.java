package br.com.anderson.acaiDelivey.repository;

import br.com.anderson.acaiDelivey.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
}
