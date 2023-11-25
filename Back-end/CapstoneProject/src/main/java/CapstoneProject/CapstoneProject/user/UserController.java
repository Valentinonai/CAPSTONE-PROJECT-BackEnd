package CapstoneProject.CapstoneProject.user;

import CapstoneProject.CapstoneProject.exception.BadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String order){
        return userService.getAllUsers(page,size>20?10:size,order);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User getSingleUser(@PathVariable long id){
        return userService.getSingleUser(id);
    }
    @PutMapping("/me")
    public User modificaPasswordUser(@AuthenticationPrincipal User u, @RequestBody @Validated ModificaPasswordPayLoad body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }

        return userService.modificaPassword(body,u.getId());

    }

    @DeleteMapping("/elimina/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void eliminaUtente(@PathVariable long id){
        userService.deleteUser(id);
    }


    @PutMapping("/carica_immagine/me")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public User caricaImmagine(@AuthenticationPrincipal User u, @RequestParam("user_img") MultipartFile file) throws IOException {
       return userService.uploadImg(file,u.getId());
    }

    @DeleteMapping("/elimina/me")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void canccellaBuild(@AuthenticationPrincipal User u){
        userService.deleteUser(u.getId());
    }


}
