package CapstoneProject.CapstoneProject.alimentatore;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentatoreRepository extends JpaRepository<Alimentatore,Long> {

    @Query("select a from Alimentatore a where a.potenza_max_erogata>:potenzaNecessaria")
    Page<Alimentatore> findByPotenza_max_erogataGreaterThan(Pageable p,int potenzaNecessaria);
}
