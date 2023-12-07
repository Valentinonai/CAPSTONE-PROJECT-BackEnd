package CapstoneProject.CapstoneProject.order;


import CapstoneProject.CapstoneProject.build.Build;
import CapstoneProject.CapstoneProject.item.Item;

import java.util.List;

public record ElementiNonPresentiPayload(
        List<Build> build_list,
        List<Item> items_list
) {
}
