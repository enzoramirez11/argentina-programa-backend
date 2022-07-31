
package com.argentinaprograma.portfolio.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContactoForm extends Contacto {
    
    private String user_id;

    public ContactoForm(String user_id, Long id, String nro_telefono, String ciudad, String provincia, String pais, String usuario_id, String linkedin, String github) {
        super(id, nro_telefono, ciudad, provincia, pais, usuario_id, linkedin, github);
        this.user_id = user_id;
    }

    public ContactoForm() {
    }
    
}
