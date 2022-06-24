/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.esenred.esenredweb.business.endpoints;

//import io.swagger.annotations.Api;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/test")
//@Api("/test")
public class TestService {

    @GET
    @Path("/echo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test() {
        return Response.ok("rest test").build();
    }

}
