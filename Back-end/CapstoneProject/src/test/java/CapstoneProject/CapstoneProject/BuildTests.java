package CapstoneProject.CapstoneProject;

import CapstoneProject.CapstoneProject.Enum.Ruolo;
import CapstoneProject.CapstoneProject.build.BuildController;
import CapstoneProject.CapstoneProject.build.BuildSavePayload;
import CapstoneProject.CapstoneProject.build.BuildService;

import CapstoneProject.CapstoneProject.user.User;
import CapstoneProject.CapstoneProject.user.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = BuildController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {BuildController.class})
public class BuildTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BuildService buildService;
    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private BuildController buildController;

 private User mockUser = User.builder().id(1000).nome("prova").cognome("prova").email("prova@prova.com").password("prova").ruolo(Ruolo.USER).build();
    @Test
    public void creaBuild() throws Exception {
        BuildSavePayload buildSavePayload=new BuildSavePayload(List.of(2L,5L,10L));
        when(userService.getSingleUser(any(Long.class))).thenReturn(mockUser);
        ResultActions risp = mockMvc.perform(post("/builds/me")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buildSavePayload)));

        risp.andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    public void getAllBuilds() throws Exception {

        when(userService.getSingleUser(any(Long.class))).thenReturn(mockUser);
        ResultActions risp = mockMvc.perform(get("/builds/user_builds/me")
                .contentType(MediaType.APPLICATION_JSON));

        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteMyBuild() throws Exception {

        when(userService.getSingleUser(any(Long.class))).thenReturn(mockUser);
        ResultActions risp = mockMvc.perform(delete("/builds/cancella_build/1")
                .contentType(MediaType.APPLICATION_JSON));

        risp.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
