package com.argentinaprograma.portfolio.repository;

import com.argentinaprograma.portfolio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    
}
