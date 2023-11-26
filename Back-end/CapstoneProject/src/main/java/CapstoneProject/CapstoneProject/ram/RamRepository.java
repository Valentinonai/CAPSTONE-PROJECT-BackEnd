package CapstoneProject.CapstoneProject.ram;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RamRepository extends JpaRepository<Ram,Long> {
@Query("SELECT r FROM Ram r JOIN r.lista_schedemadri sm WHERE sm.id = :schedaMadreId AND r.stato='ATTIVO'")
Page<Ram> findBySchedaMadreId(Pageable pageable, @Param("schedaMadreId") Long schedaMadreId);
}
