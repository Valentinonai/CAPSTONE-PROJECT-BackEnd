package CapstoneProject.CapstoneProject.indirizzo_di_spedizione;

import CapstoneProject.CapstoneProject.exception.BadRequest;
import CapstoneProject.CapstoneProject.exception.Unauthorized;
import CapstoneProject.CapstoneProject.user.User;
import CapstoneProject.CapstoneProject.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/indirizzi")
public class IndirizzoController {
    @Autowired
    private IndirizzoDiSpedizioneService indirizzoDiSpedizioneService;
    @Autowired
    private UserService userService;

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<IndirizzoDiSpedizione> getAllIndirizzi(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "id") String order){
        return indirizzoDiSpedizioneService.getAllIndirizzi(page,size>20?10:size,order);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public IndirizzoDiSpedizione getSingleIndirizzo(@PathVariable long id){
        return indirizzoDiSpedizioneService.getSingleIndirizzo(id);
    }
    @PostMapping("/me")
    @ResponseStatus(HttpStatus.CREATED)
    public IndirizzoDiSpedizione aggiungiIndirizzo(@RequestBody @Validated IndirizzoDiSpedizionePayLoad body, BindingResult validation,@AuthenticationPrincipal User u){
        User user=userService.getSingleUser(u.getId());
        if(user.getIndirizzoSpedizione()==null)
        {
            if(validation.hasErrors())
            {
                throw new BadRequest(validation.getAllErrors());
            }
            return indirizzoDiSpedizioneService.saveIndirizzo(body,user);
        }else {
            throw new Unauthorized("Hai già un indirizzo associato non puoi crearne uno nuovo");
        }

    }

    @PutMapping("/modifica_indirizzo/me")
    public  IndirizzoDiSpedizione modificaIndirizzo(@RequestBody ModificaIndirizzoPayLoad body, @AuthenticationPrincipal User u){
       return indirizzoDiSpedizioneService.modifyIndirizzo(body,u);
    }
}
