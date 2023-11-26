package CapstoneProject.CapstoneProject.cpu;

import CapstoneProject.CapstoneProject.Enum.Stato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuRepository extends JpaRepository<Cpu,Long> {
    Page<Cpu> findBySocketAndStato(Pageable pageable, String socket, Stato stato);
}
