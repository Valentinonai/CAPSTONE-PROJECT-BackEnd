package CapstoneProject.CapstoneProject;

import CapstoneProject.CapstoneProject.Enum.Formato;
import CapstoneProject.CapstoneProject.cpu.CpuPayLoad;
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

       /* itemService.saveSchedaMadre(new SchedaMadrePayLoad("Asus","Rog Strix Z790-E","Stile e sostanza si uniscono come mai prima d'ora nella ROG Strix Z790-E II, ricca di funzionalità.",669, LocalDate.of(2023,11,01),120,100, "ATX","Intel Z790","Intel LGA 1700","DDR5",192,true,true,12,3,true));

        itemService.saveSchedaMadre(new SchedaMadrePayLoad("Asus","ROG MAXIMUS Z790 DARK HERO","ROG Maximus Z790 Dark Hero vanta un potente raffreddamento, WiFi 7 e un ampio slot PCIe 5.0 per prestazioni impareggiabili.",869, LocalDate.of(2023,11,01),120,20, "ATX","Intel Z790","Intel LGA 1700","DDR5",192,true,true,21,3,true));
        itemService.saveSchedaMadre(new SchedaMadrePayLoad("Asus","ROG STRIX Z790-I GAMING WIFI","Le regole sono fatte per essere infrante.",569, LocalDate.of(2023,11,01),120,10, "MINI_ITX","Intel Z790","Intel LGA 1700","DDR5",96,true,true,12,1,true));

        itemService.saveSchedaMadre(new SchedaMadrePayLoad("Asus","ROG STRIX B660-I GAMING WIFI","ROG Strix B660-I Gaming WiFi fornisce alimentazione di prima qualità e raffreddamento triple-decker in formato mini-ITX.",200, LocalDate.of(2023,11,01),120,10, "MINI_ITX","Intel B660","Intel LGA 1700","DDR5",96,true,true,12,1,true));

        itemService.saveSchedaMadre(new SchedaMadrePayLoad("Asus","ROG CROSSHAIR X670E HERO","Un'erogazione di potenza inflessibile e un'affidabile gestione termica costituiscono i principi indelebili del suo credo.",699, LocalDate.of(2023,11,01),120,5, "ATX", "AMD X670","AMD AM5","DDR5",192,true,true,23,3,true));

        itemService.saveSchedaMadre(new SchedaMadrePayLoad("Asus","ROG STRIX X670E-A GAMING WIFI","Preparati per la prossima generazione con l'elegante ROG Strix X670E-A Gaming WiFi.",430, LocalDate.of(2023,11,01),120,5, "ATX", "AMD X670","AMD AM5","DDR5",192,true,true,19,3,true));

        itemService.saveSchedaMadre(new SchedaMadrePayLoad("MSI","MEG Z790 GODLIKE MAX","Sperimenta l'apice dell'innovazione con la migliore scheda madre MEG Z790 GODLIKE MAX. ",609, LocalDate.of(2023,11,01),120,5, "ATX", "Intel Z790","Intel LGA 1700","DDR5",192,true,true,18,4,true));

        itemService.saveSchedaMadre(new SchedaMadrePayLoad("MSI","MEG Z790 ACE","Estetica premium con finiture nere e dettagli dorati.",600, LocalDate.of(2023,11,01),120,5, "ATX", "Intel Z790","Intel LGA 1700","DDR5",192,true,true,18,4,true));

        itemService.saveSchedaMadre(new SchedaMadrePayLoad("MSI","MPG B650 EDGE WIFI","MPG B650 EDGE WIFI utilizza una combinazione di colori bianco argentato per mostrare personalità e identità diverse, unendo l'eleganza alla potenza.",240, LocalDate.of(2023,11,01),120,5, "ATX", "AMD X670","AMD AM5","DDR5",192,true,true,19,3,true));

        itemService.saveSchedaMadre(new SchedaMadrePayLoad("MSI","MEG X670E ACE","La serie MEG si rivela come una leggenda che rappresenta la massima eleganza nel gaming.",938, LocalDate.of(2023,11,01),120,5, "ATX", "AMD X670","AMD AM5","DDR5",192,true,true,21,3,true));*/

        //------------------------------CPU----------------------------------------

      /*  itemService.saveCpu(new CpuPayLoad("Intel","Intel® Core™ i7 processor 14700K",
                "Intel® Core™ i7 Processors (14th gen)",430,LocalDate.of(2023,10,15),253,10,"Intel LGA 1700",20,28,"5.6GHz",28,33,100,true,"DDR4/DDR5"));

        itemService.saveCpu(new CpuPayLoad("Intel","Intel® Core™ i9 processor 14900K",
                        "Intel® Core™ i9 Processors (14th gen)",659,LocalDate.of(2023,10,15),253,10,"Intel LGA 1700",24,32,"6GHz",32,36,100,true,"DDR4/DDR5"));

        itemService.saveCpu(new CpuPayLoad("Intel","Intel® Core™ i5 processor 14600K",
                                "Intel® Core™ i5 Processors (14th gen)",324,LocalDate.of(2023,10,15),181,10,"Intel LGA 1700",14,20,"5.3GHz",20,24,100,true,"DDR4/DDR5"));

        itemService.saveCpu(new CpuPayLoad("Intel","Intel® Core™ i9-13900 Processor",
                                        "13th Generation Intel® Core™ i9 Processors",562,LocalDate.of(2022,10,15),219,10,"Intel LGA 1700",24,32,"5.6GHz",32,36,100,true,"DDR4/DDR5"));

        itemService.saveCpu(new CpuPayLoad("Intel","Intel® Core™ i7-13700K Processor",
                                                "13th Generation Intel® Core™ i7 Processors",392,LocalDate.of(2022,10,15),254,10,"Intel LGA 1700",16,24,"5.4GHz",24,30,100,true,"DDR4/DDR5"));

        itemService.saveCpu(new CpuPayLoad("AMD Ryzen","AMD RYZEN™ 9 7950X Processor",
                                                "AMD Ryzen™ 9 Desktop Processors",654,LocalDate.of(2022,9,27),170,10,"AMD AM5",16,32,"5.7GHz",16,64,95,true,"DDR4/DDR5"));

        itemService.saveCpu(new CpuPayLoad("AMD Ryzen","AMD RYZEN™ 7 7800X3D Processor",
                                                "AMD Ryzen™ 7 Desktop Processors",403,LocalDate.of(2023,4,6),120,10,"AMD AM5",8,16,"5GHz",8,96,89,true,"DDR4/DDR5"));

        itemService.saveCpu(new CpuPayLoad("AMD Ryzen","AMD RYZEN™ 5 7600X Processor",
                                                "AMD Ryzen™ 5 Desktop Processors",239,LocalDate.of(2022,9,27),105,10,"AMD AM5",6,12,"5.3GHz",6,32,95,true,"DDR4/DDR5"));

        itemService.saveCpu(new CpuPayLoad("AMD Ryzen","AMD RYZEN™ 9 7950X3D Processor",
                                                "AMD Ryzen™ 9 Desktop Processors",654,LocalDate.of(2023,2,23),120,10,"AMD AM5",16,32,"5.7GHz",16,128,89,true,"DDR4/DDR5"));

        itemService.saveCpu(new CpuPayLoad("AMD Ryzen","AMD RYZEN™ 7 5800X3D Processor",
                                                "AMD Ryzen™ 7 Desktop Processors",392,LocalDate.of(2020,11,5),105,10,"AMD AM4",8,16,"4.5GHz",4,32,90,false,"DDR4"));*/


    }
}
