package com.argentinaprograma.portfolio.service;

import com.argentinaprograma.portfolio.model.Tecnologia;
import java.util.List;


public interface ITecnologiaService {
    
    public List<Tecnologia> getTecnologia();
    public void saveTecnologia(Tecnologia tecno);
    public void deleteTecnologia(Long id);
    public Tecnologia findTecnologia(Long id);
}
