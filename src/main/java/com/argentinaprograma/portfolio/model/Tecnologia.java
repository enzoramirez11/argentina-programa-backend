package com.argentinaprograma.portfolio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Tecnologia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private Long id;
    private String titulo;
    private String url_img;
    private String nivel;
    private Long usuario_id;

    public Tecnologia(Long id, String titulo, String url_img, String nivel, Long usuario_id) {
        this.id = id;
        this.titulo = titulo;
        this.url_img = url_img;
        this.nivel = nivel;
        this.usuario_id = usuario_id;
    }

    public Tecnologia() {
    }
    
    
}
