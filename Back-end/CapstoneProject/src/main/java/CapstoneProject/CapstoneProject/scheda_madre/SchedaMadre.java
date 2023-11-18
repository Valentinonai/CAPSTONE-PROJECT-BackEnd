package CapstoneProject.CapstoneProject.scheda_madre;

import CapstoneProject.CapstoneProject.Enum.Categoria;
import CapstoneProject.CapstoneProject.Enum.Formato;
import CapstoneProject.CapstoneProject.item.Item;
import CapstoneProject.CapstoneProject.ram.Ram;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;


@Getter
@Builder
@Entity
@Table(name = "schede_madri")
public class SchedaMadre extends Item {

    //Compatibilità case
    @Column(name = "formato",nullable = false)
    @Enumerated(EnumType.STRING)
    private Formato formato;

    //Compatibilità cpu
    @Column(name = "chiset",nullable = false)
    private String chipset;
    @Column(name="socket",nullable = false)
    private String socket;

    //Compatibilità ram
    @Column(name="tipo_di_memoria",nullable = false) //DDR4-DDR5
    private String tipo_di_memoria;
    @Column(name="max_memory_size",nullable = false)
    private int max_memory_size;

    //Specifiche extra
    @Column(name = "has_wifi")
    private boolean has_wifi=false;
    @Column(name = "has_bluetooth")
    private boolean has_bluetooth=false;
    @Column(name = "numero_porte_usb")
    private int numero_porte_usb;
    @Column(name = "numero_pcie")
    private int numero_pcie;
    @Column(name = "supporto_nvme_m2")
    private boolean supporto_m2;


    @ManyToMany
    @JoinTable(name = "schedamadre_ram",joinColumns = @JoinColumn(name = "scheda_madre"),inverseJoinColumns = @JoinColumn(name = "ram"))
            @JsonIgnore
    Set<Ram> lista_ram;

    //metodi e costruttori

    public SchedaMadre(){}

    public SchedaMadre(String marca, String nome, String descrizione, double prezzo, LocalDate data_di_rilascio, int potenza_di_picco, int quantità, Categoria categoria, Formato formato, String chipset, String socket, String tipo_di_memoria, int max_memory_size, boolean has_wifi, boolean has_bluetooth, int numero_porte_usb, int numero_pcie, boolean supporto_m2, Set<Ram> lista_ram) {
        super(marca, nome, descrizione, prezzo, data_di_rilascio, potenza_di_picco, quantità, categoria);
        this.formato = formato;
        this.chipset = chipset;
        this.socket = socket;
        this.tipo_di_memoria = tipo_di_memoria;
        this.max_memory_size = max_memory_size;
        this.has_wifi = has_wifi;
        this.has_bluetooth = has_bluetooth;
        this.numero_porte_usb = numero_porte_usb;
        this.numero_pcie = numero_pcie;
        this.supporto_m2 = supporto_m2;
        this.lista_ram = lista_ram;
    }

    public SchedaMadre(Formato formato, String chipset, String socket, String tipo_di_memoria, int max_memory_size, boolean has_wifi, boolean has_bluetooth, int numero_porte_usb, int numero_pcie, boolean supporto_m2, Set<Ram> lista_ram) {
        this.formato = formato;
        this.chipset = chipset;
        this.socket = socket;
        this.tipo_di_memoria = tipo_di_memoria;
        this.max_memory_size = max_memory_size;
        this.has_wifi = has_wifi;
        this.has_bluetooth = has_bluetooth;
        this.numero_porte_usb = numero_porte_usb;
        this.numero_pcie = numero_pcie;
        this.supporto_m2 = supporto_m2;
        this.lista_ram = lista_ram;
    }

    public void setFormato(Formato formato) {
        this.formato = formato;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public void setTipo_di_memoria(String tipo_di_memoria) {
        this.tipo_di_memoria = tipo_di_memoria;
    }

    public void setMax_memory_size(int max_memory_size) {
        this.max_memory_size = max_memory_size;
    }

    public void setHas_wifi(boolean has_wifi) {
        this.has_wifi = has_wifi;
    }

    public void setHas_bluetooth(boolean has_bluetooth) {
        this.has_bluetooth = has_bluetooth;
    }

    public void setNumero_porte_usb(int numero_porte_usb) {
        this.numero_porte_usb = numero_porte_usb;
    }

    public void setNumero_pcie(int numero_pcie) {
        this.numero_pcie = numero_pcie;
    }

    public void setSupporto_m2(boolean supporto_m2) {
        this.supporto_m2 = supporto_m2;
    }

    public void setLista_ram(Ram ram) {
        this.lista_ram.add(ram);
    }

}
