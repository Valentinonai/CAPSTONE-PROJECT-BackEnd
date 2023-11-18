package CapstoneProject.CapstoneProject.item;

import CapstoneProject.CapstoneProject.Enum.Categoria;
import CapstoneProject.CapstoneProject.order.Ordine;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column(name = "marca", nullable = false)
    protected String marca;

    @Column(name = "nome", nullable = false)
    protected String nome;

    @Column(name = "descizione")
    protected String descrizione;

    @Column(name = "prezzo", nullable = false)
    protected double prezzo;

    @Column(name = "immagine_url")
    protected String immagineUrl = "https://res.cloudinary.com/dzr77mvcs/image/upload/v1699804334/uzqt1xnviwyjlcxqnqka.webp";

    @Column(name = "data_creazione")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected Date data_creazione;

    @Column(name = "data_di_rilascio", nullable = false)
    protected LocalDate data_di_rilascio;

    @Column(name = "potenza_di_picco",nullable = false)
    protected int potenza_di_picco;

    @Column(name = "quantità",nullable = false)
    protected int quantità;

    @Column(name = "categoria",nullable = false)
    @Enumerated(EnumType.STRING)
    protected Categoria categoria;

    @ManyToMany
    @JoinTable(name = "item_ordine",joinColumns = @JoinColumn(name = "item_id"),inverseJoinColumns = @JoinColumn(name ="ordine_id" ))
    private List<Ordine> ordini;


    @ManyToMany
    @JoinTable(name = "item_user",joinColumns = @JoinColumn(name = "item_id"),inverseJoinColumns = @JoinColumn(name ="user_id" ))
    @JsonIgnore
    private Set<User> users;

    //metodi e costruttori

    public Item(){}

    public Item(String marca, String nome, String descrizione, double prezzo, LocalDate data_di_rilascio, int potenza_di_picco, int quantità, Categoria categoria) {
        this.marca = marca;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.data_di_rilascio = data_di_rilascio;
        this.potenza_di_picco = potenza_di_picco;
        this.quantità = quantità;
        this.categoria = categoria;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public void setImmagineUrl(String immagineUrl) {
        this.immagineUrl = immagineUrl;
    }

    public void setData_di_rilascio(LocalDate data_di_rilascio) {
        this.data_di_rilascio = data_di_rilascio;
    }

    public void setPotenza_di_picco(int potenza_di_picco) {
        this.potenza_di_picco = potenza_di_picco;
    }

    public void setQuantità(int quantità) {
        this.quantità = quantità;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setOrdini(Ordine ordine) {
        this.ordini.add(ordine);
    }

    public void setUsers(User user) {
        this.users.add(user);
    }
}