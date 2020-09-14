/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboln.modelo;

import java.io.Serializable;

/**
 *
 * @author aula205c
 */
public class Visitante implements  Serializable{
    private String identificacion;
    private String nombre;

    public Visitante() {
    }

    public Visitante(String identificacion, String nombre) {
        this.identificacion = identificacion;
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Visitante{" + "identificacion=" + identificacion + ", nombre=" + nombre + '}';
    }
    
    
    
    
}
