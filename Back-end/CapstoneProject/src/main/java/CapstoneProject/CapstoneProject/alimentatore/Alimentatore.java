package CapstoneProject.CapstoneProject.alimentatore;

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
@Table(name = "alimentatori")
public class Alimentatore extends Item {

    @Column(name = "potenza_massima_erogata",nullable = false)
    private int potenza_max_erogata;
    @Column(name = "modulare")
    private boolean modulare;
    @Column(name = "peso_g")
    private int peso;

    //metodi e costruttori

    public Alimentatore(){}

    public Alimentatore(String marca, String nome, String descrizione, double prezzo, LocalDate data_di_rilascio, int potenza_di_picco, int quantità, Categoria categoria, int potenza_max_erogata, boolean modulare,int peso) {
        super(marca, nome, descrizione, prezzo, data_di_rilascio, potenza_di_picco, quantità, categoria);
        this.potenza_max_erogata = potenza_max_erogata;
        this.modulare = modulare;
        this.peso=peso;
    }
}
