package ru.matrix.matrix.auth;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank @Size(max = 100) String email,
    @NotBlank @Size(min = 8, max = 100) String password,
    @NotBlank @Size(max = 100) String full_name,
    @NotBlank @AssertTrue boolean consentAccepted
) {}
