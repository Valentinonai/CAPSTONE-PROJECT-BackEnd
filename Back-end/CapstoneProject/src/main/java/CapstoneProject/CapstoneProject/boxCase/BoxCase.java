package CapstoneProject.CapstoneProject.boxCase;

import CapstoneProject.CapstoneProject.Enum.Categoria;
import CapstoneProject.CapstoneProject.Enum.Formato;
import CapstoneProject.CapstoneProject.item.Item;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@Entity
@Table(name = "cases")
public class BoxCase extends Item {

    //per verificare compatibilità con scheda madre
    @Column(name = "formato",nullable = false)
    @Enumerated(EnumType.STRING)
    private Formato formato;

    //per sapere il numero massimo di ventole che si possono inserire
    @Column(name = "num_ventole",nullable = false)
    private int num_ventole;

    @Column(name = "larghezza_mm")
    private int larghezza;
    @Column(name = "altezza_mm")
    private int altezza;
    @Column(name = "profondità_mm")
    private int profondità;

    //metodi e costruttori

    public BoxCase(){}

    public BoxCase(String marca, String nome, String descrizione, double prezzo, LocalDate data_di_rilascio, int potenza_di_picco, int quantità, Categoria categoria, Formato formato, int num_ventole, int larghezza, int altezza, int profondità) {
        super(marca, nome, descrizione, prezzo, data_di_rilascio, potenza_di_picco, quantità, categoria);
        this.formato = formato;
        this.num_ventole = num_ventole;
        this.larghezza = larghezza;
        this.altezza = altezza;
        this.profondità = profondità;
    }

    public BoxCase(Formato formato, int num_ventole, int larghezza, int altezza, int profondità) {
        this.formato = formato;
        this.num_ventole = num_ventole;
        this.larghezza = larghezza;
        this.altezza = altezza;
        this.profondità = profondità;
    }
}
