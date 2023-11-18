package CapstoneProject.CapstoneProject.build;

import CapstoneProject.CapstoneProject.item.Item;
import CapstoneProject.CapstoneProject.user.User;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record BuildSavePayload(

        @NotNull(message = "La lista items noon può essere vuota")
        List<Item> items,

        @NotNull(message = "Lo user non può essere vuoto")
        User user
) {
}
