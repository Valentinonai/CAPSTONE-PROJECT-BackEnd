package CapstoneProject.CapstoneProject.carta_di_credito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaDiCreditoRepository extends JpaRepository<CartaDiCredito, Long> {
}
