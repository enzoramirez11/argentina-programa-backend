
package com.argentinaprograma.portfolio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Educacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String titulo;    
    private String institucion;
    private String url_img;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
    private String url_cert;
    private Long usuario_id;

    public Educacion(Long id, String titulo, String institucion, String url_img, String descripcion, String fechaInicio, String fechaFin, String url_cert, Long usuario_id) {
        this.id = id;
        this.titulo = titulo;
        this.institucion = institucion;
        this.url_img = url_img;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.url_cert = url_cert;
        this.usuario_id = usuario_id;
    }

    

    

    public Educacion() {
    }
    
    
}
