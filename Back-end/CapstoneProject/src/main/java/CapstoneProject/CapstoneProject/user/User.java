package CapstoneProject.CapstoneProject.user;

import CapstoneProject.CapstoneProject.Enum.Ruolo;
import CapstoneProject.CapstoneProject.carta_di_credito.CartaDiCredito;
import CapstoneProject.CapstoneProject.indirizzo_di_spedizione.IndirizzoDiSpedizione;
import CapstoneProject.CapstoneProject.item.Item;
import CapstoneProject.CapstoneProject.order.Ordine;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"password", "enabled", "accountNonExpired", "accountNonLocked", "authorities", "credentialsNonExpired","username","builds","ordini"})
public class User implements UserDetails {
    //Dati obbligatori
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome",nullable = false)
    private String nome;

    @Column(name = "cognome",nullable = false)
    private String cognome;

    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "has_dati_ordine")
    private boolean has_dati_ordine=false;

    @Column(name = "immagine_url")
    private String immagineUrl="https://res.cloudinary.com/dzr77mvcs/image/upload/v1699804243/bqnqdcricxpzxojhihxz.webp";

    @Column(name = "data_creazione")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected Date data_creazione;


    @Column(name = "ruolo")
    private Ruolo ruolo=Ruolo.USER;


    //Dati di pagamento

    @OneToOne(mappedBy = "user")
    private CartaDiCredito cartaDiCredito;

    //Dati Spedizione

    @OneToOne(mappedBy = "user")
    private IndirizzoDiSpedizione indirizzoSpedizione;

    //Build

    @OneToMany(mappedBy = "user")
    private List<Build> builds;

    //Ordini

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Ordine> ordini;


    //items

    @ManyToMany
    @JoinTable(name = "item_user",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name ="item_id" ))
    private Set<Item> items;

    //costruttori

    public User(){}


    public User(String nome, String cognome, String email, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }




    //Setters




    public void setImmagineUrl(String immagineUrl) {
        this.immagineUrl = immagineUrl;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHas_dati_ordine(boolean has_dati_ordine) {
        this.has_dati_ordine = has_dati_ordine;
    }

    public void setCartaDiCredito(CartaDiCredito cartaDiCredito) {
        this.cartaDiCredito = cartaDiCredito;
    }

    public void setIndirizzoSpedizione(IndirizzoDiSpedizione indirizzoSpedizione) {
        this.indirizzoSpedizione = indirizzoSpedizione;
    }

    public void setBuilds(Build build) {
        this.builds.add(build) ;
    }

    public void setOrdini(Ordine ordine) {
        this.ordini.add(ordine);
    }


    public void setBuilds(Build build) {
        this.builds.add(build);
    }

    public void setOrdini(Ordine ordine) {
        this.ordini.add(ordine);
    }

    public void setItems(Item item) {
        this.items.add(item);
    }

//Metodi dell'interfaccia


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((this.ruolo.name())));
    }

    @Override
    public String getUsername() {
        return this.nome+"_"+this.cognome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
