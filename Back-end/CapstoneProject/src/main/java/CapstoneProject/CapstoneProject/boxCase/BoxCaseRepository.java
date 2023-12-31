package CapstoneProject.CapstoneProject.boxCase;

import CapstoneProject.CapstoneProject.Enum.Formato;
import CapstoneProject.CapstoneProject.Enum.Stato;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxCaseRepository extends JpaRepository<BoxCase,Long> {
    Page<BoxCase> findByFormatoAndStato(Pageable p, Formato formato, Stato stato);
}
