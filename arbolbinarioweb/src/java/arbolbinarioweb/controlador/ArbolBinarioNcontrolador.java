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
import javax.faces.context.FacesContext;
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

        private ArbolN arbolN = new ArbolN();
        private Prenda prenda = new Prenda();
        private List<Prenda> lista;
        private boolean verPrenda;
        private DefaultDiagramModel model;
        private String padre;
        private int dato;
        private String texto;
         private DefaultDiagramModel modelArbol2;
        private ArbolBinario arbolFinal = new ArbolBinario();    

    public DefaultDiagramModel getModelArbol2() {
        return modelArbol2;
    }

    public void setModelArbol2(DefaultDiagramModel modelArbol2) {
        this.modelArbol2 = modelArbol2;
    }
    
    
    public void pintarArbolTerminados() {

        modelArbol2 = new DefaultDiagramModel();
        modelArbol2.setMaxConnections(-1);
        modelArbol2.setConnectionsDetachable(false);
        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:10}");
        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
        modelArbol2.setDefaultConnector(connector);
        pintarArbolTerminados(arbolFinal.getRaiz(), modelArbol2, null, 30, 0);

    }

    private void pintarArbolTerminados(Nodo reco, DefaultDiagramModel model, Element padre, int x, int y) {

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

            pintarArbolTerminados(reco.getIzquierda(), model, elementHijo, x - 20, y + 5);
            pintarArbolTerminados(reco.getDerecha(), model, elementHijo, x + 20, y + 5);
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
    
    public ArbolN getArbolN() {
        return arbolN;
    }

    public void setArbolN(ArbolN arbolN) {
        this.arbolN = arbolN;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
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
   
    public DefaultDiagramModel getModel() {
        return model;
    }

    public void setModel(DefaultDiagramModel model) {
        this.model = model;
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
            Element elementHijo = new Element(reco.getDato().getIdPrenda() + " " + reco.getDato().getNombre()+""+ reco.getDato().getPrecio());

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
        
    public void guardarPrenda() {
        try {
            arbolN.adicionarNodo(prenda, padre);
            prenda = new Prenda();
            verPrenda = false;
            pintarArbolN();
            //arbol.sumarPrecios();
        } catch (ArbolNException ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
        }
    }

    public void onClickRight() {
        String id = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("elementId");

        prendaSeleccionado = id.replaceAll("frmPrendaAbb:diagrama-", "");

    }
    
    private String prendaSeleccionado = "";

       public void buscarPrenda(){
        arbolN.buscar(dato);
        pintarArbolN();
       }      

       public void borrarPrenda(){
         arbolN.borrar(dato);
         pintarArbolN();
       }      
       public void cambiarPrenda(){
        arbolN.cambiar();
          pintarArbolN();
       }
       
       public void podar(){
           arbolN.podar();
           pintarArbolN();
        }
        
       public void ordenarPorTalla(){
       }
       public void ordenarPorPrecio(){
       }
}
 

    
    
      
 