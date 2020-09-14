
package arbolgrafico.controlador;

import arbolgrafico.modelo.Arbol;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author ploks
 * @author Eliana jurado, Dennis David Arango, Juan Esteban Marin
 */
public class SimuladorArbolBinario {

    Arbol miArbol = new Arbol();

    public SimuladorArbolBinario() {
    }
    
    //Insertar dato
    public boolean insertar(Integer dato) {
        return (this.miArbol.agregar(dato));
    }
    
    public String borrar(Integer dato) {
        Integer x = this.miArbol.borrar(dato);
        //si el dato no existe mostrar este mensaje
        if (x == null) {
            return ("No existe el dato en el arbol");
        }
        // si el dato exite mostrar "Borrado el dato" junto con el numero del dato
        return ("Borrado el dato: " + x.toString());
    }

    //proceso lógico de preorden
    public String preOrden() {
        ArrayList it = this.miArbol.preOrden();
        return (recorrido(it, "Recorrido PreOrden"));
    }
    //proceso lógico de inorden
    public String inOrden() {
        ArrayList it = this.miArbol.inOrden();
        return (recorrido(it, "Recorrido InOrden"));
    }
    //proceso lógico de Posorden
    public String posOrden() {
        ArrayList it = this.miArbol.postOrden();
        return (recorrido(it, "Recorrido PosOrden"));
    } 
    //proceso lógico donde se muestran por niveles los datos
    public String imprimirPorNiveles() {
        ArrayList it = this.miArbol.impNiveles();
        return (recorrido(it, "Imprimir Por niveles"));
    }
    //proceso lógico donde se muestran las hojas del arbol 
    public String darHojas() {
        ArrayList it = this.miArbol.getHojas();
        return (recorrido(it, "Hojas del Arbol"));
    }
    /**
     * 
     * @param hijo es quien se encuentra debajo del padre, parametro que indica ser menor al parametro padre
     * @return darPadre: se obtiene el padre
     */
    public String darPadre(Integer hijo) {
        //se obtiene la raiz y el dato para identificar si la raiz tiene un padre
        if (this.miArbol.getRaiz().getDato() == (hijo)) {
            //si no tiene padre mostrará el mensaje de "La raiz no tiene padre"
            return ("La raiz no tiene padre");
        }
        //para buscar el padre de un hijo se llaman al padre con todos los datos de hijo
        Integer padre = this.miArbol.padre(hijo);
        // Si se ingresa un dato que no se encuentra en padre
        if (padre == null) {
            //se mostrará el mensaje "No existe el dato"
            return ("No existe el Dato: " + hijo.toString());
        }
        //Si el dato si existe, el sistema mostrará quien es el padre del hijo(dato) dado 
        return ("El padre de: " + hijo + "\n es : " + padre.toString());
    }
    //para buscar el asrbol
    public String esta(Integer dato) {
        //se ingresa el dato y muestra el mensaje "Si se encuentra en el arbol" si exite y si no "No se encuentra en el arbol"
        boolean siEsta = this.miArbol.buscar(dato);
        String r = "El dato:" + dato.toString() + "\n";
        r += siEsta ? "Si se encuentra en el arbol" : "No se encuentra en el arbol";
        return (r);
    }

    /**
     * 
     * @param it
     * @param msg
     * @return 
     */
    private String recorrido(ArrayList it, String msg) {
        int i = 0;
        String r = msg + "\n";
        while (i < it.size()) {
            r += "\t" + it.get(i).toString() + "\n";
            i++;
        }
        return (r);
    }
    
    public String CantidadNodos(){
        return this.miArbol.cantidadNodos();
    }
    
    public String CantidadHojas(){
        return this.miArbol.cantidadNodosHoja();
    }
    public String alturaArbol(){
        return this.miArbol.retornarAltura();
    }
    public String menorValor(){
        return this.miArbol.menorValor();
    }
    public String mayorValor(){
        return this.miArbol.mayorValor();
    }
    public String balance(){
        return this.miArbol.imprimirBalance();
    }
    public String borrarMenor(){
        return this.miArbol.borrarMenor();
    }
    public String borrarMayor(){
        return this.miArbol.borrarMayor();
    }
    //se obtiene la lista y se llama al metodo para imprimir nivel
    public String porNivel(){
        this.miArbol.alturaArbol();
        ArrayList it = this.miArbol.imprimirNivel();
        return (recorrido(it, "Imprimir Por niveles en orden!!!"));
    }
    public String cambiar() {
        this.miArbol.cambiar();
        return "Valores cambiados!!!";
    }
    public String ramaMayor() {
        ArrayList it = this.miArbol.ObtenerRamamayor();
        return (recorrido(it, "Rama(s) con mas valores"));
    }
    

    public void podarArbol() {
        this.miArbol.podar();
    }

    public JPanel getDibujo() {
        return this.miArbol.getdibujo();
    }
}
