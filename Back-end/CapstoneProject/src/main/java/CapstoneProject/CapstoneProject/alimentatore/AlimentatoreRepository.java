package CapstoneProject.CapstoneProject.alimentatore;

import CapstoneProject.CapstoneProject.Enum.Stato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentatoreRepository extends JpaRepository<Alimentatore,Long> {

    @Query("select a from Alimentatore a where a.potenza_max_erogata>:potenzaNecessaria and a.stato=:stato")
    Page<Alimentatore> findByPotenza_max_erogataGreaterThanAndStato(Pageable p, int potenzaNecessaria, Stato stato);
}
