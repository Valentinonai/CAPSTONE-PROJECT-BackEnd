package CapstoneProject.CapstoneProject.chat;

import CapstoneProject.CapstoneProject.exception.BadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/chat")
public class chatController {
    @Autowired
    private ChatService chatService;

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ChatPayload richiestaChat(@RequestBody @Validated ChatPayload message, BindingResult validation) throws IOException {
        if(validation.hasErrors())
        {
            throw new BadRequest(validation.getAllErrors());
        }
        return chatService.domanda(message);
    }
}
