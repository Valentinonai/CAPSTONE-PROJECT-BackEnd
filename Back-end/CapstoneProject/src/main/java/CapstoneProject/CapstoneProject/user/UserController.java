package CapstoneProject.CapstoneProject.user;

import CapstoneProject.CapstoneProject.exception.BadRequest;
import CapstoneProject.CapstoneProject.exception.ListErrorsPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PutMapping("/me")
    public User modificaPasswordUser(@AuthenticationPrincipal User u, @RequestBody @Validated ModificaPasswordPayLoad body, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }

        return userService.modificaPassword(body,u.getId());

    }

}
