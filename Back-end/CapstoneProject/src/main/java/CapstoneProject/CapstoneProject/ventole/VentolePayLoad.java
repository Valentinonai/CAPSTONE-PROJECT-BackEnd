package CapstoneProject.CapstoneProject.ventole;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record VentolePayLoad(
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
        @NotNull(message = "Il campo rpm min non può essere vuoto")
        @Min(0)
      int rpm_min,
        @NotNull(message = "Il campo rpm max non può essere vuoto")
        @Min(0)
        int rpm_max,
  boolean pwm,
        @NotNull(message = "Il campo dimensione non può essere vuoto")
        @Min(0)
      int dimensione,
         @NotNull(message = "Il campo numero pezzi per pacco non può essere vuoto")
        @Min(3)
         int pezzi_per_pacco
) {
}
