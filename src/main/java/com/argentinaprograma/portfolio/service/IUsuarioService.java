/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.argentinaprograma.portfolio.service;
import com.argentinaprograma.portfolio.model.Usuario;
import java.util.List;

/**
 *
 * @author enzor
 */
public interface IUsuarioService {
    //metodo para traer las personas
    public List<Usuario> getUsuarios();
    //metodo para crear una persona 
    public void saveUsuario(Usuario user);
    //metodo para borrar una persona
    public void deleteUsuario(Long id);
    //metodo para encontrar una persona
    public Usuario findUsuario(Long id);
}
