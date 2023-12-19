package CapstoneProject.CapstoneProject;

import CapstoneProject.CapstoneProject.alimentatore.AlimentatorePayLoad;
import CapstoneProject.CapstoneProject.boxCase.BoxCasePayLoad;
import CapstoneProject.CapstoneProject.cpu.CpuPayLoad;
import CapstoneProject.CapstoneProject.hard_disk.HardDisskPayLoad;
import CapstoneProject.CapstoneProject.item.ItemController;
import CapstoneProject.CapstoneProject.item.ItemPayLoadPrezzo;
import CapstoneProject.CapstoneProject.item.ItemPayLoadQuantita;
import CapstoneProject.CapstoneProject.item.ItemService;
import CapstoneProject.CapstoneProject.ram.RamPayLoad;
import CapstoneProject.CapstoneProject.scheda_grafica.SchedaGraficaPayload;
import CapstoneProject.CapstoneProject.scheda_madre.SchedaMadrePayLoad;
import CapstoneProject.CapstoneProject.ventole.VentolePayLoad;
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

import java.time.LocalDate;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = ItemController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {ItemController.class})
public class ItemTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private  ItemService itemService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllItems() throws Exception {

        ResultActions risp= mockMvc.perform(get("/items").contentType(MediaType.APPLICATION_JSON));

        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void getItemsById() throws Exception {

        ResultActions risp= mockMvc.perform(get("/items/5").contentType(MediaType.APPLICATION_JSON));

        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void getCpuBySocket() throws Exception {

        ResultActions risp= mockMvc.perform(get("/items/cpu_socket?socket=amd").contentType(MediaType.APPLICATION_JSON));

        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void getRamByCompatibilità() throws Exception {

        ResultActions risp= mockMvc.perform(get("/items/ram_schedamadre?scheda_madre_id=2").contentType(MediaType.APPLICATION_JSON));

        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void getCaseByTipo() throws Exception {

        ResultActions risp= mockMvc.perform(get("/items/case?formato=atx").contentType(MediaType.APPLICATION_JSON));

        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void getAlimentatoreByPower() throws Exception {

        ResultActions risp= mockMvc.perform(get("/items/alimentatore?power=799").contentType(MediaType.APPLICATION_JSON));

        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void getAllByCategoria() throws Exception {

        ResultActions risp= mockMvc.perform(get("/items/categoria?categoria=scheda_madre").contentType(MediaType.APPLICATION_JSON));

        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void getVentoleByDimensione() throws Exception {

        ResultActions risp= mockMvc.perform(get("/items/ventole?dimensione=120").contentType(MediaType.APPLICATION_JSON));

        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void getHardDisk() throws Exception {

        ResultActions risp= mockMvc.perform(get("/items/hard_disk?m2=true").contentType(MediaType.APPLICATION_JSON));

        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void modificaPrezzo() throws Exception {

        ItemPayLoadPrezzo itemPayLoadPrezzo=new ItemPayLoadPrezzo(10);

        ResultActions risp= mockMvc.perform(put("/items/modifica_prezzo/4").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(itemPayLoadPrezzo)));

        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void modificaQuantità() throws Exception {

        ItemPayLoadQuantita itemPayLoadQuantita=new ItemPayLoadQuantita(2);

        ResultActions risp= mockMvc.perform(put("/items/modifica_quantità/4").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(itemPayLoadQuantita)));

        risp.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void creaSchedaMadre() throws Exception {

        SchedaMadrePayLoad schedaMadrePayLoad=new SchedaMadrePayLoad("Asus","Rog Strix Z790-E","Stile e sostanza si uniscono come mai prima d'ora nella ROG Strix Z790-E II, ricca di funzionalità.",669, LocalDate.of(2023,11,01),120,100, "ATX","Intel Z790","Intel LGA 1700","DDR5",192,true,true,12,3,true);

        ResultActions risp= mockMvc.perform(post("/items/scheda_madre").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(schedaMadrePayLoad)));

        risp.andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    public void creaCpu() throws Exception {

        CpuPayLoad cpuPayLoad= new CpuPayLoad("Intel","Intel® Core™ i7 processor 14700K",
                "Intel® Core™ i7 Processors (14th gen)",430,LocalDate.of(2023,10,15),253,10,"Intel LGA 1700",20,28,"5.6GHz",28,33,100,true,"DDR4/DDR5");

        ResultActions risp= mockMvc.perform(post("/items/cpu").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(cpuPayLoad)));

        risp.andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    public void creaRam() throws Exception {

        RamPayLoad ramPayLoad=new RamPayLoad("Corsair","DRAM DOMINATOR PLATINUM RGB 2X16Gb ","Spingi le prestazioni oltre ogni limite con la memoria DDR5 CORSAIR DOMINATOR PLATINUM RGB ottimizzata per i processori Intel.",234,LocalDate.of(2022,10,10),6,20,"DDR5",7200,32, Arrays.asList(1L,2L,3L,4L,7L,8L));

        ResultActions risp= mockMvc.perform(post("/items/ram").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(ramPayLoad)));

        risp.andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    public void creaCase() throws Exception {

        BoxCasePayLoad boxCasePayLoad=new BoxCasePayLoad("Corsair","Case ATX Mid-Tower 4000D AIRFLOW con vetro temperato — Nero","CORSAIR 4000D AIRFLOW è un case ATX mid-tower dal design distintivo, ottimizzato per garantire un flusso d’aria elevato.",119.90,LocalDate.of(2022,10,1),0,20,"ATX",6,230,466,453,120);

        ResultActions risp= mockMvc.perform(post("/items/case").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(boxCasePayLoad)));

        risp.andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    public void creaSchedaGrafica() throws Exception {

        SchedaGraficaPayload schedaGraficaPayload=new SchedaGraficaPayload("Nvidia","GeForce RTX4090","NVIDIA® GeForce RTX™ 4090 è la GPU GeForce definitiva. Si tratta di un enorme passo avanti in termini di prestazioni, efficienza e grafica basata su IA.",1829,LocalDate.of(2022,11,1),450,20,137,304,"2.52GHz",24);

        ResultActions risp= mockMvc.perform(post("/items/scheda_grafica").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(schedaGraficaPayload)));

        risp.andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    public void creaHardDisk() throws Exception {

        HardDisskPayLoad hardDisskPayLoad=new HardDisskPayLoad("Corsair","MP600 PRO LPX 8TB PCIe Gen4 x4 NVMe M.2 SSD","CORSAIR MP600 PRO LPX M.2 PCIe NVMe 1.4 Gen4 x 4 è un'unità SSD ad alte prestazioni ottimizzata per PS5, che potenzia la capacità di archiviazione della tua console fino a 8 TB.",1039,LocalDate.of(2023,10,8),12,50,true,"8Tb");

        ResultActions risp= mockMvc.perform(post("/items/hard_disk").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(hardDisskPayLoad)));

        risp.andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    public void creaVentole() throws Exception {

        VentolePayLoad ventolePayLoad=new VentolePayLoad("Corsair","Ventola PWM iCUE SP120 RGB ELITE Performance da 120 mm","Il kit da tre ventole CORSAIR iCUE SP120 RGB ELITE Performance vanta otto brillanti LED RGB regolabili singolarmente per ciascuna ventola, potenziando l’illuminazione RGB di qualsiasi PC.",79.90,LocalDate.of(2022,10,10),6,20,600,1500,true,120,3);

        ResultActions risp= mockMvc.perform(post("/items/ventole").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(ventolePayLoad)));

        risp.andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    public void creaAlimentatore() throws Exception {

        AlimentatorePayLoad alimentatorePayLoad=new AlimentatorePayLoad("CoolerMaster","MWE GOLD 850 V2 ATX 3.0 READY","Cooler Master presents the MWE Gold V2 ATX 3.0 Ready featuring high power, ultra-silent performance with exceptional efficiency and reliability.",120,LocalDate.of(2022,9,1),0,20,850,true,2500);

        ResultActions risp= mockMvc.perform(post("/items/alimentatore").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(alimentatorePayLoad)));

        risp.andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
