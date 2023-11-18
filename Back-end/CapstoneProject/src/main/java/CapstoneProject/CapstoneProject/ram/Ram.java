package CapstoneProject.CapstoneProject.ram;

import CapstoneProject.CapstoneProject.item.Item;
import CapstoneProject.CapstoneProject.scheda_madre.SchedaMadre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Builder
@Entity
@Table(name = "rams")
public class Ram extends Item {

    @Column(name = "tipo_di_memoria", nullable = false)//DDR4-DDR5
    private String tipo_di_memoria;
    @Column(name = "velocità",nullable = false)
    private int velocità;
    @Column(name = "dimensione",nullable = false)
    private int dimensione;

    @ManyToMany
    @JoinTable(name = "schedamadre_ram",joinColumns = @JoinColumn(name = "ram") ,inverseJoinColumns =@JoinColumn(name = "scheda_madre"))
    Set<SchedaMadre> lista_schedemadri;



    //metodi e costruttori

    public Ram(){}

    public Ram(String tipo_di_memoria, int velocità, int dimensione, Set<SchedaMadre> lista_schedemadri) {
        this.tipo_di_memoria = tipo_di_memoria;
        this.velocità = velocità;
        this.dimensione = dimensione;
        this.lista_schedemadri = lista_schedemadri;
    }

    public void setTipo_di_memoria(String tipo_di_memoria) {
        this.tipo_di_memoria = tipo_di_memoria;
    }

    public void setVelocità(int velocità) {
        this.velocità = velocità;
    }

    public void setDimensione(int dimensione) {
        this.dimensione = dimensione;
    }

    public void setLista_schedemadri(SchedaMadre schedamadre) {
        this.lista_schedemadri.add(schedamadre) ;
    }
}
