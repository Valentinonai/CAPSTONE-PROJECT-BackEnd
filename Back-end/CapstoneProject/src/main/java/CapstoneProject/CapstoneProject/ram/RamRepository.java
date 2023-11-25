package CapstoneProject.CapstoneProject.ram;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RamRepository extends JpaRepository<Ram,Long> {
    @Query("select r from Ram r where :scheda_id member of r.lista_schedemadri")
    Page<Ram> findBySchedaId(Pageable p,Long scheda_id);

}
