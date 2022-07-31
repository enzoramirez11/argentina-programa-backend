package com.argentinaprograma.portfolio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nro_telefono;
    private String ciudad;
    private String provincia;
    private String pais;
    private String usuario_id;
    private String linkedin;
    private String github;

    public Contacto(Long id, String nro_telefono, String ciudad, String provincia, String pais, String usuario_id, String linkedin, String github) {
        this.id = id;
        this.nro_telefono = nro_telefono;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.pais = pais;
        this.usuario_id = usuario_id;
        this.linkedin = linkedin;
        this.github = github;
    }

    

    public Contacto() {
    }
    
    
}
