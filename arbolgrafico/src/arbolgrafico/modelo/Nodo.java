/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolgrafico.modelo;

/**
 *
 * @author ploks
 * @author Eliana jurado, Dennis David Arango, Juan Esteban Marin
 */
public class Nodo {
    
    private int dato;
    private Nodo izq, der;
    /**
     * 
     * @param dato recoge la información que permite formar el arbol
     * @param izq parametro para direccionar nodos de menor valor al actual
     * @param der parametro para direccionar nodos de mayor valor actual
     */
    public Nodo(int dato, Nodo izq, Nodo der) {
        this.dato = dato;
        this.izq = izq;
        this.der = der;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public Nodo getIzq() {
        return izq;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public Nodo getDer() {
        return der;
    }

    public void setDer(Nodo der) {
        this.der = der;
    } 

}
