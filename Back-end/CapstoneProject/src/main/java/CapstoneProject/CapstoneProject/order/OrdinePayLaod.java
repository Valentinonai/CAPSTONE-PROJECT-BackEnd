package CapstoneProject.CapstoneProject.order;

import CapstoneProject.CapstoneProject.build.Build;
import CapstoneProject.CapstoneProject.indirizzo_di_spedizione.IndirizzoDiSpedizione;
import CapstoneProject.CapstoneProject.item.Item;
import CapstoneProject.CapstoneProject.user.User;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record OrdinePayLaod(



        @NotNull(message = "Il campo indirizzo non può essere vuoto")
        IndirizzoDiSpedizione indirizzo_di_spedizione,
        @NotNull(message = "Il campo user non può essere vuoto")
        User user,
        @NotNull(message = "Il campo builds non può essere vuoto")
        List<Build> builds,
        @NotNull(message = "Il campo items non può essere vuoto")
        List<Item> items
) {
}
