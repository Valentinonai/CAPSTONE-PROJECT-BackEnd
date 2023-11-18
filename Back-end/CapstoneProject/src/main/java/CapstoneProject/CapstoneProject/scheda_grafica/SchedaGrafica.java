package CapstoneProject.CapstoneProject.scheda_grafica;

import CapstoneProject.CapstoneProject.Enum.Categoria;
import CapstoneProject.CapstoneProject.item.Item;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@Entity
@Table(name = "schede_grafiche")
public class SchedaGrafica extends Item {

    @Column(name = "larghezza_mm")
    private int larghezza;
    @Column(name = "lunghezza_mm")
    private int lunghezza;
    @Column(name = "boost_clock")
    private String boost_clock;
    @Column(name = "dimensione_memoria")
    private int dimensione_memoria;

    //metodi e costruttore

    public SchedaGrafica(){}

    public SchedaGrafica(String marca, String nome, String descrizione, double prezzo, LocalDate data_di_rilascio, int potenza_di_picco, int quantità, Categoria categoria, int larghezza, int lunghezza, String boost_clock, int dimensione_memoria) {
        super(marca, nome, descrizione, prezzo, data_di_rilascio, potenza_di_picco, quantità, categoria);
        this.larghezza = larghezza;
        this.lunghezza = lunghezza;
        this.boost_clock = boost_clock;
        this.dimensione_memoria = dimensione_memoria;
    }
}
