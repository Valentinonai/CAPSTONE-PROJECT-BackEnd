package CapstoneProject.CapstoneProject.item;

import CapstoneProject.CapstoneProject.alimentatore.Alimentatore;
import CapstoneProject.CapstoneProject.alimentatore.AlimentatorePayLoad;
import CapstoneProject.CapstoneProject.boxCase.BoxCase;
import CapstoneProject.CapstoneProject.boxCase.BoxCasePayLoad;
import CapstoneProject.CapstoneProject.cpu.Cpu;
import CapstoneProject.CapstoneProject.cpu.CpuPayLoad;
import CapstoneProject.CapstoneProject.exception.BadRequest;
import CapstoneProject.CapstoneProject.hard_disk.HardDisskPayLoad;
import CapstoneProject.CapstoneProject.ram.Ram;
import CapstoneProject.CapstoneProject.ram.RamPayLoad;
import CapstoneProject.CapstoneProject.scheda_grafica.SchedaGraficaPayload;
import CapstoneProject.CapstoneProject.scheda_madre.SchedaMadrePayLoad;
import CapstoneProject.CapstoneProject.ventole.VentolePayLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    //------------------------EndPoint per Items-------------------------------
    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Page<Item> getAllItems(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String order){
        return itemService.getAllItems(page,size>20?10:size,order);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Item getSingleItem(@PathVariable long id){
        return itemService.getSingleItem(id);
    }

    @DeleteMapping("/cancella_item/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancellaItem(@PathVariable long id){
        itemService.deleteItem(id);
    }

    @PutMapping("/carica_img/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Item caricaImmagine(@RequestParam("item_img")MultipartFile file,@PathVariable long id) throws IOException {
       return itemService.uploadImg(file, id);
    }

    @PutMapping("/modifica_prezzo/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Item modificaPrezzoItem(@RequestBody @Validated ItemPayLoadPrezzo body,BindingResult validation,@PathVariable long id ){
        if(validation.hasErrors())
        {
            throw new BadRequest(validation.getAllErrors());
        }
        return itemService.modificaPrezzo(body,id);
    }

    @PutMapping("/modifica_quantità/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Item modificaQuantitaItem(@RequestBody @Validated ItemPayLoadQuantita body,BindingResult validation,@PathVariable long id ){
        if(validation.hasErrors())
        {
            throw new BadRequest(validation.getAllErrors());
        }
        return itemService.modificaQuantita(body,id);
    }

    //--------------------EndPoint creazione-------------------------

    @PostMapping("/scheda_madre")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Item creaSchedaMadre(@RequestBody @Validated SchedaMadrePayLoad body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }
        return itemService.saveSchedaMadre(body);
    }
    @PostMapping("/cpu")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Item creaCpu(@RequestBody @Validated CpuPayLoad body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }
        return itemService.saveCpu(body);
    }

    @PostMapping("/ram")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Item creaRam(@RequestBody @Validated RamPayLoad body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }
        return itemService.saveRam(body);
    }
    @PostMapping("/case")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Item creaBoxCase(@RequestBody @Validated BoxCasePayLoad body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }
        return itemService.saveBoxCase(body);
    }
    @PostMapping("/scheda_grafica")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Item creaSchedaGrafica(@RequestBody @Validated SchedaGraficaPayload body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }
        return itemService.saveSchedaGrafica(body);
    }

    @PostMapping("/hard_disk")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Item creaHardDisk(@RequestBody @Validated HardDisskPayLoad body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }
        return itemService.saveHardDisk(body);
    }
    @PostMapping("/ventole")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Item creaVentole(@RequestBody @Validated VentolePayLoad body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }
        return itemService.saveVentola(body);
    }

    @PostMapping("/alimentatore")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Item creaAlimentatore(@RequestBody @Validated AlimentatorePayLoad body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }
        return itemService.saveAlimentatore(body);
    }

    //---------------------------EndPoint per queries--------------------------

    @GetMapping("/cpu_socket")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Page<Cpu> getCpuBySocket(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String order,@RequestParam String socket){
        if(socket!=null)    return itemService.getCpuBySocket(page,size>20?10:size,order,socket);
        else throw new BadRequest("Parametro socket non valido");
    }

    @GetMapping("/ram_schedamadre")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Page<Ram> getRamBySchedaMadre(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String order,@RequestParam String scheda_madre_id){
        if(scheda_madre_id!=null)    return itemService.getRamBySchedaMadre(page,size>20?10:size,order,Long.parseLong(scheda_madre_id));
        else throw new BadRequest("Parametro scheda madre id non valido");
    }

    @GetMapping("/case")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Page<BoxCase> getBoxCaseByFormato(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String order, @RequestParam String formato){
        if(formato!=null)    return itemService.getBoxCaseByFormato(page,size>20?10:size,order,formato);
        else throw new BadRequest("Parametro formato non valido");
    }
    @GetMapping("/alimentatore")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Page<Alimentatore> getAlimentatoreByPotenza(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String order, @RequestParam String power){
        if(power!=null)    return itemService.getAlimentatoreByPotenza(page,size>20?10:size,order,Integer.parseInt(power));
        else throw new BadRequest("Parametro power non valido");
    }

    @GetMapping("/categoria")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Page<Item> getAllByCategoria(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String order,@RequestParam String categoria){
        if(categoria!=null)    return itemService.getByCategoria(page,size>20?10:size,order,categoria);
        else throw new BadRequest("Parametro categoria non valido");
    }
}
