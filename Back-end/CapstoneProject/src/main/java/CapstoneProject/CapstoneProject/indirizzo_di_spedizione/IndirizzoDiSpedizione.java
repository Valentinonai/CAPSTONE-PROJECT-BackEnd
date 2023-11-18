package CapstoneProject.CapstoneProject.indirizzo_di_spedizione;

import CapstoneProject.CapstoneProject.order.Ordine;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import CapstoneProject.CapstoneProject.user.User;
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
    @Temporal(TemporalType.DATE)
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

    public IndirizzoDiSpedizione(long id, String via, int numero, String codice_postale, String paese, String provincia, LocalDate data_creazione, User user, List<Ordine> ordini) {
        this.id = id;
        this.via = via;
        this.numero = numero;
        this.codice_postale = codice_postale;
        this.paese = paese;
        this.provincia = provincia;
        this.data_creazione = data_creazione;
        this.user = user;
        this.ordini = ordini;
    }
}
