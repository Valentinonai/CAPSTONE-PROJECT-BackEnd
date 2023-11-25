package CapstoneProject.CapstoneProject.item;

import CapstoneProject.CapstoneProject.alimentatore.AlimentatorePayLoad;
import CapstoneProject.CapstoneProject.boxCase.BoxCasePayLoad;
import CapstoneProject.CapstoneProject.cpu.CpuPayLoad;
import CapstoneProject.CapstoneProject.exception.BadRequest;
import CapstoneProject.CapstoneProject.hard_disk.HardDisskPayLoad;
import CapstoneProject.CapstoneProject.ram.RamPayLoad;
import CapstoneProject.CapstoneProject.scheda_grafica.SchedaGraficaPayload;
import CapstoneProject.CapstoneProject.scheda_madre.SchedaMadrePayLoad;
import CapstoneProject.CapstoneProject.user.User;
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Item> getAllItems(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String order){
        return itemService.getAllItems(page,size>20?10:size,order);
    }

    @GetMapping("/{id}")
    @PreAuthorize("('ADMIN')")
    public Item getSingleItem(@PathVariable long id){
        return itemService.getSingleItem(id);
    }

    @DeleteMapping("/cancella_item/{id}")
    @PreAuthorize("('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancellaItem(@PathVariable long id){
        itemService.deleteItem(id);
    }

    @PutMapping("/carica_img/{id}")
    @PreAuthorize("('ADMIN')")
    public Item caricaImmagine(@RequestParam("item_img")MultipartFile file,@PathVariable long id) throws IOException {
       return itemService.uploadImg(file, id);
    }

    @PutMapping("/modifica_prezzo/{id}")
    @PreAuthorize("('ADMIN')")
    public Item modificaPrezzoItem(@RequestBody @Validated ItemPayLoadPrezzo body,BindingResult validation,@PathVariable long id ){
        if(validation.hasErrors())
        {
            throw new BadRequest(validation.getAllErrors());
        }
        return itemService.modificaPrezzo(body,id);
    }

    @PutMapping("/modifica_quantità/{id}")
    @PreAuthorize("('ADMIN')")
    public Item modificaQuantitaItem(@RequestBody @Validated ItemPayLoadQuantita body,BindingResult validation,@PathVariable long id ){
        if(validation.hasErrors())
        {
            throw new BadRequest(validation.getAllErrors());
        }
        return itemService.modificaQuantita(body,id);
    }

    //--------------------EndPoint creazione-------------------------

    @PostMapping("/scheda_madre")
    @PreAuthorize("('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Item creaSchedaMadre(@RequestBody @Validated SchedaMadrePayLoad body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }
        return itemService.saveSchedaMadre(body);
    }
    @PostMapping("/cpu")
    @PreAuthorize("('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Item creaCpu(@RequestBody @Validated CpuPayLoad body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }
        return itemService.saveCpu(body);
    }

    @PostMapping("/ram")
    @PreAuthorize("('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Item creaRam(@RequestBody @Validated RamPayLoad body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }
        return itemService.saveRam(body);
    }
    @PostMapping("/case")
    @PreAuthorize("('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Item creaBoxCase(@RequestBody @Validated BoxCasePayLoad body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }
        return itemService.saveBoxCase(body);
    }
    @PostMapping("/scheda_grafica")
    @PreAuthorize("('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Item creaSchedaGrafica(@RequestBody @Validated SchedaGraficaPayload body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }
        return itemService.saveSchedaGrafica(body);
    }

    @PostMapping("/hard_disk")
    @PreAuthorize("('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Item creaHardDisk(@RequestBody @Validated HardDisskPayLoad body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }
        return itemService.saveHardDisk(body);
    }
    @PostMapping("/ventole")
    @PreAuthorize("('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Item creaVentole(@RequestBody @Validated VentolePayLoad body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }
        return itemService.saveVentola(body);
    }

    @PostMapping("/alimentatore")
    @PreAuthorize("('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Item creaAlimentatore(@RequestBody @Validated AlimentatorePayLoad body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }
        return itemService.saveAlimentatore(body);
    }
}
