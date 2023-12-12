package CapstoneProject.CapstoneProject.user;

import java.util.List;

public record LikesDTO(
        List<Long> items_id,
        List<Long> count_likes
) {
}
