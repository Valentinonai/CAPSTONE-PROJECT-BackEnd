package CapstoneProject.CapstoneProject.user;

import jakarta.validation.constraints.NotEmpty;

public record ModificaPasswordPayLoad(

        @NotEmpty(message = "Il campo password non può essere vuoto")
        String password
) {
}
