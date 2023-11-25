package CapstoneProject.CapstoneProject.hard_disk;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record HardDisskPayLoad(
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
     boolean m2,
        @NotEmpty(message = "Il campo capacità non può essere vuoto")
       String capacita
) {
}
