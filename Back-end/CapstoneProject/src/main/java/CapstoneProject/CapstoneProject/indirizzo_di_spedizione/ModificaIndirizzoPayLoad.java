package CapstoneProject.CapstoneProject.indirizzo_di_spedizione;

import CapstoneProject.CapstoneProject.user.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ModificaIndirizzoPayLoad (

        String via,
        String numero,
        String codice_postale,
        String paese,
        String provincia,

        String numero_interno


){
}
