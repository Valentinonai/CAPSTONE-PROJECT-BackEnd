package CapstoneProject.CapstoneProject.ventole;

import CapstoneProject.CapstoneProject.Enum.Stato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentoleRepository extends JpaRepository<Ventola,Long> {

    Page<Ventola>  findByDimensioneAndStato(Pageable p, int dimensione, Stato stato);
}
