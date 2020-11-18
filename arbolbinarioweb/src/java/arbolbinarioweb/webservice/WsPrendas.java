/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolbinarioweb.webservice;

import dremali.modelo.Prenda;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author camil
 */
@Path("prendaws")
public class WsPrendas {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WsPrendas
     */
    public WsPrendas() {
    }

    /**
     * Retrieves representation of an instance of arbolbinarioweb.webservice.WsPrendas
     * @return an instance of dremali.modelo.Prenda
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Prenda getJson() {
        //TODO return proper representation object
        return new Prenda();
        //throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of WsPrendas
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(Prenda content) {
    }
}
