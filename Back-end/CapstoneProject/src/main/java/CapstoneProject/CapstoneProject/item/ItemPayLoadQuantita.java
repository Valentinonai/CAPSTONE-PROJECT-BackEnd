package CapstoneProject.CapstoneProject.item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemPayLoadQuantita(
        @NotNull(message = "la quantità non può essere vuota")
                @Min(0)
        int quantita

) {
}
