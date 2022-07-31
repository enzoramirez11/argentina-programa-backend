package com.argentinaprograma.portfolio.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioForm extends Usuario {
    private String user_id;

    public UsuarioForm(String user_id, Long id, String email, String password) {
        super(id, email, password);
        this.user_id = user_id;
    }

    public UsuarioForm() {
    }
    
    
}
