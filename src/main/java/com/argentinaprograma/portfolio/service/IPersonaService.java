package com.argentinaprograma.portfolio.service;

import com.argentinaprograma.portfolio.model.Persona;
import java.util.List;


public interface IPersonaService {
    //metodo para traer las personas
    public List<Persona> getPersonas();
    //metodo para crear una persona 
    public void savePersona(Persona perso);
    //metodo para borrar una persona
    public void deletePersona(Long id);
    //metodo para encontrar una persona
    public Persona findPersona(Long id);
}
