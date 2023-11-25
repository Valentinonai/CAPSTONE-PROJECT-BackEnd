package CapstoneProject.CapstoneProject.security;


import CapstoneProject.CapstoneProject.exception.Unauthorized;
import CapstoneProject.CapstoneProject.user.User;
import CapstoneProject.CapstoneProject.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String autenticazione(LogInDTO body){
        User u=userService.findByEmail(body.email());
        if(passwordEncoder.matches(body.password(),u.getPassword())){
            return jwtTools.creaToken(u);
        }
        else{
            throw new Unauthorized("Password errrata");
        }
    }
}
