package CapstoneProject.CapstoneProject.order;

import CapstoneProject.CapstoneProject.Enum.Stato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> {

    @Query("select o from Ordine o where o.user_id.id=:user_id and (o.stato='ATTIVO' or o.stato='IN_LAVORAZIONE'or o.stato='SPEDITO'or o.stato='CONSEGNATO') order by o.id desc ")
    Page<Ordine> findByUser(Pageable p, long user_id);


    @Query("select count(o) from Ordine o JOIN o.items i where i.id=:id")
    Integer contaItemsVenduti(long id);
    @Query("select count(o) from Ordine o JOIN o.builds b JOIN b.items i where i.id=:id")
    Integer contaItemsBuildsVenduti(long id);
}
