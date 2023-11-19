package CapstoneProject.CapstoneProject.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record UserPayLoad(
        @NotEmpty(message = "Il campo nome non può essere vuoto")
        String nome,
        @NotEmpty(message = "Il campo nome non può essere vuoto")
        String cognome,
        @NotEmpty(message = "Il campo email non può essere vuoto")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
        String email,
        @NotEmpty(message = "Il campo password non può essere vuoto")
        String password
) {
}
