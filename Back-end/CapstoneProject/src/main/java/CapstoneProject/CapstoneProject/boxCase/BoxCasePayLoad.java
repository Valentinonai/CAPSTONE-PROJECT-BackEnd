package CapstoneProject.CapstoneProject.boxCase;

import CapstoneProject.CapstoneProject.Enum.Formato;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BoxCasePayLoad(
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
        @NotEmpty(message = "Il campo formato non può essere vuoto")
        String formato,
                @NotNull(message = "Il campo numero di ventole non può essere vuoto")
        @Min(0)
   int num_ventole,
        @NotNull(message = "Il campo larghezza non può essere vuoto")
        @Min(0)
       int larghezza,
        @NotNull(message = "Il campo altezza non può essere vuoto")
        @Min(0)
        int altezza,
        @NotNull(message = "Il campo profondità non può essere vuoto")
        @Min(0)
         int profondità
) {
}
