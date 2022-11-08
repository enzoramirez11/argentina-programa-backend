package com.argentinaprograma.portfolio.repository;

import com.argentinaprograma.portfolio.model.Tecnologia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnologiaRepository extends JpaRepository <Tecnologia,Long> {
    
}
