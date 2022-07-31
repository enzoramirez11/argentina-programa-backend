package com.argentinaprograma.portfolio.repository;

import com.argentinaprograma.portfolio.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository <Persona, Long> {
    
}
