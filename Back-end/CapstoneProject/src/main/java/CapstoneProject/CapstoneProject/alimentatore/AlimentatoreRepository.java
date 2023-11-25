package CapstoneProject.CapstoneProject.alimentatore;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentatoreRepository extends JpaRepository<Alimentatore,Long> {

    Page<Alimentatore> findByPotenza_max_erogataGreaterThan(Pageable p,int potenza_max_erogata);
}
