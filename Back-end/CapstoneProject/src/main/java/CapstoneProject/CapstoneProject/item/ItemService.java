package CapstoneProject.CapstoneProject.item;

import CapstoneProject.CapstoneProject.Enum.Categoria;
import CapstoneProject.CapstoneProject.Enum.Formato;
import CapstoneProject.CapstoneProject.Enum.Stato;
import CapstoneProject.CapstoneProject.alimentatore.Alimentatore;
import CapstoneProject.CapstoneProject.alimentatore.AlimentatorePayLoad;
import CapstoneProject.CapstoneProject.alimentatore.AlimentatoreRepository;
import CapstoneProject.CapstoneProject.boxCase.BoxCase;
import CapstoneProject.CapstoneProject.boxCase.BoxCasePayLoad;
import CapstoneProject.CapstoneProject.boxCase.BoxCaseRepository;
import CapstoneProject.CapstoneProject.cpu.Cpu;
import CapstoneProject.CapstoneProject.cpu.CpuPayLoad;
import CapstoneProject.CapstoneProject.cpu.CpuRepository;
import CapstoneProject.CapstoneProject.exception.BadRequest;
import CapstoneProject.CapstoneProject.exception.NotFoundException;
import CapstoneProject.CapstoneProject.exception.ProdottoEsauritoException;
import CapstoneProject.CapstoneProject.hard_disk.HardDisk;
import CapstoneProject.CapstoneProject.hard_disk.HardDisskPayLoad;
import CapstoneProject.CapstoneProject.ram.Ram;
import CapstoneProject.CapstoneProject.ram.RamPayLoad;
import CapstoneProject.CapstoneProject.ram.RamRepository;
import CapstoneProject.CapstoneProject.scheda_grafica.SchedaGrafica;
import CapstoneProject.CapstoneProject.scheda_grafica.SchedaGraficaPayload;
import CapstoneProject.CapstoneProject.scheda_madre.SchedaMadre;
import CapstoneProject.CapstoneProject.scheda_madre.SchedaMadrePayLoad;
import CapstoneProject.CapstoneProject.cloudinary.CloudinaryService;
import CapstoneProject.CapstoneProject.ventole.Ventola;
import CapstoneProject.CapstoneProject.ventole.VentolePayLoad;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CpuRepository cpuRepository;
    @Autowired
    private RamRepository ramRepository;
    @Autowired
    private BoxCaseRepository boxCaseRepository;
    @Autowired
    private AlimentatoreRepository alimentatoreRepository;

    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private Cloudinary cloudinary;

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
        if(body.formato().equalsIgnoreCase("ATX") ||
        body.formato().equalsIgnoreCase("MICRO_ATX") || body.formato().equalsIgnoreCase("MINI_ITX") ){
            SchedaMadre sm=new SchedaMadre(body.marca(), body.nome(), body.descrizione(), body.prezzo(), body.data_di_rilascio(), body.potenza_di_picco(),body.quantita(), Categoria.SCHEDA_MADRE,Formato.valueOf(body.formato().toUpperCase()), body.chipset(), body.socket(), body.tipo_di_memoria(), body.max_memory_size(), body.has_wifi(), body.has_bluetooth(), body.numero_porte_usb(), body.numero_pcie(), body.supporto_m2());
            return itemRepository.save(sm);
        }
     else throw new BadRequest("Formato non disponibile");
    }

    public Item saveCpu(CpuPayLoad body){
        Cpu c=new Cpu(body.marca(), body.nome(), body.descrizione(), body.prezzo(), body.data_di_rilascio(), body.potenza_di_picco(),body.quantita(), Categoria.CPU, body.socket(), body.numero_core(), body.numero_threads(), body.max_boost_clock(), body.cache_l2(), body.cache_l3(), body.max_temperatura(), body.grafica_integrata(), body.tipo_memoria_di_sistema());
    return itemRepository.save(c);
    }

    public Item saveRam(RamPayLoad body){
        Set<SchedaMadre> schedaMadreList=new HashSet<>();
        for(int i=0;i<body.lista_schedemadri_id().size();i++) {
            SchedaMadre schedaMadre=(SchedaMadre) getSingleItem(body.lista_schedemadri_id().get(i));
            schedaMadreList.add(schedaMadre);
        }
            Ram r=new Ram(body.marca(), body.nome(), body.descrizione(), body.prezzo(), body.data_di_rilascio(), body.potenza_di_picco(),body.quantita(), Categoria.RAM, body.tipo_di_memoria(), body.velocità(), body.dimensione(), schedaMadreList);
        return itemRepository.save(r);
    }

    public Item saveBoxCase(BoxCasePayLoad body){
        if(body.formato().equalsIgnoreCase("ATX") ||
                body.formato().equalsIgnoreCase("MICRO_ATX") || body.formato().equalsIgnoreCase("MINI_ITX") ) {
            BoxCase b = new BoxCase(body.marca(), body.nome(), body.descrizione(), body.prezzo(), body.data_di_rilascio(), body.potenza_di_picco(), body.quantita(), Categoria.CASE,Formato.valueOf(body.formato().toUpperCase()) , body.num_ventole(), body.larghezza(), body.altezza(), body.profondità());
            return itemRepository.save(b);
        }
        else throw new BadRequest("Formato non disponibile");
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
        i.setStato(Stato.INATTIVO);
        itemRepository.save(i);
//        itemRepository.delete(i);
    }

    //--------------------MODIFICA----------------------



    public Item scalaQuandita(long id){
        Item i=getSingleItem(id);
        if(i.getQuantità()>0){
            i.setQuantità(i.getQuantità()-1);
            return itemRepository.save(i);
        }
        else throw new ProdottoEsauritoException("Il prodotto "+i.getNome()+" è esaurito");

    }

    public Item modificaQuantita(ItemPayLoadQuantita body,long id){
        Item i=getSingleItem(id);
        i.setQuantità(body.quantita());
        return itemRepository.save(i);
    }

    public Item modificaPrezzo(ItemPayLoadPrezzo body,long id){
        Item i=getSingleItem(id);
        i.setPrezzo(body.prezzo());
        return itemRepository.save(i);
    }
   //--------------------UPLOAD IMMAGINE----------------------

    public Item uploadImg(MultipartFile file,long id) throws IOException {
        Item i=getSingleItem(id);
        if(!i.getImmagineUrl().equals("https://res.cloudinary.com/dzr77mvcs/image/upload/v1699804334/uzqt1xnviwyjlcxqnqka.webp"))
            cloudinaryService.deleteImageByUrl(i.getImmagineUrl());
            String url=(String)  cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
            i.setImmagineUrl(url);
            return itemRepository.save(i);

    }


    //------------------------QUERIES-----------------------------

    public Page<Cpu> getCpuBySocket(int page,int size,String order,String socket){
        Pageable p=PageRequest.of(page,size,Sort.by(order));
        return cpuRepository.findBySocket(p,socket);
    }

    public Page<Ram> getRamBySchedaMadre(int page,int size,String order,long schedaMadre_id){
        Pageable p=PageRequest.of(page,size,Sort.by(order));
//    return ramRepository.findBySchedaId(p,schedaMadre_id);
    return ramRepository.findBySchedaMadreId(p,schedaMadre_id);
    }
    public Page<BoxCase> getBoxCaseByFormato(int page,int size,String order,String tipo){
        Pageable p=PageRequest.of(page,size,Sort.by(order));
        if(tipo.equalsIgnoreCase("ATX") || tipo.equalsIgnoreCase("MICRO_ATX") || tipo.equalsIgnoreCase("MINI_ITX") )
        return boxCaseRepository.findByFormato(p,Formato.valueOf(tipo.toUpperCase()));
        else    throw new BadRequest("Formato scelto inesistente");
    }

    public Page<Alimentatore> getAlimentatoreByPotenza(int page,int size,String order,int power){
        Pageable p=PageRequest.of(page,size,Sort.by(order));
        return alimentatoreRepository.findByPotenza_max_erogataGreaterThan(p,power);

    }
    public Page<Item> getByCategoria(int page,int size,String order,String cat){
        if(cat.equalsIgnoreCase("SCHEDA_MADRE")||
                cat.equalsIgnoreCase("CPU")||
                cat.equalsIgnoreCase("RAM")||
                cat.equalsIgnoreCase("CASE")||
                cat.equalsIgnoreCase("SCHEDA_GRAFICA")||
                cat.equalsIgnoreCase("HARD_DISK")||
                cat.equalsIgnoreCase("VENTOLE")||
                cat.equalsIgnoreCase("ALIMENTATORE")){
            Pageable p=PageRequest.of(page,size,Sort.by(order));
            return itemRepository.findByCategoria(p,Categoria.valueOf(cat.toUpperCase()));
        }
        else throw new BadRequest("Categoria inserita inesistente");
    }
}
