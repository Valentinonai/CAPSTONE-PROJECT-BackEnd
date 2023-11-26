package CapstoneProject.CapstoneProject.build;

import CapstoneProject.CapstoneProject.Enum.Stato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildRepository extends JpaRepository<Build, Long> {
    @Query("select b from Build b join b.user u where  u.id=:user_id and b.stato=:stato")
    Page<Build> getAllBuildsAttive(Pageable pageable, long user_id, Stato stato);
}
