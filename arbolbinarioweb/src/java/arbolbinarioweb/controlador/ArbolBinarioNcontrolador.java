/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinarioweb.controlador;

import arbolbinario.modelo.ArbolBinario;
import arbolbinario.modelo.Nodo;
import arbolbinarioweb.controlador.util.JsfUtil;
import dremali.modelo.ArbolN;
import dremali.modelo.NodoN;
import dremali.modelo.Prenda;
import dremali.modelo.excepciones.ArbolNException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

/**
 *
 * @author camil
 */
@Named(value = "arbolBinarioNcontrolador")
@Dependent
public class ArbolBinarioNcontrolador {
 private DefaultDiagramModel model;
        
        private ArbolN arbolN = new ArbolN();
        private Prenda prenda;
        private List<Prenda> lista;
        private boolean verPrenda;
        private String padre;
        private String texto;
        private ArbolBinario arbolFinal = new ArbolBinario();

    public DefaultDiagramModel getModel() {
        return model;
    }

    public void setModel(DefaultDiagramModel model) {
        this.model = model;
    }

    public ArbolN getArbolN() {
        return arbolN;
    }

    public void setArbolN(ArbolN arbolN) {
        this.arbolN = arbolN;
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }

    public List<Prenda> getLista() {
        return lista;
    }

    public void setLista(List<Prenda> lista) {
        this.lista = lista;
    }

    public boolean isVerPrenda() {
        return verPrenda;
    }

    public void setVerPrenda(boolean verPrenda) {
        this.verPrenda = verPrenda;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
     public void pintarArbolTerminados() {

        model= new DefaultDiagramModel();
        model.setMaxConnections(-1);
        model.setConnectionsDetachable(false);
        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:10}");
        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
        model.setDefaultConnector(connector);
        pintarArbolFinal(arbolFinal.getRaiz(), model, null, 30, 0);

    }

    private void pintarArbolFinal(Nodo reco, DefaultDiagramModel model, Element padre, int x, int y) {

        if (reco != null) {
            Element elementHijo = new Element(reco.getDato());

            elementHijo.setX(String.valueOf(x) + "em");
            elementHijo.setY(String.valueOf(y) + "em");

            if (padre != null) {
                elementHijo.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
                DotEndPoint conectorPadre = new DotEndPoint(EndPointAnchor.BOTTOM);
                padre.addEndPoint(conectorPadre);
                model.connect(new Connection(conectorPadre, elementHijo.getEndPoints().get(0)));

            }

            model.addElement(elementHijo);

            pintarArbolFinal(reco.getIzquierda(), model, elementHijo, x - 20, y + 5);
            pintarArbolFinal(reco.getDerecha(), model, elementHijo, x + 20, y + 5);
        }
    }   
     
        
    @PostConstruct
    private void inicializar() {
        arbolN = new ArbolN();
        texto= "Arbol n ario";
    pintarArbolN();
    }
    public ArbolBinarioNcontrolador() {
    }
        public void adicionarPrenda(){
        try {
        arbolN.adicionarNodo(prenda, padre);
        prenda = new Prenda();
        verPrenda = false;
        pintarArbolN();      
        } catch (ArbolNException ex){
        JsfUtil.addErrorMessage(ex.getMessage());
        }
    }
   
        public void pintarArbolN(){
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        model.setConnectionsDetachable(false);
        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:2}");
        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
        model.setDefaultConnector(connector);
        pintarArbolN(arbolN.getRaiz(), model, null, 42, 0);
        }
    
        private void pintarArbolN(NodoN reco, DefaultDiagramModel model, Element padre, int x, int y){
        
        if (reco != null) {
            Element elementHijo = new Element(reco.getDato().getIdPrenda() + " " + reco.getDato().getNombre() + " " + reco.getDato().getTalla() + "" + reco.getDato().getColor());

            elementHijo.setX(String.valueOf(x) + "em");
            elementHijo.setY(String.valueOf(y) + "em");

            if (padre != null) {
                elementHijo.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
                DotEndPoint conectorPadre = new DotEndPoint(EndPointAnchor.BOTTOM);
                padre.addEndPoint(conectorPadre);
                model.connect(new Connection(conectorPadre, elementHijo.getEndPoints().get(0)));

            }
            model.addElement(elementHijo);
            for(NodoN hijo:reco.getHijos()){
            pintarArbolN(hijo,model,elementHijo, x-10, y+5);
            }
        }
  }
        
       public void mostrarPrecios(){
       }
       public void buscarCategoria(){
       }
       public void eliminarCategoria(){
       }
       public void eliminarPrenda(){
       }
       public void cambiarCategoria(){
       }
       public void cambiarTalla(){
       }
       public void ordenarPorTalla(){
       }
       public void ordenarPorPrecio(){
       }
}
 

    
    
      
 