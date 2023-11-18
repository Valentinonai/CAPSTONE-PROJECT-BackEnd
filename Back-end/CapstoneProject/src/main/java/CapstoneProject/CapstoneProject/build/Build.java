package CapstoneProject.CapstoneProject.build;

import CapstoneProject.CapstoneProject.item.Item;
import CapstoneProject.CapstoneProject.order.Ordine;
import CapstoneProject.CapstoneProject.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Getter
@Builder
@Entity
@Table(name = "bulds")
public class Build {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "data_creazione")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected Date data_creazione;

    @Column(name = "prezzo")
    private double prezzo;

    @ManyToMany
    @JoinTable(name = "build_ordine",joinColumns = @JoinColumn(name ="build_id" ),inverseJoinColumns = @JoinColumn(name = "ordine_id"))
    @JsonIgnore
    private List<Ordine> ordini;

    @ManyToMany
    @JoinTable(name = "item_build",joinColumns = @JoinColumn(name ="build_id" ),inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;

    @ManyToMany
    @JoinColumn(name = "user")
    @JsonIgnore
    private User user;


    //metodi e costruttori

    public Build(){}

    public Build(List<Item> items) {
        this.prezzo = calcolatot(items);
        this.items = items;
    }


    public double calcolatot(List<Item> items){
        double tot=0;
        for(int i=0;i<items.size();i++)
            tot+=items.get(i).getPrezzo();
        return tot;
    }
}
