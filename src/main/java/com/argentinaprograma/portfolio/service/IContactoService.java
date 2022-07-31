package com.argentinaprograma.portfolio.service;

import com.argentinaprograma.portfolio.model.Contacto;
import java.util.List;

public interface IContactoService {
    
    public List<Contacto> getContactos();
    
    public void saveContacto(Contacto contacto);
    
    public void deleteContacto(Long id);
    
    public Contacto findContacto(Long id);
    
    
}
