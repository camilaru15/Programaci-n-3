/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinario.modeloespecial;

import arbolbinario.modelo.excepciones.ArbolBinarioException;
import java.io.Serializable;

/**
 *
 * @author carloaiza
 */
public class ArbolBinario implements Serializable{
    private Nodo raiz;
    
     public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

     public void adicionarNodo(Dato dato, Nodo ubicacion) throws ArbolBinarioException {
        if (raiz == null) {
            raiz = new Nodo(dato);

        } else {
            if (dato.getNumero() < ubicacion.getDato().getNumero()) {
                if (ubicacion.getIzquierda() == null) {
                    ubicacion.setIzquierda(new Nodo(dato));
                } else {
                    adicionarNodo(dato, ubicacion.getIzquierda());
                }
            } else if (dato.getNumero() > ubicacion.getDato().getNumero()) {
                if (ubicacion.getDerecha() == null) {
                    ubicacion.setDerecha(new Nodo(dato));
                } else {
                    adicionarNodo(dato, ubicacion.getDerecha());
                }
            } else {
                throw new ArbolBinarioException("No se puede repetir");
            }
            ubicacion.calcularSuma();
        }
    }

    
    
}