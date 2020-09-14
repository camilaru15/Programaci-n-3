/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinario.modelo;

import arbolbinario.modelo.excepciones.ArbolBinarioException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author carloaiza
 */
public class ArbolBinario {

    private Nodo raiz;
    String[] niveles;
    int altura;

    //public void adicionarNodo()
    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public String[] getNiveles() {
        return niveles;
    }

    public void setNiveles(String[] niveles) {
        this.niveles = niveles;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    

    public void isLleno() throws ArbolBinarioException {
        if (raiz == null) {
            throw new ArbolBinarioException("El árbol está vacío");
        }
    }

    public void adicionarNodo(int dato, Nodo ubicacion) throws ArbolBinarioException {
        if (raiz == null) {
            raiz = new Nodo(dato);

        } else {
            if (dato < ubicacion.getDato()) {
                if (ubicacion.getIzquierda() == null) {
                    ubicacion.setIzquierda(new Nodo(dato));
                } else {
                    adicionarNodo(dato, ubicacion.getIzquierda());
                }
            } else if (dato > ubicacion.getDato()) {
                if (ubicacion.getDerecha() == null) {
                    ubicacion.setDerecha(new Nodo(dato));
                } else {
                    adicionarNodo(dato, ubicacion.getDerecha());
                }
            } else {
                throw new ArbolBinarioException("No se puede repetir");
            }
        }
    }
    
     
    public ArrayList inOrden() throws ArbolBinarioException{
        isLleno();
        ArrayList l=new ArrayList();
        inOrden(raiz,l);
        return l;
    }

    private void inOrden(Nodo reco,ArrayList l) {
        if (reco != null) {
            inOrden(reco.getIzquierda(),l);
            l.add(reco.getDato() + " ");
            inOrden(reco.getDerecha(),l);
        }
    }
    

    /**
     * Método que retorna un arreglo de enteros con los datos de recorrer el árbol en preorden
     * @return Arraylist
     * @throws ArbolBinarioException 
     */
    
    //imprimir lista de números en preorden
    public ArrayList preOrden() throws ArbolBinarioException{
        ArrayList l=new ArrayList();
        if (raiz != null) 
        {
            preOrden(raiz, l);
        }
        return l;
    } 
    
    /**
     * Método recursivo que recorre el árbol en preorden
     * @param temp Ayudante que toma referencia en un nodo
     * @param listado acumulador para registrar el dato del nodo visitado
     */
    
    private void preOrden(Nodo temp, ArrayList listado)
    {
        //Condición que garantiza que el método finalice
        if(temp!=null)
        {
            listado.add(temp.getDato());
            preOrden(temp.getIzquierda(), listado);
            preOrden(temp.getDerecha(), listado);
        }
    }
    
    /**
     * Método recursivo que recorre el árbol en preorden
     * @param temp Ayudante que toma referencia en un nodo
     * @param listado acumulador para registrar el dato del nodo visitado
     */
    private void postOrden(Nodo temp, ArrayList listado) {
        if (temp != null) {
            postOrden(temp.getIzquierda(),listado);
            postOrden(temp.getDerecha(),listado);
            listado.add(temp.getDato() + " ");
        }
    }
    
    //imprimir post orden
    public ArrayList postOrden() throws ArbolBinarioException {
        ArrayList l=new ArrayList();
        if(raiz !=null)
        {
            postOrden(raiz,l);
        }
        return l;
    }
    
    public ArrayList impNiveles() {
        ArrayList l=new ArrayList();
        impNiveles(raiz, 1,l);
        return l;
    }
    private void impNiveles(Nodo nodo, int nivel, ArrayList l) {
        if (nodo != null) {
            impNiveles(nodo.getIzquierda(), nivel + 1, l);
            l.add(nodo.getDato() + " Nivel: (" + nivel + ") ");
            impNiveles(nodo.getDerecha(), nivel + 1, l);
        }
    }
    
    public ArrayList imprimirNivel() {
        niveles = new String[altura + 1];
        ArrayList l=new ArrayList();
        imprimirNivel(raiz, 0);
        for (int i = 0; i < niveles.length; i++) {
            l.add(niveles[i] + " ");
            //System.out.println(niveles[i] + " ");
        }
        return l;
    }
      public void imprimirNivel(Nodo nodo, int nivel2) {
        if (nodo != null) {
            niveles[nivel2] = nodo.getDato() + ", " + ((niveles[nivel2] != null) ? niveles[nivel2] : "");
            imprimirNivel(nodo.getDerecha(), nivel2 + 1);
            imprimirNivel(nodo.getIzquierda(), nivel2 + 1);
        }
    }
      
       public ArrayList<String> obtenerHojas() {
        ArrayList l = new ArrayList();
        getHojas(this.raiz, l);
        return (l);
    }

       private void getHojas(Nodo nodo, ArrayList l) {
        if (nodo != null) {
            if (this.esHoja(nodo)) {
                l.add(nodo.getDato());
            }
            getHojas(nodo.getIzquierda(), l);
            getHojas(nodo.getDerecha(), l);
        }
    }
    protected boolean esHoja(Nodo nodo) {
        return (nodo != null && nodo.getIzquierda() == null && nodo.getDerecha() == null);
    }
   
    public void llenarArbol(String datos) throws ArbolBinarioException
    {
        String[] arrayDatos= datos.split(",");
        for(String cadena: arrayDatos)
        {
            adicionarNodo(Integer.parseInt(cadena), raiz);
        }
        
    }
     
    public boolean buscar(int x) {
        return (buscar(this.raiz, x));
       
    }

    private boolean buscar(Nodo nodo, int x) {
        if (nodo == null) {
            return (false);
        }
        int compara = ((Comparable) nodo.getDato()).compareTo(x);
        if (compara > 0) {
            return (buscar(nodo.getIzquierda(), x));
        } else if (compara < 0) {
            return (buscar(nodo.getDerecha(), x));
        } else {
            return (true);
        }
     
    }
    public int padre(int info) {
        if (info == 0 || this.raiz == null) {
            return 0;
        }
        Nodo x = padre(this.raiz, info);
        if (x == null) {
            return 0;
        }
        return (x.getDato());
    }

    private Nodo padre(Nodo x, int info) {
        if (x == null) {
            return null;
        }
        if ((x.getIzquierda() != null && x.getIzquierda().getDato()==(info)) || (x.getDerecha() != null && x.getDerecha().getDato()==(info))) {
            return (x);
        }
        Nodo y = padre(x.getIzquierda(), info);
        if (y == null) {
            return (padre(x.getDerecha(), info));
        } else {
            return (y);
        }
    }
    
     public String darPadre(Integer hijo) {
        //se obtiene la raiz y el dato para identificar si la raiz tiene un padre
        if (this.getRaiz().getDato() == (hijo)) {
            //si no tiene padre mostrará el mensaje de "La raiz no tiene padre"
            return ("La raiz no tiene padre");
        }
        //para buscar el padre de un hijo se llaman al padre con todos los datos de hijo
        Integer padre = this.padre(hijo);
        // Si se ingresa un dato que no se encuentra en padre
        if (padre == null) {
            //se mostrará el mensaje "No existe el dato"
            return ("No existe el Dato: " + hijo.toString());
        }
        //Si el dato si existe, el sistema mostrará quien es el padre del hijo(dato) dado 
        return ("El padre de: " + hijo + "\n es : " + padre.toString());
    }
     
     //Borrar el que sea
    public int borrar(int x) {
        if (!this.buscar(x)) {
            return 0;
        }

        Nodo z = borrar(this.raiz, x);
        this.setRaiz(z);
        return x;

    }

     //buscar min
    private Nodo buscarMin(Nodo nodo) {
        for (; nodo.getIzquierda() != null; nodo = nodo.getIzquierda());
        return (nodo);
    }
    
    private Nodo borrar(Nodo nodo, int x) {
        if (nodo == null) {
            return null;//<--Dato no encontrado		
        }
        int compara = ((Comparable) nodo.getDato()).compareTo(x);
        if (compara > 0) {
            nodo.setIzquierda(borrar(nodo.getIzquierda(), x));
        } else if (compara < 0) {
            nodo.setDerecha(borrar(nodo.getDerecha(), x));
        } else {
            System.out.println("Encontro el dato:" + x);
            if (nodo.getIzquierda() != null && nodo.getDerecha() != null) {
                /*
                 *	Buscar el menor de los derechos y lo intercambia por el dato
                 *	que desea borrar. La idea del algoritmo es que el dato a borrar 
                 *	se coloque en una hoja o en un nodo que no tenga una de sus ramas.
                 **/
                Nodo cambiar = buscarMin(nodo.getDerecha());
                int aux = cambiar.getDato();
                cambiar.setDato(nodo.getDato());
                nodo.setDato(aux);
                nodo.setDerecha(borrar(nodo.getDerecha(), x));
                System.out.println("2 ramas");
            } else {
                nodo = (nodo.getIzquierda() != null) ? nodo.getIzquierda() : nodo.getDerecha();
                System.out.println("Entro cuando le faltan ramas ");
            }
        }
        return nodo;
    }
    
    //eliminar hojas
     public void podar() {
        podar(this.raiz);
    }

    private void podar(Nodo x) {
        if (x == null) {
            return;
        }
        if (this.esHoja(x.getIzquierda())) {
            x.setIzquierda(null);
        }
        if (this.esHoja(x.getDerecha())) {
            x.setDerecha(null);
        }
        podar(x.getIzquierda());
        podar(x.getDerecha());
    }
    
    //borrar mayor
    public String borrarMayor() {
        Nodo reco=raiz.getIzquierda();
        if (raiz != null) {
            if (raiz.getDerecha() == null) {
                raiz = raiz.getIzquierda();
            } else {
                Nodo anterior = raiz;
                reco = raiz.getDerecha();
                while (reco.getDerecha() != null) {
                    anterior = reco;
                    reco = reco.getDerecha();
                }
                
                anterior.setDerecha(reco.getIzquierda());
            }
        }
        return ("Valor eliminado: " + reco.getDato());
    }
    
    //Borrar menor
    public String borrarMenor() {
        Nodo reco = raiz.getIzquierda();
        if (raiz != null) {
            if (raiz.getIzquierda() == null) {
                raiz = raiz.getDerecha();
            } else {
                Nodo anterior = raiz;
                reco = raiz.getIzquierda();
                while (reco.getIzquierda() != null) {
                    anterior = reco;
                    reco = reco.getIzquierda();
                }
                
                anterior.setIzquierda(reco.getDerecha());
            }
        }
        return ("Valor eliminado: " + reco.getDato());
    }
     //cambiar datos
      public boolean cambiar() {
            cambiar(raiz, 1);
            //System.out.println();
            return true;
    }

    private void cambiar(Nodo reco, int nivel) {
        if (reco != null) {
            reco.setDato(reco.getDato() * 3);
            cambiar(reco.getIzquierda(), nivel + 1);
            //System.out.print(reco.getDato() + " Nivel: (" + nivel + ") ,");
            cambiar(reco.getDerecha(), nivel + 1);
        }
    }
    
}
