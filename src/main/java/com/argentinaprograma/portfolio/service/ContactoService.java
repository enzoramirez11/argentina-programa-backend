package com.argentinaprograma.portfolio.service;

import com.argentinaprograma.portfolio.model.Contacto;
import com.argentinaprograma.portfolio.repository.ContactoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactoService implements IContactoService {
    @Autowired
    private ContactoRepository contactoRepository;
    

    @Override
    public List<Contacto> getContactos() {
        return contactoRepository.findAll();
    }

    @Override
    public void saveContacto(Contacto contacto) {
        contactoRepository.save(contacto);
    }

    @Override
    public void deleteContacto(Long id) {
        contactoRepository.deleteById(id);
    }

    @Override
    public Contacto findContacto(Long id) {
        return contactoRepository.findById(id).orElse(null);
    }
    
}
