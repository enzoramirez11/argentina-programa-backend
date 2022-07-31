
package com.argentinaprograma.portfolio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Persona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String apellido;
    private String sobre_mi;
    private String ocupacion;
    private String url_foto;
    private String usuario_id;

    public Persona(Long id, String nombre, String apellido, String sobre_mi, String ocupacion, String url_foto, String usuario_id) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sobre_mi = sobre_mi;
        this.ocupacion = ocupacion;
        this.url_foto = url_foto;
        this.usuario_id = usuario_id;
    }

    

    public Persona() {
    }
    
    
}
