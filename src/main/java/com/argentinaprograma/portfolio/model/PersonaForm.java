/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaprograma.portfolio.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PersonaForm extends Persona {
    
    private String token;

    public PersonaForm(String token, Long id, String nombre, String apellido, String sobre_mi, String ocupacion, String url_foto, String usuario_id) {
        super(id, nombre, apellido, sobre_mi, ocupacion, url_foto, usuario_id);
        this.token = token;
    }

    public PersonaForm() {
    }
    
    
}
