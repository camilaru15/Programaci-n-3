/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinarioweb.controlador;

import arbolbinarioweb.controlador.util.JsfUtil;
import dremali.modelo.ArbolN;
import dremali.modelo.NodoN;
import dremali.modelo.Prenda;
import dremali.modelo.excepciones.ArbolNException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;

/**
 *
 * @author Alison y Camila 
 */
@Named(value = "arbolBinarioNcontrolador")
@SessionScoped
public class ArbolBinarioNcontrolador implements Serializable{
        private DefaultDiagramModel model;
        
        private ArbolN arbolN = new ArbolN();
        private Prenda dato;
        private boolean verInOrden = false;
        
        private String datobuscar;
        private int datoPromediar;
        private int datoSumar;
        private String textoEnviar;

    public ArbolN getArbolN() {
        return arbolN;
    }

    public void setArbolN(ArbolN arbolN) {
        this.arbolN = arbolN;
    }

    public Prenda getDato() {
        return dato;
    }

    public void setDato(Prenda dato) {
        this.dato = dato;
    }

    public boolean isVerInOrden() {
        return verInOrden;
    }

    public void setVerInOrden(boolean verInOrden) {
        this.verInOrden = verInOrden;
    }

    public String getDatobuscar() {
        return datobuscar;
    }

    public void setDatobuscar(String datobuscar) {
        this.datobuscar = datobuscar;
    }

    public int getDatoPromediar() {
        return datoPromediar;
    }

    public void setDatoPromediar(int datoPromediar) {
        this.datoPromediar = datoPromediar;
    }

    public int getDatoSumar() {
        return datoSumar;
    }

    public void setDatoSumar(int datoSumar) {
        this.datoSumar = datoSumar;
    }

    public String getTextoEnviar() {
        return textoEnviar;
    }

    public void setTextoEnviar(String textoEnviar) {
        this.textoEnviar = textoEnviar;
    }
        
    public ArbolBinarioNcontrolador(){
    
    }
    
    public void adicionarNodo(){
    try{
        arbolN.adicionarNodo(dato , arbolN.getRaiz(), datobuscar);
        JsfUtil.addSuccessMessage("El dato ha sido adicionado");
        dato = new Prenda();
        pintarArbolN();
        }catch(ArbolNException ex){
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
        connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:6}");
        connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");
        model.setDefaultConnector(connector);
        pintarArbolN(arbolN.getRaiz(), model, null, 50, 0);
    }
    
    private void pintarArbolN(NodoN reco, DefaultDiagramModel model, Element padre, int x, int y) {

        if (reco != null) {
            Element elementHijo = new Element(reco.getDato()+" G:"+reco.obtenerGradoNodo() +" H:"+
                    reco.obtenerAlturaNodo());

            elementHijo.setX(String.valueOf(x) + "em");
            elementHijo.setY(String.valueOf(y) + "em");

            if (padre != null) {
                elementHijo.addEndPoint(new DotEndPoint(EndPointAnchor.TOP));
                DotEndPoint conectorPadre = new DotEndPoint(EndPointAnchor.BOTTOM);
                padre.addEndPoint(conectorPadre);
                model.connect(new Connection(conectorPadre, elementHijo.getEndPoints().get(0)));

            }
            model.addElement(elementHijo);

           /* pintarArbolN(reco.getDato(), model, elementHijo, x - 10, y + 5);*/
        }
    }
    
}
