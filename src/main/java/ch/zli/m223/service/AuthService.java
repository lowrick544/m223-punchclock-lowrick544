
package ch.zli.m223.service;

import java.util.Set;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.repository.ApplicationUserRepository;

import io.smallrye.jwt.build.Jwt;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class AuthService {

    @Inject
    ApplicationUserRepository userRepository;

    public Response login(String username, String password) {

        if (username == null || password == null) {
            return Response.status(401).build();
        }

        ApplicationUser user =
                userRepository.findByUsername(username);

        if (user == null ||
                !user.getPassword().equals(password)) {
            return Response.status(401).build();
        }

        String token = Jwt.upn(user.getUsername())
                .issuer("https://example.com/issuer")
                .groups(Set.of(user.getRole()))
                .sign();

        return Response.ok(token).build();
    }
}
