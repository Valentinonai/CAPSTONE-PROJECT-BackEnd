package CapstoneProject.CapstoneProject.alimentatore;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AlimentatorePayLoad(
        @NotEmpty(message = "Il campo marca non può essere vuoto")
        String marca,
        @NotEmpty(message = "Il campo nome non può essere vuoto")
        String nome,

        String descrizione,
        @NotNull(message = "Il campo prezzo non può essere vuoto")
        @Min(0)
        double prezzo,
        LocalDate data_di_rilascio,

        @NotNull(message = "Il campo potenza di picco non può essere vuoto")
        int potenza_di_picco,
        @NotNull(message = "Il campo quantità non può essere vuoto")
        @Min(0)
        int quantita,

//---------Specifiche
        @NotNull(message = "Il campo potenza erogata non può essere vuoto")
        @Min(0)
       int potenza_max_erogata,
   boolean modulare,
        @NotNull(message = "Il campo peso non può essere vuoto")
        @Min(0)
    int peso

) {
}
