package CapstoneProject.CapstoneProject.ram;

import CapstoneProject.CapstoneProject.scheda_madre.SchedaMadre;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

public record RamPayLoad(
        @NotEmpty(message = "Il campo marca non può essere vuoto")
        String marca,
        @NotEmpty(message = "Il campo nome non può essere vuoto")
        String nome,

        String descrizione,
        @NotNull(message = "Il campo prezzo non può essere vuoto")
        @Min(0)
        double prezzo,
        @NotEmpty(message = "Il campo data di rilascio non può essere vuoto")
        LocalDate data_di_rilascio,

        @NotNull(message = "Il campo potenza di picco non può essere vuoto")
        int potenza_di_picco,
        @NotNull(message = "Il campo quantità non può essere vuoto")
        @Min(0)
        int quantita,

//---------Specifiche
        @NotEmpty(message = "Il campo tipo di memoria non può essere vuoto")
      String tipo_di_memoria,
                @NotNull(message = "Il campo velocità non può essere vuoto")
        @Min(0)
       int velocità,
        @NotNull(message = "Il campo dimensione non può essere vuoto")
        @Min(0)
      int dimensione,

        @NotEmpty(message = "Il campo lista di compatibilità  non può essere vuoto")
        Set<SchedaMadre> lista_schedemadri
) {
}
