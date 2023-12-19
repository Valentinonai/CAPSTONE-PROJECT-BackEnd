package CapstoneProject.CapstoneProject;


import CapstoneProject.CapstoneProject.Enum.Ruolo;
import CapstoneProject.CapstoneProject.order.OrderController;

import CapstoneProject.CapstoneProject.order.OrdinePayLaod;
import CapstoneProject.CapstoneProject.order.OrdineService;

import CapstoneProject.CapstoneProject.user.User;
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


import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;




import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = OrderController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {OrderController.class})
public class OrdiniTests {


    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private OrdineService ordineService;
    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private OrderController orderController;

    @Test
    public void getAllOrdini() throws Exception {

        ResultActions risp= mockMvc.perform(get("/ordini").contentType(MediaType.APPLICATION_JSON));

        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void controllaElementiVenduti() throws Exception {

        ResultActions risp= mockMvc.perform(get("/ordini/totale/3").contentType(MediaType.APPLICATION_JSON));

        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void getSingleOrdine() throws Exception {

        ResultActions risp= mockMvc.perform(get("/ordini/3").contentType(MediaType.APPLICATION_JSON));

        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void modificaStatoOrdine() throws Exception {

        ResultActions risp= mockMvc.perform(put("/ordini/14?stato=SPEDITO").contentType(MediaType.APPLICATION_JSON));

        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void creaOrdine() throws Exception {
        OrdinePayLaod ordinePayLaod = new OrdinePayLaod((List.of(1L, 2L, 3L)), (List.of(2L, 4L, 5L)));
        User mockUser = User.builder().id(1000).nome("prova").cognome("prova").email("sasdad@d.ds").password("ppapa").ruolo(Ruolo.USER).build();
        when(userService.getSingleUser(any(Long.class))).thenReturn(mockUser);
        ResultActions risp = mockMvc.perform(post("/ordini/me")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ordinePayLaod)));

        risp.andExpect(MockMvcResultMatchers.status().isCreated());
    }

}
