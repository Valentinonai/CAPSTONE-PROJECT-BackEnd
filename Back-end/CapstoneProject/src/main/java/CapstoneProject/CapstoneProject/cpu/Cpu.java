package CapstoneProject.CapstoneProject.cpu;

import CapstoneProject.CapstoneProject.Enum.Categoria;
import CapstoneProject.CapstoneProject.item.Item;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@Entity
@Table(name = "cpus")
public class Cpu extends Item {

    @Column(name = "socket",nullable = false)
    private String socket;
    @Column(name = "numero_core",nullable = false)
    private int numero_core;
    @Column(name = "numero_threads",nullable = false)
    private int numero_threads;
    @Column(name="max_boost_clock",nullable = false)
    private String max_boost_clock;
    @Column(name = "cache_l2",nullable = false)
    private int cache_l2;
    @Column(name = "cache_l3",nullable = false)
    private int cache_l3;
    @Column(name = "max_temperatura",nullable = false)
    private int max_temperatura;

    @Column(name = "grafica_integrata",nullable = false)
    private boolean grafica_integrata;
    @Column(name = "tipo_memoria_di_sistema",nullable = false)
    private String tipo_memoria_di_sistema;


    //metodi e costruttori


    public Cpu(){}

    public Cpu(String marca, String nome, String descrizione, double prezzo, LocalDate data_di_rilascio, int potenza_di_picco, int quantità, Categoria categoria, String socket, int numero_core, int numero_threads, String max_boost_clock, int cache_l2, int cache_l3, int max_temperatura, boolean grafica_integrata, String tipo_memoria_di_sistema) {
        super(marca, nome, descrizione, prezzo, data_di_rilascio, potenza_di_picco, quantità, categoria);
        this.socket = socket;
        this.numero_core = numero_core;
        this.numero_threads = numero_threads;
        this.max_boost_clock = max_boost_clock;
        this.cache_l2 = cache_l2;
        this.cache_l3 = cache_l3;
        this.max_temperatura = max_temperatura;
        this.grafica_integrata = grafica_integrata;
        this.tipo_memoria_di_sistema = tipo_memoria_di_sistema;
    }

    public Cpu(String socket, int numero_core, int numero_threads, String max_boost_clock, int cache_l2, int cache_l3, int max_temperatura, boolean grafica_integrata, String tipo_memoria_di_sistema) {
        this.socket = socket;
        this.numero_core = numero_core;
        this.numero_threads = numero_threads;
        this.max_boost_clock = max_boost_clock;
        this.cache_l2 = cache_l2;
        this.cache_l3 = cache_l3;
        this.max_temperatura = max_temperatura;
        this.grafica_integrata = grafica_integrata;
        this.tipo_memoria_di_sistema = tipo_memoria_di_sistema;
    }
}
