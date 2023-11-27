package CapstoneProject.CapstoneProject.order;

import CapstoneProject.CapstoneProject.build.Build;
import CapstoneProject.CapstoneProject.build.BuildSavePayload;
import CapstoneProject.CapstoneProject.build.BuildService;
import CapstoneProject.CapstoneProject.exception.BadRequest;
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

import java.util.List;

@RestController
@RequestMapping("/ordini")
public class OrderController {
    @Autowired
    private OrdineService ordineService;
    @Autowired
    private UserService userService;

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Ordine> getAllOrdini(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String order){
        return ordineService.getAllOrdine(page,size>20?10:size,order);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Ordine getSingleOrdine(@PathVariable long id){
        return ordineService.getSingleOrdine(id);
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Page<Ordine> getUserOrdini(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String order,@AuthenticationPrincipal User u){
        return ordineService.getUserOrdini(page,size>20?10:size,order,u);
    }

    @PostMapping("/me")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Ordine creaOrdine(@RequestBody @Validated OrdinePayLaod body, BindingResult validation, @AuthenticationPrincipal User u){
        User user=userService.getSingleUser(u.getId());
        if(validation.hasErrors())
        {
            throw new BadRequest(validation.getAllErrors());
        }
        return ordineService.saveOrdine(body,u.getId());
    }

    @DeleteMapping("/elimina_ordine/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void cancellaOrdine(@PathVariable long id){
        ordineService.eliminaOrdine(id);
    }

}