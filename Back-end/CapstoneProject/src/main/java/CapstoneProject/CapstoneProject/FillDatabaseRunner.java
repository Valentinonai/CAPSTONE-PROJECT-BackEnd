package CapstoneProject.CapstoneProject;

import CapstoneProject.CapstoneProject.Enum.Formato;
import CapstoneProject.CapstoneProject.item.ItemService;
import CapstoneProject.CapstoneProject.scheda_madre.SchedaMadrePayLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class FillDatabaseRunner implements CommandLineRunner {
    @Autowired
    private ItemService itemService;
    @Override
    public void run(String... args) throws Exception {

        //---------------SCHEDA MADRE--------------------

        itemService.saveSchedaMadre(new SchedaMadrePayLoad("Asus","Rog Strix Z790-E","Stile e sostanza si uniscono come mai prima d'ora nella ROG Strix Z790-E II, ricca di funzionalità.",669, LocalDate.of(2023,11,01),120,100, "ATX","Intel Z790","Intel LGA 1700","DDR5",192,true,true,12,3,true));

        itemService.saveSchedaMadre(new SchedaMadrePayLoad("Asus","ROG MAXIMUS Z790 DARK HERO","ROG Maximus Z790 Dark Hero vanta un potente raffreddamento, WiFi 7 e un ampio slot PCIe 5.0 per prestazioni impareggiabili.",869, LocalDate.of(2023,11,01),120,20, "ATX","Intel Z790","Intel LGA 1700","DDR5",192,true,true,21,3,true));
        itemService.saveSchedaMadre(new SchedaMadrePayLoad("Asus","ROG STRIX Z790-I GAMING WIFI","Le regole sono fatte per essere infrante.",569, LocalDate.of(2023,11,01),120,10, "MINI_ITX","Intel Z790","Intel LGA 1700","DDR5",96,true,true,12,1,true));

        itemService.saveSchedaMadre(new SchedaMadrePayLoad("Asus","ROG STRIX B660-I GAMING WIFI","ROG Strix B660-I Gaming WiFi fornisce alimentazione di prima qualità e raffreddamento triple-decker in formato mini-ITX.",200, LocalDate.of(2023,11,01),120,10, "MINI_ITX","Intel B660","Intel LGA 1700","DDR5",96,true,true,12,1,true));

        itemService.saveSchedaMadre(new SchedaMadrePayLoad("Asus","ROG CROSSHAIR X670E HERO","Un'erogazione di potenza inflessibile e un'affidabile gestione termica costituiscono i principi indelebili del suo credo.",699, LocalDate.of(2023,11,01),120,5, "ATX", "AMD X670","AMD AM5","DDR5",192,true,true,23,3,true));

        itemService.saveSchedaMadre(new SchedaMadrePayLoad("Asus","ROG STRIX X670E-A GAMING WIFI","Preparati per la prossima generazione con l'elegante ROG Strix X670E-A Gaming WiFi.",430, LocalDate.of(2023,11,01),120,5, "ATX", "AMD X670","AMD AM5","DDR5",192,true,true,19,3,true));

        itemService.saveSchedaMadre(new SchedaMadrePayLoad("MSI","MEG Z790 GODLIKE MAX","Sperimenta l'apice dell'innovazione con la migliore scheda madre MEG Z790 GODLIKE MAX. ",609, LocalDate.of(2023,11,01),120,5, "ATX", "Intel Z790","Intel LGA 1700","DDR5",192,true,true,18,4,true));

        itemService.saveSchedaMadre(new SchedaMadrePayLoad("MSI","MEG Z790 ACE","Estetica premium con finiture nere e dettagli dorati.",600, LocalDate.of(2023,11,01),120,5, "ATX", "Intel Z790","Intel LGA 1700","DDR5",192,true,true,18,4,true));

        itemService.saveSchedaMadre(new SchedaMadrePayLoad("MSI","MPG B650 EDGE WIFI","MPG B650 EDGE WIFI utilizza una combinazione di colori bianco argentato per mostrare personalità e identità diverse, unendo l'eleganza alla potenza.",240, LocalDate.of(2023,11,01),120,5, "ATX", "AMD X670","AMD AM5","DDR5",192,true,true,19,3,true));

        itemService.saveSchedaMadre(new SchedaMadrePayLoad("MSI","MEG X670E ACE","La serie MEG si rivela come una leggenda che rappresenta la massima eleganza nel gaming.",938, LocalDate.of(2023,11,01),120,5, "ATX", "AMD X670","AMD AM5","DDR5",192,true,true,21,3,true));

        //------------------------------CPU----------------------------------------


    }
}
