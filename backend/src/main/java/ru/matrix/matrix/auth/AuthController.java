package ru.matrix.matrix.auth;

import jakarta.validation.Valid;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/api/auth/csrf")
    public CsrfToken getCsrf(CsrfToken token) {
        return token;
    }

    // TODO: Поменять формат возвращаемого response
//    @PostMapping("/api/auth/register")
//    public ResponseEntity register(@Valid @RequestBody RegisterRequest registerRequest) {
//
//    }
}
