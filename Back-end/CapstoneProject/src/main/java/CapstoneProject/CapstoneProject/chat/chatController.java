package CapstoneProject.CapstoneProject.chat;

import CapstoneProject.CapstoneProject.chat.ChatPayload;
import CapstoneProject.CapstoneProject.chat.ChatService;
import CapstoneProject.CapstoneProject.chat.ThreadPayload;
import CapstoneProject.CapstoneProject.exception.BadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/chat")
public class chatController {
    @Autowired
    private ChatService chatService;

    @PostMapping("/{thread}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ChatPayload richiestaChat(@RequestBody @Validated ChatPayload message, BindingResult validation, @PathVariable String thread) throws IOException {
        if(validation.hasErrors())
        {
            throw new BadRequest(validation.getAllErrors());
        }
        return chatService.domanda(message,thread);
    }
    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ThreadPayload apriChat() throws IOException {

        return chatService.apriThread();
    }
    @PostMapping("/chiudi_thread/{thread}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public void  apriChat(@PathVariable String thread) throws IOException {
        chatService.chiudiThread(thread);
    }
}
