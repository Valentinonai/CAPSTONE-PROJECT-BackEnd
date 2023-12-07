package CapstoneProject.CapstoneProject.chat;

import jakarta.validation.constraints.NotEmpty;

public record ChatPayload(
        @NotEmpty(message = "il messaggio non può essere vuoto")
        String messaggio
) {
}
