package br.com.phoenix.api.auth;

import br.com.phoenix.api.security.JwtService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authManager, JwtService jwtService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public record LoginRequest(@NotBlank String username, @NotBlank String password){}
    public record TokenResponse(String token){}

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest req) {
        var auth = new UsernamePasswordAuthenticationToken(req.username(), req.password());
        authManager.authenticate(auth);
        String token = jwtService.generateToken(req.username(), Map.of("scope", "USER"));
        return new TokenResponse(token);
    }
}
