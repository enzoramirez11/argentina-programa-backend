/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.argentinaprograma.portfolio.model;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author enzor
 */
@Getter @Setter
public class Respuesta {
    
    public boolean estaRegistrado;
    public String mensaje;
    public String token;

    public Respuesta(boolean estaRegistrado, String mensaje, String token) {
        this.estaRegistrado = estaRegistrado;
        this.mensaje = mensaje;
        this.token = token;
    }

    public Respuesta() {
    }

    
    
    
    
}
