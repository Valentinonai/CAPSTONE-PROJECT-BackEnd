package CapstoneProject.CapstoneProject.carta_di_credito;

import CapstoneProject.CapstoneProject.user.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CartaDiCreditoPayLoad(
        @NotEmpty(message = "Il numero di carta non può essere vuoto")
        String numero_carta,
        @NotEmpty(message = "Il cvv non può essere vuoto")
        String cvv,
        @NotEmpty(message = "La data di scadenza non può essere vuota")
        String data_di_scadenza,
        @NotNull(message = "La carta deve essere associata ad uno user")
        User user
) {
}
