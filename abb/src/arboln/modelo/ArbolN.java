/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arboln.modelo;

import arbolbinario.modelo.excepciones.ArbolNException;

/**
 *
 * @author aula205c
 */
public class ArbolN {
    private NodoN raiz;

    public ArbolN() {
    }

    public NodoN getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoN raiz) {
        this.raiz = raiz;
    }
    
    public void insertarHijo(NodoN pivote, Visitante hijo, Visitante padre){
        if(raiz==null)
        {
            raiz= new NodoN(hijo);
        }    
        else
        {
            if(pivote.getDato().getIdentificacion().equals(padre.getIdentificacion()))
            {
                pivote.aumentarHijo(new NodoN(hijo));
            }
            else
            {
                for(NodoN hijoPivote: pivote.getHijos())
                {
                    if(hijoPivote.getDato().getIdentificacion().equals(padre.getIdentificacion()))
                    {
                        hijoPivote.aumentarHijo(new NodoN(hijo));
                        break;
                    }
                    else
                    {
                        insertarHijo(hijoPivote, hijo, padre);
                    }    
                }    
            }
        }    
    }
    
    public String recorrerArbolN() throws ArbolNException{
        if(raiz!=null)
        {
            String listado="";
            listado += recorrerArbolN(raiz, listado);
            return listado;
        }   
        throw new ArbolNException("El árbol está vacío");
    }
    
    public String recorrerArbolN(NodoN pivote, String listado)
    {
        listado += "\n"+pivote.getDato();
        for(NodoN hijo: pivote.getHijos())
        {
            listado = recorrerArbolN(hijo, listado);
        }   
        return listado;
    }
    
}
