package CapstoneProject.CapstoneProject.security;

import CapstoneProject.CapstoneProject.exception.BadRequest;
import CapstoneProject.CapstoneProject.user.User;
import CapstoneProject.CapstoneProject.user.UserPayLoad;
import CapstoneProject.CapstoneProject.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class SecurityController {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public TokenPayload login(@RequestBody LogInDTO body){
            return new TokenPayload(securityService.autenticazione(body)) ;
    }
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenPayload createUser(@RequestBody @Validated UserPayLoad userPayload, BindingResult validation){
        if(validation.hasErrors()){
            throw new BadRequest(validation.getAllErrors());
        }
         User u=userService.saveUser(userPayload);
        return new TokenPayload(securityService.autenticazione(new LogInDTO(u.getEmail(),userPayload.password())));
    }
}
