package CapstoneProject.CapstoneProject.scheda_grafica;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SchedaGraficaPayload(
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
        @NotNull(message = "Il campo larghezza non può essere vuoto")
        @Min(0)
       int larghezza,
                @NotNull(message = "Il campo lunghezza non può essere vuoto")
        @Min(0)
   int lunghezza,
        @NotEmpty(message = "Il campo boost clock non può essere vuoto")
       String boost_clock,
        @NotNull(message = "Il campo dimensione memoria non può essere vuoto")
        @Min(0)
       int dimensione_memoria

) {
}
