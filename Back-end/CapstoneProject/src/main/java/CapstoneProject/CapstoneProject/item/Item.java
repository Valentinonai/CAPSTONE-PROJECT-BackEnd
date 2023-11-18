package CapstoneProject.CapstoneProject.item;

import CapstoneProject.CapstoneProject.Enum.Categoria;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
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
}