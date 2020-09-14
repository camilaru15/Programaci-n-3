/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboln.modelo;

import arbolbinario.modelo.excepciones.ArbolNException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author aula205c
 */
public class PruebaArbolN {
    public static void main(String... args)
    {
        ArbolN arbol= new ArbolN();
        
        Visitante visitante1= new Visitante("123", "Pedro Pérez");
        arbol.insertarHijo(arbol.getRaiz(), visitante1, null);        
        arbol.insertarHijo(arbol.getRaiz(), new Visitante("234", "Juanito Pérez"), visitante1);
        arbol.insertarHijo(arbol.getRaiz(), new Visitante("456", "Rosita Pérez"), visitante1);
        arbol.insertarHijo(arbol.getRaiz(), new Visitante("678", "Danielito Rojas"), 
                new Visitante("456", "Rosita Pérez"));
        
        try {
            JOptionPane.showMessageDialog(null, arbol.recorrerArbolN());
        } catch (ArbolNException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        
        
        
    }
    
    
}
