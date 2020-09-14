/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinario.modelo.excepciones;

/**
 *
 * @author aula205c
 */
public class ArbolNException extends Exception{

    public ArbolNException() {
    }

    public ArbolNException(String message) {
        super(message);
    }

    public ArbolNException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArbolNException(Throwable cause) {
        super(cause);
    }
    
}
