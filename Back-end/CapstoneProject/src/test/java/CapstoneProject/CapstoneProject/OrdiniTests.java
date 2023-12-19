package CapstoneProject.CapstoneProject;


import CapstoneProject.CapstoneProject.order.OrderController;

import CapstoneProject.CapstoneProject.order.OrdineService;

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

}
