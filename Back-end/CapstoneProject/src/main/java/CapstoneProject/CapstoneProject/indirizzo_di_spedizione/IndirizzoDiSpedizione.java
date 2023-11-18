package CapstoneProject.CapstoneProject.indirizzo_di_spedizione;

import CapstoneProject.CapstoneProject.order.Ordine;
import CapstoneProject.CapstoneProject.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "indirizzi_spedizione")
public class IndirizzoDiSpedizione {


    @Column(name = "data_creazione")
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    protected LocalDate data_creazione;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "via", nullable = false)
    private String via;
    @Column(name = "numero", nullable = false)
    private int numero;
    @Column(name = "codice_postale", nullable = false)
    private String codice_postale;
    @Column(name = "paese", nullable = false)
    private String paese;
    @Column(name = "provincia", nullable = false)
    private String provincia;
    @Column(name = "numero_interno", nullable = false)
    private int numero_interno;
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "indirizzo_di_spedizione")
    @JsonIgnore
    private List<Ordine> ordini;

    //metodi e costruttori

    public IndirizzoDiSpedizione() {
    }

    public IndirizzoDiSpedizione(String via, int numero, String codice_postale, String paese, int numero_interno, String provincia, User user) {
        this.via = via;
        this.numero = numero;
        this.codice_postale = codice_postale;
        this.paese = paese;
        this.provincia = provincia;
        this.numero_interno = numero_interno;
        this.user = user;
    }


}
