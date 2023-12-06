package CapstoneProject.CapstoneProject.build;

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

@RestController
@RequestMapping("/builds")
public class BuildController {

    @Autowired
    private BuildService buildService;
    @Autowired
    private UserService userService;

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Build> getAllBuilds(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String order){
        return buildService.getAllBuilds(page,size>20?10:size,order);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Build getSingleBuild(@PathVariable long id){
        return buildService.getSingleBuild(id);
    }

    @GetMapping("/user_builds/me")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Page<Build> getAllBuilds(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String order,@AuthenticationPrincipal User u){
        return buildService.getAllBuildsAttive(page,size>20?10:size,order,u.getId());
    }
    @PostMapping("/me")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Build creaBuild(@RequestBody @Validated BuildSavePayload body, BindingResult validation, @AuthenticationPrincipal User u){
        User user=userService.getSingleUser(u.getId());
            if(validation.hasErrors())
            {
                throw new BadRequest(validation.getAllErrors());
            }
            return buildService.saveBuild(body,user);
        }
    @PutMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Build modificaBuild(@RequestBody @Validated BuildSavePayload body, BindingResult validation,@RequestParam long build_id){
        if(validation.hasErrors())
        {
            throw new BadRequest(validation.getAllErrors());
        }
        return buildService.modifyBuild(body,build_id);
    }
    @DeleteMapping("/cancella_build/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void canccellaBuild(@PathVariable long id){
        buildService.deleteBuild(id);
    }
    @DeleteMapping("/cancella_build/{id}/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public void canccellaMyBuild(@PathVariable long id,@AuthenticationPrincipal User u){
        buildService.deleteMyBuild(id,u.getId());
    }

}

