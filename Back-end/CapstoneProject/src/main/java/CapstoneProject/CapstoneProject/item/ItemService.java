package CapstoneProject.CapstoneProject.item;

import CapstoneProject.CapstoneProject.Enum.Categoria;
import CapstoneProject.CapstoneProject.alimentatore.Alimentatore;
import CapstoneProject.CapstoneProject.alimentatore.AlimentatorePayLoad;
import CapstoneProject.CapstoneProject.boxCase.BoxCase;
import CapstoneProject.CapstoneProject.boxCase.BoxCasePayLoad;
import CapstoneProject.CapstoneProject.cpu.Cpu;
import CapstoneProject.CapstoneProject.cpu.CpuPayLoad;
import CapstoneProject.CapstoneProject.exception.NotFoundException;
import CapstoneProject.CapstoneProject.exception.ProdottoEsauritoException;
import CapstoneProject.CapstoneProject.hard_disk.HardDisk;
import CapstoneProject.CapstoneProject.hard_disk.HardDisskPayLoad;
import CapstoneProject.CapstoneProject.ram.Ram;
import CapstoneProject.CapstoneProject.ram.RamPayLoad;
import CapstoneProject.CapstoneProject.scheda_grafica.SchedaGrafica;
import CapstoneProject.CapstoneProject.scheda_grafica.SchedaGraficaPayload;
import CapstoneProject.CapstoneProject.scheda_madre.SchedaMadre;
import CapstoneProject.CapstoneProject.scheda_madre.SchedaMadrePayLoad;
import CapstoneProject.CapstoneProject.ventole.Ventola;
import CapstoneProject.CapstoneProject.ventole.VentolePayLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    //-----------------------GET------------------------------

    public Page<Item> getAllItems(int page,int size,String order){
        Pageable p= PageRequest.of(page,size, Sort.by(order));
        return itemRepository.findAll(p);
    }
    public Item getSingleItem(long id){
        return itemRepository.findById(id).orElseThrow(()->new NotFoundException("Elemento non trovato"));
    }

    //----------------------SAVE-----------------------
    public Item saveSchedaMadre(SchedaMadrePayLoad body){
        SchedaMadre sm=new SchedaMadre(body.marca(), body.nome(), body.descrizione(), body.prezzo(), body.data_di_rilascio(), body.potenza_di_picco(),body.quantita(), Categoria.SCHEDA_MADRE,body.formato(), body.chipset(), body.socket(), body.tipo_di_memoria(), body.max_memory_size(), body.has_wifi(), body.has_bluetooth(), body.numero_porte_usb(), body.numero_pcie(), body.supporto_m2());
        return itemRepository.save(sm);
    }

    public Item saveCpu(CpuPayLoad body){
        Cpu c=new Cpu(body.marca(), body.nome(), body.descrizione(), body.prezzo(), body.data_di_rilascio(), body.potenza_di_picco(),body.quantita(), Categoria.CPU, body.socket(), body.numero_core(), body.numero_threads(), body.max_boost_clock(), body.cache_l2(), body.cache_l3(), body.max_temperatura(), body.grafica_integrata(), body.tipo_memoria_di_sistema());
    return itemRepository.save(c);
    }

    public Item saveRam(RamPayLoad body){
        Ram r=new Ram(body.marca(), body.nome(), body.descrizione(), body.prezzo(), body.data_di_rilascio(), body.potenza_di_picco(),body.quantita(), Categoria.RAM, body.tipo_di_memoria(), body.velocità(), body.dimensione(), body.lista_schedemadri());
        return itemRepository.save(r);
    }

    public Item saveBoxCase(BoxCasePayLoad body){
        BoxCase b=new BoxCase(body.marca(), body.nome(), body.descrizione(), body.prezzo(), body.data_di_rilascio(), body.potenza_di_picco(),body.quantita(), Categoria.CASE,body.formato(), body.num_ventole(), body.larghezza(), body.altezza(), body.profondità());
        return itemRepository.save(b);
    }

    public Item saveAlimentatore(AlimentatorePayLoad body){
        Alimentatore a=new Alimentatore(body.marca(), body.nome(), body.descrizione(), body.prezzo(), body.data_di_rilascio(), body.potenza_di_picco(),body.quantita(), Categoria.ALIMENTATORE, body.potenza_max_erogata(), body.modulare(), body.peso());
        return itemRepository.save(a);
    }

    public Item saveHardDisk(HardDisskPayLoad body){
        HardDisk h=new HardDisk(body.marca(), body.nome(), body.descrizione(), body.prezzo(), body.data_di_rilascio(), body.potenza_di_picco(),body.quantita(), Categoria.HARD_DISK, body.m2(), body.capacita());
        return itemRepository.save(h);
    }

    public Item saveSchedaGrafica(SchedaGraficaPayload body){
        SchedaGrafica sg=new SchedaGrafica(body.marca(), body.nome(), body.descrizione(), body.prezzo(), body.data_di_rilascio(), body.potenza_di_picco(),body.quantita(), Categoria.SCHEDA_GRAFICA, body.larghezza(), body.lunghezza(), body.boost_clock(), body.dimensione_memoria());
        return itemRepository.save(sg);
    }
    public Item saveVentola(VentolePayLoad body){
        Ventola v=new Ventola(body.marca(), body.nome(), body.descrizione(), body.prezzo(), body.data_di_rilascio(), body.potenza_di_picco(),body.quantita(), Categoria.VENTOLE, body.rpm_min(), body.rpm_max(), body.pwm(), body.dimensione(), body.pezzi_per_pacco());
        return itemRepository.save(v);
    }

    //---------------------DELETE--------------------------

    public void deleteItem(long id){
        Item i=getSingleItem(id);
        itemRepository.delete(i);
    }

    //--------------------MODIFICA----------------------



    public Item modificaQuandità(long id){
        Item i=getSingleItem(id);
        if(i.getQuantità()>0){
            i.setQuantità(i.getQuantità()-1);
            return itemRepository.save(i);
        }
        else throw new ProdottoEsauritoException("Il prodotto "+i.getNome()+" è esaurito");

    }


    //!!!!!!!!!!!!!!!!!!!!!!!!! MODIFICA IMMAGINE DA FARE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

}
