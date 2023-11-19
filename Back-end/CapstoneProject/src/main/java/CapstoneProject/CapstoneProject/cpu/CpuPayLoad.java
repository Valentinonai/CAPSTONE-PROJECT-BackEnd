package CapstoneProject.CapstoneProject.cpu;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CpuPayLoad(
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
                @Min(0)
        int potenza_di_picco,
        @NotNull(message = "Il campo quantità non può essere vuoto")
        @Min(0)
        int quantita,


        //Specifiche

        @NotEmpty(message = "Il campo dsocket non può essere vuoto")
      String socket,
                @NotEmpty(message = "Il campo numero di core non può essere vuoto")
                @Min(0)
   int numero_core,
        @NotEmpty(message = "Il campo numero di threads non può essere vuoto")
        @Min(0)
       int numero_threads,
        @NotEmpty(message = "Il campo max boost di clock non può essere vuoto")
         String max_boost_clock,
        @NotEmpty(message = "Il campo cache l2 non può essere vuoto")
        @Min(0)
      int cache_l2,
        @NotEmpty(message = "Il campocache l3 non può essere vuoto")
        @Min(0)
      int cache_l3,
        @NotEmpty(message = "Il campo temperatura massima non può essere vuoto")
        @Min(0)
   int max_temperatura,

      boolean grafica_integrata,
        @NotEmpty(message = "Il campo tipo di memoria di sistema non può essere vuoto")
      String tipo_memoria_di_sistema
) {
}
