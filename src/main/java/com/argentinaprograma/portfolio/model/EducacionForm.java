package com.argentinaprograma.portfolio.model;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EducacionForm extends Educacion {
    private String token;

    public EducacionForm(String token, Long id, String titulo, String institucion, String url_img, String descripcion, String fechaInicio, String fechaFin, String url_cert, Long usuario_id) {
        super(id, titulo, institucion, url_img, descripcion, fechaInicio, fechaFin, url_cert, usuario_id);
        this.token = token;
    }
    
    public EducacionForm() {
    }
    
    
}
