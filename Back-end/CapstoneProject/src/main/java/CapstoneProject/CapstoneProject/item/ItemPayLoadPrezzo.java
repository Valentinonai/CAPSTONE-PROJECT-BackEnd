package CapstoneProject.CapstoneProject.item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemPayLoadPrezzo(
        @NotNull(message = "il prezzo non può essere vuoto")
                @Min(0)
        double prezzo
) {
}
