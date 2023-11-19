package CapstoneProject.CapstoneProject.ventole;

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
@Table(name = "ventole")
public class Ventola extends Item {
    @Column(name = "rpm_min")
    private int rpm_min;
    @Column(name = "rpm_max")
    private int rpm_max;
    @Column(name = "controllo_pwm")
    private boolean pwm=false;
    @Column(name = "dimensione",nullable = false)
    private int dimensione;
    @Column(name="pezzi per pacco")
    private int pezzi_per_pacco;

    //metodi e costruttori

    public Ventola(){}

    public Ventola(String marca, String nome, String descrizione, double prezzo, LocalDate data_di_rilascio, int potenza_di_picco, int quantità, Categoria categoria, int rpm_min, int rpm_max, boolean pwm, int dimensione,int pezzi_per_pacco) {
        super(marca, nome, descrizione, prezzo, data_di_rilascio, potenza_di_picco, quantità, categoria);
        this.rpm_min = rpm_min;
        this.rpm_max = rpm_max;
        this.pwm = pwm;
        this.dimensione = dimensione;
        this.pezzi_per_pacco=getPezzi_per_pacco();
    }

    public Ventola(int rpm_min, int rpm_max, boolean pwm, int dimensione) {
        this.rpm_min = rpm_min;
        this.rpm_max = rpm_max;
        this.pwm = pwm;
        this.dimensione = dimensione;
    }
}
