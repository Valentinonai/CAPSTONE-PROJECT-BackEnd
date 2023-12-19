package CapstoneProject.CapstoneProject;

import CapstoneProject.CapstoneProject.user.ModificaPasswordPayLoad;
import CapstoneProject.CapstoneProject.user.UserController;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {UserController.class})
public class UserTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserController userController;

    @Test
    public void getAllUsers() throws Exception {
        ResultActions risp=mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON));
        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getMyself() throws Exception {
        ResultActions risp=mockMvc.perform(get("/users/me").contentType(MediaType.APPLICATION_JSON));
        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getLikes() throws Exception {
        ResultActions risp=mockMvc.perform(get("/users/likes").contentType(MediaType.APPLICATION_JSON));
        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void getSingleUser() throws Exception {
        ResultActions risp=mockMvc.perform(get("/users/1").contentType(MediaType.APPLICATION_JSON));
        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteMyself() throws Exception {
        ResultActions risp=mockMvc.perform(delete("/users/elimina/me").contentType(MediaType.APPLICATION_JSON));
        risp.andExpect(MockMvcResultMatchers.status().isNoContent());
    }
    @Test
    public void cancellaUtente() throws Exception {
        ResultActions risp=mockMvc.perform(delete("/users/elimina/1").contentType(MediaType.APPLICATION_JSON));
        risp.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void aggiungiPreferiti() throws Exception {
        ResultActions risp=mockMvc.perform(put("/users/aggiungi_preferiti/me?item_id=1").contentType(MediaType.APPLICATION_JSON));
        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void modificaPassword() throws Exception {
        ModificaPasswordPayLoad modificaPasswordPayLoad=new ModificaPasswordPayLoad("");
        ResultActions risp=mockMvc.perform(put("/users/me").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(modificaPasswordPayLoad)));
        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
