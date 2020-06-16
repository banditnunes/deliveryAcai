package br.com.anderson.acaiDelivey.repository;


import br.com.anderson.acaiDelivey.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    public Optional<Usuario>  findByEmail(String email);

}
