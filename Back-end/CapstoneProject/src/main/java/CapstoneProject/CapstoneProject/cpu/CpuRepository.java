package CapstoneProject.CapstoneProject.cpu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuRepository extends JpaRepository<Cpu,Long> {
    Page<Cpu> findBySocket(Pageable pageable, String socket);
}
