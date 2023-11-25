package CapstoneProject.CapstoneProject.order;

import CapstoneProject.CapstoneProject.build.Build;
import CapstoneProject.CapstoneProject.indirizzo_di_spedizione.IndirizzoDiSpedizione;
import CapstoneProject.CapstoneProject.item.Item;
import CapstoneProject.CapstoneProject.user.User;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record OrdinePayLaod(

        @NotNull(message = "Il campo user non può essere vuoto")
        long  user_id,

        List<Build> builds,

        List<Item> items
) {
}
