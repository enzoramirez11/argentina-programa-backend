package com.argentinaprograma.portfolio.service;

import com.argentinaprograma.portfolio.model.Tecnologia;
import com.argentinaprograma.portfolio.repository.TecnologiaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TecnologiaService implements ITecnologiaService {
    @Autowired
    private TecnologiaRepository tecnologiaRepository;
    
    @Override
    public List<Tecnologia> getTecnologia() {
        return tecnologiaRepository.findAll();
    }

    @Override
    public void saveTecnologia(Tecnologia tecno) {
        tecnologiaRepository.save(tecno);
    }

    @Override
    public void deleteTecnologia(Long id) {
        tecnologiaRepository.deleteById(id);
    }

    @Override
    public Tecnologia findTecnologia(Long id) {
        return tecnologiaRepository.findById(id).orElse(null);
    }
    
}
