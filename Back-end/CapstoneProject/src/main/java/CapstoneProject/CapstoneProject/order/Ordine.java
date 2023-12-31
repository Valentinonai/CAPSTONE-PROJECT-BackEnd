package CapstoneProject.CapstoneProject.order;

import CapstoneProject.CapstoneProject.Enum.Stato;
import CapstoneProject.CapstoneProject.build.Build;
import CapstoneProject.CapstoneProject.indirizzo_di_spedizione.IndirizzoDiSpedizione;
import CapstoneProject.CapstoneProject.item.Item;
import CapstoneProject.CapstoneProject.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "ordini")
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "prezzo",nullable = false)
    private double prezzo;

    @Column(name = "data_creazione")
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    protected LocalDate data_creazione;

    @Column(name = "data_di_consegna")
    private LocalDate data_di_consegna;

    @Column(name = "stato")
    @Enumerated(EnumType.STRING)
    private Stato stato=Stato.ATTIVO;



    @ManyToOne
    @JoinColumn(name = "indirizzo_di_spedizione")
    private IndirizzoDiSpedizione indirizzo_di_spedizione;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @ManyToMany
    @JoinTable(name = "build_ordine",joinColumns = @JoinColumn(name = "ordine_id"),inverseJoinColumns = @JoinColumn(name = "build_id"))
    private List<Build> builds;

    @ManyToMany
    @JoinTable(name = "item_ordine",joinColumns = @JoinColumn(name = "ordine_id"),inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;


    //metodi e costruttori

    public Ordine (){}

    public Ordine(IndirizzoDiSpedizione indirizzo_di_spedizione, User user,List<Build> builds,  List<Item> items) {
        this.prezzo =calcolaTot(builds,items);
        this.data_di_consegna = LocalDate.now().plusDays(5);
        this.indirizzo_di_spedizione = indirizzo_di_spedizione;
        this.user_id = user;
        this.builds = builds;
        this.items = items;
    }

    public double calcolaTot(List<Build> builds, List<Item> items){
        double tot=0;
        for(int i=0;i<items.size();i++)
            tot+=items.get(i).getPrezzo();
        for(int i=0;i<builds.size();i++)
            tot+=builds.get(i).getPrezzo();
        return tot;
    }
}
