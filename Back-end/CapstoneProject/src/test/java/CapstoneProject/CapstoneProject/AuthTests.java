package CapstoneProject.CapstoneProject;
import CapstoneProject.CapstoneProject.build.BuildController;
import CapstoneProject.CapstoneProject.security.LogInDTO;
import CapstoneProject.CapstoneProject.security.SecurityController;
import CapstoneProject.CapstoneProject.security.SecurityService;
import CapstoneProject.CapstoneProject.user.UserController;
import CapstoneProject.CapstoneProject.user.UserPayLoad;
import CapstoneProject.CapstoneProject.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = SecurityController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {SecurityController.class})
public class AuthTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private SecurityController securityController;

    @Test
    public void createUser() throws Exception {

        UserPayLoad userPayLoad=new UserPayLoad("prova","prova","prova@prova.com","prova");
        ResultActions resp = mockMvc.perform(post("/auth/signup").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(userPayLoad)));

        resp.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void loginUser() throws Exception {

        LogInDTO logInDTO=new LogInDTO("prova","prova");
        ResultActions resp = mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(logInDTO)));

        resp.andExpect(MockMvcResultMatchers.status().isOk());
    }

}
