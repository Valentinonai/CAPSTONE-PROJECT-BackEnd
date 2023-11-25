package CapstoneProject.CapstoneProject.carta_di_credito;

import CapstoneProject.CapstoneProject.exception.BadRequest;
import CapstoneProject.CapstoneProject.exception.Unauthorized;
import CapstoneProject.CapstoneProject.indirizzo_di_spedizione.IndirizzoDiSpedizione;
import CapstoneProject.CapstoneProject.indirizzo_di_spedizione.IndirizzoDiSpedizionePayLoad;
import CapstoneProject.CapstoneProject.indirizzo_di_spedizione.ModificaIndirizzoPayLoad;
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
@RequestMapping("/carte_di_credito")
public class CartaDiCreditoController {
    @Autowired
    private CartaDiCreditoService cartaDiCreditoService;
    @Autowired
    private UserService userService;

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<CartaDiCredito> getAllCarte(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String order){
        return cartaDiCreditoService.getAllCarteDiCredito(page,size>20?10:size,order);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CartaDiCredito getSingleCarta(@PathVariable long id){
        return cartaDiCreditoService.getSingleCartaDiCredito(id);
    }
    @PostMapping("/me")
    @ResponseStatus(HttpStatus.CREATED)
    public CartaDiCredito aggiungiIndirizzo(@RequestBody @Validated CartaDiCreditoPayLoad body, BindingResult validation, @AuthenticationPrincipal User u){
        User user=userService.getSingleUser(u.getId());
        if(user.getCartaDiCredito()==null)
        {
            if(validation.hasErrors())
            {
                throw new BadRequest(validation.getAllErrors());
            }
            return cartaDiCreditoService.saveCartaDiCredito(body,user);
        }else {
            throw new Unauthorized("Hai già un indirizzo associato non puoi crearne uno nuovo");
        }

    }

    @PutMapping("/modifica_carta/me")
    public  CartaDiCredito modificaIndirizzo(@RequestBody ModificaCarta body, @AuthenticationPrincipal User u){
        return cartaDiCreditoService.modifyCartaDiCredito(body,u);
    }
}
