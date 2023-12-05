package CapstoneProject.CapstoneProject.hard_disk;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardDiskRepository extends JpaRepository<HardDisk,Long> {
    Page<HardDisk> findByM2(Pageable p, boolean m2);
}
