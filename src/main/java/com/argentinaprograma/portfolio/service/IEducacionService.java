package com.argentinaprograma.portfolio.service;

import com.argentinaprograma.portfolio.model.Educacion;
import java.util.List;


public interface IEducacionService {
    //metodo para traer las personas
    public List<Educacion> getEducacion();
    //metodo para crear una persona 
    public void saveEducacion(Educacion ed);
    //metodo para borrar una persona
    public void deleteEducacion(Long id);
    //metodo para encontrar una persona
    public Educacion findEducacion(Long id);
}
