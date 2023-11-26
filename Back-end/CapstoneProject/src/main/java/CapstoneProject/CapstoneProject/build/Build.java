package CapstoneProject.CapstoneProject.build;

import CapstoneProject.CapstoneProject.Enum.Stato;
import CapstoneProject.CapstoneProject.item.Item;
import CapstoneProject.CapstoneProject.order.Ordine;
import CapstoneProject.CapstoneProject.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "builds")
public class Build {

    @Column(name = "data_creazione")
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    protected LocalDate data_creazione;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "prezzo")
    private double prezzo;

    @Column(name = "stato")
    @Enumerated(EnumType.STRING)
    private Stato stato=Stato.ATTIVO;

    @ManyToMany
    @JoinTable(name = "build_ordine", joinColumns = @JoinColumn(name = "build_id"), inverseJoinColumns = @JoinColumn(name = "ordine_id"))
    @JsonIgnore
    private List<Ordine> ordini;

    @ManyToMany
    @JoinTable(name = "item_build", joinColumns = @JoinColumn(name = "build_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    //metodi e costruttori


    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public Build(List<Item> items, User user) {
        this.prezzo = calcolatot(items);
        this.items = items;
        this.user = user;
    }

    public Build(LocalDate data_creazione, double prezzo, List<Item> items, User user) {
        this.data_creazione = data_creazione;
        this.prezzo = calcolatot(items);
        this.items = items;
        this.user = user;
    }

    public void setOrdini(List<Ordine> ordini) {
        this.ordini = ordini;
    }

    public void setItems(List<Item> items) {
        this.items.removeAll(this.items);
        this.items = items;
        this.prezzo=calcolatot(items);
    }

    public double calcolatot(List<Item> items) {
        double tot = 0;
        for (int i = 0; i < items.size(); i++)
            tot += items.get(i).getPrezzo();
        return tot;
    }
}
