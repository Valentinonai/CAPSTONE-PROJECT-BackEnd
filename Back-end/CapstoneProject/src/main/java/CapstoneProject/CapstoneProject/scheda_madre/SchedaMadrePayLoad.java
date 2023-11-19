package CapstoneProject.CapstoneProject.scheda_madre;

import CapstoneProject.CapstoneProject.Enum.Formato;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;

public record SchedaMadrePayLoad(

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
@NotEmpty(message="Il campo formato non può essere vuoto")
       Formato formato,

                //Compatibilità cpu
        @NotEmpty(message="Il campo chipset non può essere vuoto")
        String chipset,
        @NotEmpty(message="Il campo socket non può essere vuoto")
         String socket,

        //Compatibilità ram
        @NotEmpty(message="Il campo tipo di memoria non può essere vuoto")
    String tipo_di_memoria,
    @NotNull(message="Il campo massima capacità di memoria non può essere vuoto")
            @Min(0)
       int max_memory_size,

        //Specifiche extra

        boolean has_wifi,
       boolean has_bluetooth,
     int numero_porte_usb,
       int numero_pcie,
        boolean supporto_m2

) {
}
