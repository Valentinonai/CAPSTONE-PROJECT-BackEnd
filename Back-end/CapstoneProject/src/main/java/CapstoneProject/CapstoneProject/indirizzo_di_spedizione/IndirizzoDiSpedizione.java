package CapstoneProject.CapstoneProject.indirizzo_di_spedizione;

import CapstoneProject.CapstoneProject.order.Ordine;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Builder
@Entity
@Table(name = "indirizzi_spedizione")
public class IndirizzoDiSpedizione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "via",nullable = false)
    private String via;

    @Column(name="numero",nullable = false)
    private int numero;

    @Column(name = "codice_postale",nullable = false)
    private String codice_postale;

    @Column(name = "paese",nullable = false)
    private String paese;

    @Column(name = "provincia",nullable = false)
    private String provincia;

    @Column(name = "data_creazione")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected LocalDate data_creazione;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "indirizzo_di_spedizione")
    @JsonIgnore
    private List<Ordine> ordini;

    //metodi e costruttori

    public IndirizzoDiSpedizione(){}

    public IndirizzoDiSpedizione(String via, int numero, String codice_postale, String paese, String provincia, User user) {
        this.via = via;
        this.numero = numero;
        this.codice_postale = codice_postale;
        this.paese = paese;
        this.provincia = provincia;
        this.user = user;
    }
}