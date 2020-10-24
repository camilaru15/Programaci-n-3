/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinario.modeloespecial;

import java.io.Serializable;

/**
 *
 * @author camil
 */
public class Dato implements Serializable{
    private int numero;
    private int suma;
    

    public Dato(int numero) {
        this.numero = numero;
        this.suma = numero;
    }

   

    

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getSuma() {
        return suma;
    }

    public void setSuma(int suma) {
        this.suma = suma;
    }
    
    
}
