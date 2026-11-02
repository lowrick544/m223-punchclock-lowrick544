
package ch.zli.m223.controller;

import ch.zli.m223.service.AuthService;
import ch.zli.m223.dto.LoginRequestDto;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
public class AuthController {

    @Inject
    AuthService authService;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(LoginRequestDto dto) {

        if (dto == null) {
            return Response.status(401).build();
        }

        return authService.login(dto.username, dto.password);
    }
}
