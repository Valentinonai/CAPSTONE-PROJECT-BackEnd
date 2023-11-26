package CapstoneProject.CapstoneProject.item;

import CapstoneProject.CapstoneProject.Enum.Categoria;
import CapstoneProject.CapstoneProject.Enum.Stato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findByCategoriaAndStato(Pageable p, Categoria categoria, Stato stato);

    @Query("select i from Item i where i.stato=:stato")
    Page<Item> getAllAttivi(Pageable p,Stato stato);
}
