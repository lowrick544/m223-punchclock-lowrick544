package ch.zli.m223.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/secure")
public class SecureResource {

    @GET
    @Path("/data")
    @RolesAllowed("User")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getSecureData(){
        return Response.ok("This is protected data.").build();
    }
}