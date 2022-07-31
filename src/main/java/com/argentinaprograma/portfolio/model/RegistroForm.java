package com.argentinaprograma.portfolio.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegistroForm {
    
    private String nombre;
    private String apellido;
    private String email;
    private String password;

    public RegistroForm(String nombre, String apellido, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }

    public RegistroForm() {
    }
    
    
}
