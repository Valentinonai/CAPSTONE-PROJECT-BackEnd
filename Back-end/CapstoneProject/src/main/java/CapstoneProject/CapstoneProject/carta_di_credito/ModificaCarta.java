package CapstoneProject.CapstoneProject.carta_di_credito;

import jakarta.validation.constraints.NotEmpty;

public record ModificaCarta(
        String numero_carta,
        String cvv,
        String data_di_scadenza
) {
}
