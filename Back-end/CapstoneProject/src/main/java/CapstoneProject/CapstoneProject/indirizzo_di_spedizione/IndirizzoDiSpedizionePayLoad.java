package CapstoneProject.CapstoneProject.indirizzo_di_spedizione;

import CapstoneProject.CapstoneProject.user.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record IndirizzoDiSpedizionePayLoad(
        @NotEmpty(message = "La via non può essere vuota")
        String via,
        @Min(value = 1, message = "Il numero civico deve essere maggiore di 0")
        int numero,
        @NotEmpty(message = "Il codice postale non può essere vuoto")
        String codice_postale,
        @NotEmpty(message = "Il paese non può essere vuoto")
        String paese,
        @NotEmpty(message = "La provincia non può essere vuota")
        String provincia,
        @Min(value = 1, message = "Il numero interno deve essere maggiore di 0")
        int numero_interno,

        @NotNull(message = "L'indirizzo deve essere associato ad un utente")
        User user


) {
}
