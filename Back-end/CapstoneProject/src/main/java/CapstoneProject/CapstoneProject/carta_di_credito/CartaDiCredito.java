package CapstoneProject.CapstoneProject.carta_di_credito;

import CapstoneProject.CapstoneProject.Enum.Stato;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import CapstoneProject.CapstoneProject.user.User;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "carte_di_credito")
public class CartaDiCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "numero_carta",nullable = false)
    private String numero_carta;
    @Column(name = "cvv",nullable = false)
    private String cvv;
    @Column(name = "data_di_scadenza",nullable = false)
    private String data_di_scadenza;
    @Column(name = "data_creazione")
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    protected LocalDate data_creazione;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "stato")
    @Enumerated(EnumType.STRING)
    private Stato stato=Stato.ATTIVO;


    //metodi e costruttori

    public CartaDiCredito(){}

    public CartaDiCredito(String numero_carta, String cvv, String data_di_scadenza, User user) {
        this.numero_carta = numero_carta;
        this.cvv = cvv;
        this.data_di_scadenza = data_di_scadenza;
        this.user = user;
    }

    public CartaDiCredito(long id, String numero_carta, String cvv, String data_di_scadenza, LocalDate data_creazione, User user) {
        this.id = id;
        this.numero_carta = numero_carta;
        this.cvv = cvv;
        this.data_di_scadenza = data_di_scadenza;
        this.data_creazione = data_creazione;
        this.user = user;
    }
}