package CapstoneProject.CapstoneProject.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query(value = "select item_id, count(*)  from item_user group by item_id order by count(*) desc",nativeQuery = true)
    List<List<Object>> likesItems();
}
