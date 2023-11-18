package CapstoneProject.CapstoneProject.hard_disk;

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
@Table(name = "hard_disk")
public class HardDisk extends Item {
    @Column(name = "nvme_m2",nullable = false)
    private boolean m2;
    @Column(name = "capacità",nullable = false)
    private String capacità;

    //metodi e costruttori

    public HardDisk(){}

    public HardDisk(String marca, String nome, String descrizione, double prezzo, LocalDate data_di_rilascio, int potenza_di_picco, int quantità, Categoria categoria, boolean m2, String capacità) {
        super(marca, nome, descrizione, prezzo, data_di_rilascio, potenza_di_picco, quantità, categoria);
        this.m2 = m2;
        this.capacità = capacità;
    }
}
