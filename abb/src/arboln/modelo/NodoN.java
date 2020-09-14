/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboln.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aula205c
 */
public class NodoN {
    private Visitante dato;
    private List<NodoN> hijos;

    public NodoN(Visitante dato) {
        this.dato = dato;
        this.hijos= new ArrayList<>();
    }

    public Visitante getDato() {
        return dato;
    }

    public void setDato(Visitante dato) {
        this.dato = dato;
    }

    public List<NodoN> getHijos() {
        return hijos;
    }

    public void setHijos(List<NodoN> hijos) {
        this.hijos = hijos;
    }
    
    public int obtenerNumeroHijos(){
        return hijos.size();
    }
            
    public void aumentarHijo(NodoN hijo)
    {
        hijos.add(hijo);
    }
    
}
