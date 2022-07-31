package com.argentinaprograma.portfolio.service;

import com.argentinaprograma.portfolio.model.Educacion;
import com.argentinaprograma.portfolio.repository.EducacionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducacionService implements IEducacionService {
    
    @Autowired
    private EducacionRepository eduRepository;

    @Override
    public List<Educacion> getEducacion() {
        List<Educacion> listaEducacion= eduRepository.findAll();
        return listaEducacion;
    }

    @Override
    public void saveEducacion(Educacion ed) {
        eduRepository.save(ed);
    }

    @Override
    public void deleteEducacion(Long id) {
        eduRepository.deleteById(id);
    }

    @Override
    public Educacion findEducacion(Long id) {
        Educacion edu = eduRepository.findById(id).orElse(null);
        return edu;
    }
    
    
    
}
