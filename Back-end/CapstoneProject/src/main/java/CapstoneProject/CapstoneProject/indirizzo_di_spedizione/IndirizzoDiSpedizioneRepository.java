package CapstoneProject.CapstoneProject.indirizzo_di_spedizione;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndirizzoDiSpedizioneRepository extends JpaRepository<IndirizzoDiSpedizione, Long> {
}
