package com.argentinaprograma.portfolio.repository;

import com.argentinaprograma.portfolio.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository <Contacto, Long> {
    
}
