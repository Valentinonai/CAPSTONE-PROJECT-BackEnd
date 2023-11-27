package CapstoneProject.CapstoneProject;

import CapstoneProject.CapstoneProject.Enum.Categoria;
import CapstoneProject.CapstoneProject.Enum.Formato;
import CapstoneProject.CapstoneProject.alimentatore.AlimentatorePayLoad;
import CapstoneProject.CapstoneProject.boxCase.BoxCasePayLoad;
import CapstoneProject.CapstoneProject.cpu.CpuPayLoad;
import CapstoneProject.CapstoneProject.hard_disk.HardDisskPayLoad;
import CapstoneProject.CapstoneProject.item.ItemService;
import CapstoneProject.CapstoneProject.ram.Ram;
import CapstoneProject.CapstoneProject.ram.RamPayLoad;
import CapstoneProject.CapstoneProject.scheda_grafica.SchedaGraficaPayload;
import CapstoneProject.CapstoneProject.scheda_madre.SchedaMadrePayLoad;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class FillDatabaseRunner implements CommandLineRunner {
    @Autowired
    private ItemService itemService;
    @Override
    public void run(String... args) throws Exception {

        //---------------SCHEDA MADRE--------------------

       /*itemService.saveSchedaMadre(new SchedaMadrePayLoad("Asus","Rog Strix Z790-E","Stile e sostanza si uniscono come mai prima d'ora nella ROG Strix Z790-E II, ricca di funzionalità.",669, LocalDate.of(2023,11,01),120,100, "ATX","Intel Z790","Intel LGA 1700","DDR5",192,true,true,12,3,true));

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

       itemService.saveCpu(new CpuPayLoad("Intel","Intel® Core™ i7 processor 14700K",
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
                                                "AMD Ryzen™ 7 Desktop Processors",392,LocalDate.of(2020,11,5),105,10,"AMD AM4",8,16,"4.5GHz",4,32,90,false,"DDR4"));


        //--------------------------------------RAM--------------------------------------------

       itemService.saveRam(new RamPayLoad("Corsair","DRAM DOMINATOR PLATINUM RGB 2X16Gb ","Spingi le prestazioni oltre ogni limite con la memoria DDR5 CORSAIR DOMINATOR PLATINUM RGB ottimizzata per i processori Intel.",234,LocalDate.of(2022,10,10),6,20,"DDR5",7200,32, Arrays.asList(1L,2L,3L,4L,7L,8L)));

       itemService.saveRam(new RamPayLoad("Corsair","DRAM DOMINATOR PLATINUM RGB ","Spingi le prestazioni oltre ogni limite con la memoria DDR5 CORSAIR DOMINATOR PLATINUM RGB ottimizzata per i processori Intel.",194,LocalDate.of(2022,10,10),6,20,"DDR5",6000,32, Arrays.asList(5L,6L,9L,10L)));

        itemService.saveRam(new RamPayLoad("Corsair","VENGEANCE® RGB 32GB (2x16GB) DDR5 DRAM 6000MT/s CL30 Memory Kit — Black","CORSAIR VENGEANCE RGB DDR5 memory delivers DDR5 performance, higher frequencies, and greater capacities.",139,LocalDate.of(2021,10,10),6,10,"DDR5",6000,32, Arrays.asList(1L,2L,3L,4L,7L,8L)));

        itemService.saveRam(new RamPayLoad("Corsair","Kit di memoria DDR5 DRAM VENGEANCE RGB 32GB (2x16GB) 6000MT/s C30, Kit di memoria AMD EXPO ","Raggiungi il massimo delle prestazioni RAM DDR5 ottimizzate per le schede madre AMD, con RGB a dieci zone per memoria DDR5 per illuminare il tuo sistema PC all'avanguardia.",139,LocalDate.of(2022,10,10),6,20,"DDR5",4800,32, Arrays.asList(5L,6L,9L,10L)));

        itemService.saveRam(new RamPayLoad("Corsair","DRAM DOMINATOR TITANIUM RGB 32 GB ","La memoria DDR5 CORSAIR DOMINATOR TITANIUM RGB unisce un design pulito e raffinato ad una struttura in alluminio pressofuso di qualità superiore e un sistema di illuminazione all’avanguardia.",244.99,LocalDate.of(2023,10,10),6,10,"DDR5",4800,32, Arrays.asList(1L,2L,3L,4L,7L,8L)));

        itemService.saveRam(new RamPayLoad("Corsair","Kit di memoria DDR5 DRAM DOMINATOR TITANIUM RGB 32 GB (2x16 GB) 6000 MT/s CL30 AMD EXPO – Grey ","La memoria DDR5 CORSAIR DOMINATOR TITANIUM RGB unisce un design pulito e raffinato ad una struttura in alluminio pressofuso di qualità superiore e un sistema di illuminazione all’avanguardia.",204.99,LocalDate.of(2023,10,10),6,20,"DDR5",4800,32, Arrays.asList(5L,6L,9L,10L)));

        //-------------------------------------CASE--------------------------------------------------

        itemService.saveBoxCase(new BoxCasePayLoad("Corsair","Case ATX Mid-Tower 4000D AIRFLOW con vetro temperato — Nero","CORSAIR 4000D AIRFLOW è un case ATX mid-tower dal design distintivo, ottimizzato per garantire un flusso d’aria elevato.",119.90,LocalDate.of(2022,10,1),0,20,"ATX",6,230,466,453,120));

        itemService.saveBoxCase(new BoxCasePayLoad("Corsair","Case PC ATX mid-tower in vetro temperato 5000D AIRFLOW, nero","CORSAIR 5000D AIRFLOW è un case ATX mid-tower che mette in risalto il tuo PC, senza far vedere i cavi.",134.90,LocalDate.of(2021,10,1),0,20,"ATX",6,245,520,520,120));

        itemService.saveBoxCase(new BoxCasePayLoad("Corsair","Case per PC ATX Full-Tower CORSAIR 7000D AIRFLOW — Nero","CORSAIR 7000D AIRFLOW è un case ATX full-tower pensato per le configurazioni più ambiziose.",239,LocalDate.of(2023,10,1),0,20,"ATX",7,248,600,550,120));

        itemService.saveBoxCase(new BoxCasePayLoad("Corsair","Case per PC Mini-ITX 2000D RGB AIRFLOW - Bianco","CORSAIR 2000D RGB AIRFLOW è un case Mini-ITX con fattore di forma ridotto che vanta un’illuminazione RGB vivace.",179,LocalDate.of(2023,10,1),0,20,"MINI_ITX",3,200,458,271,120));

        itemService.saveBoxCase(new BoxCasePayLoad("CoolerMaster","MASTERBOX TD500 MESH V2 RYU","MASTERBOX TD500 MESH V2 RYU",190,LocalDate.of(2023,10,1),0,20,"ATX",6,210,500,499,120));

        itemService.saveBoxCase(new BoxCasePayLoad("CoolerMaster","COSMOS C700M","Highly Versatile Layout Graphics Card Mounting with Riser Cable Addressable RGB Lighting Extensive Cable Cover System Aluminum Panels",489,LocalDate.of(2023,10,1),0,20,"ATX",10,306 ,651,650,120));

        itemService.saveBoxCase(new BoxCasePayLoad("CoolerMaster","HAF 700 EVO","HAF 700 EVO unveils a new era of thermal efficiency through unique features designed to transcend the standards of contemporary cooling solutions.",384,LocalDate.of(2022,10,1),0,20,"ATX",10,279 ,540,556,120));

        itemService.saveBoxCase(new BoxCasePayLoad("CoolerMaster","MASTERBOX TD500 MESH V2","MasterBox TD500 Mesh V2’s iconic polygonal design keeps your system looking unique and your gaming sessions streamlined.",115,LocalDate.of(2021,10,1),0,20,"ATX",10,210 ,500,499,120));

        itemService.saveBoxCase(new BoxCasePayLoad("CoolerMaster","CMP 520","CMP 520's asymmetrical front panel design frames the pre-installed three 120mm ARGB fans as unique centerpieces on the build.",125,LocalDate.of(2021,10,1),0,20,"ATX",6,204 ,463,439,120));

        itemService.saveBoxCase(new BoxCasePayLoad("CoolerMaster","MASTERBOX K501L & K501L RGB","Gaming is in the core of the new MasterBox K501L. Donning an illuminated fan against the angled front panel ventilation speaks to its competitive character.",235,LocalDate.of(2022,10,1),0,20,"ATX",7,204 ,463,439,120));


        //---------------------------------ALIMENTATORE--------------------------------------

        itemService.saveAlimentatore(new AlimentatorePayLoad("CoolerMaster","MWE GOLD 850 V2 ATX 3.0 READY","Cooler Master presents the MWE Gold V2 ATX 3.0 Ready featuring high power, ultra-silent performance with exceptional efficiency and reliability.",120,LocalDate.of(2022,9,1),0,20,850,true,2500));

        itemService.saveAlimentatore(new AlimentatorePayLoad("CoolerMaster","MWE GOLD 750 V2 ATX 3.0 READY","Cooler Master presents the MWE Gold V2 ATX 3.0 Ready featuring high power, ultra-silent performance with exceptional efficiency and reliability.",100,LocalDate.of(2022,9,1),0,20,750,true,2300));

        itemService.saveAlimentatore(new AlimentatorePayLoad("CoolerMaster","GX III GOLD 1250","Cooler Master presents the GX III Gold featuring exceptional efficiency, low noise output, and superb reliability.",230,LocalDate.of(2022,9,1),0,20,1250,true,2800));

        itemService.saveAlimentatore(new AlimentatorePayLoad("CoolerMaster","GX III GOLD 1050","Cooler Master presents the GX III Gold featuring exceptional efficiency, low noise output, and superb reliability.",200,LocalDate.of(2022,9,1),0,20,1050,true,2800));

        itemService.saveAlimentatore(new AlimentatorePayLoad("Corsair","RM1000x Shift with Premium Sleeved Type-5 Pro Cables Kit - White","CORSAIR RMx SHIFT Series fully modular power supplies boast a revolutionary patent-pending side cable interface to keep all your connections within easy reach, for exceptionally convenient 80 PLUS Gold efficient power.",206.9,LocalDate.of(2023,9,1),0,20,1000,true,2800));

        itemService.saveAlimentatore(new AlimentatorePayLoad("Corsair","HX1500i Fully Modular Ultra-Low Noise Platinum ATX 1500 Watt PC Power Supply (EU)","Grazie ai tre connettori EPS12V e al cablaggio totalmente modulare, gli alimentatori HXi Series platinum assicurano ogni tipo di connessione necessaria per gestire al meglio l’erogazione di corrente dei PC all’avanguardia.",259.9,LocalDate.of(2023,9,1),0,20,1500,true,3100));

        itemService.saveAlimentatore(new AlimentatorePayLoad("Corsair","Alimentatore ATX RM Series interamente modulare RM650 da 650 Watt con certificazione 80 PLUS Gold (EU)","Gli alimentatori CORSAIR RM Series interamente modulari e a bassa rumorosità assicurano a un'ampia varietà di sistemi PC un’alimentazione affidabile e silenziosa con efficienza 80 PLUS Gold.",84.9,LocalDate.of(2022,10,1),0,20,650,true,2200));

        itemService.saveAlimentatore(new AlimentatorePayLoad("Corsair","SF-L Series SF850L Fully Modular Low-Noise SFX Power Supply (EU)","CORSAIR SF-L Series Fully Modular SFX Power Supplies with ATX 3.0 and PCIe 5.0 compliance provide the continuous high wattages demanded by the latest PC hardware, all in a space-saving SFX-L form-factor.",169.9,LocalDate.of(2022,10,1),0,20,850,true,2400));

        //---------------------------------Hard Disk---------------------------------------

        itemService.saveHardDisk(new HardDisskPayLoad("Corsair","MP600 PRO LPX 8TB PCIe Gen4 x4 NVMe M.2 SSD","CORSAIR MP600 PRO LPX M.2 PCIe NVMe 1.4 Gen4 x 4 è un'unità SSD ad alte prestazioni ottimizzata per PS5, che potenzia la capacità di archiviazione della tua console fino a 8 TB.",1039,LocalDate.of(2023,10,8),12,50,true,"8Tb"));

        itemService.saveHardDisk(new HardDisskPayLoad("Corsair","MP700 2TB PCIe 5.0 (Gen 5) x4 NVMe M.2 SSD","Harness the power of Gen5 SSD technology with NVMe 2.0 to enable faster read and write times, built with high-density 3D TLC NAND flash memory.",319,LocalDate.of(2023,10,8),12,50,true,"2Tb"));

        itemService.saveHardDisk(new HardDisskPayLoad("Corsair","Unità SDD MP600 PRO NH 500 GB PCIe 4.0 (Gen 4) x4 NVMe M.2","L'unità SSD CORSAIR MP600 PRO NH sfrutta la tecnologia PCIe Gen4 per raggiungere fino a 7.000 MB/sec di velocità in lettura sequenziale e fino a 6.500 MB/sec in scrittura sequenziale.",74.99,LocalDate.of(2023,10,8),12,50,true,"500Gb"));

        itemService.saveHardDisk(new HardDisskPayLoad("Corsair","MP700 1TB PCIe 5.0 (Gen 5) x4 NVMe M.2 SSD","Harness the power of Gen5 SSD technology with NVMe 2.0 to enable faster read and write times, built with high-density 3D TLC NAND flash memory.",189.99,LocalDate.of(2023,10,8),12,50,true,"1Tb"));

        itemService.saveHardDisk(new HardDisskPayLoad("Corsair","SSD M.2 NVMe 2.0 PCIe Gen5 x4 MP700 PRO da 2 TB con sistema di raffreddamento ad aria","Sfrutta le prestazioni di archiviazione PCIe Gen5 con velocità di lettura e scrittura sequenziale sbalorditive e l’interfaccia NVMe 2.0 a larghezza di banda elevata, per prestazioni e durabilità eccezionali.",379.99,LocalDate.of(2023,10,8),12,50,true,"2Tb"));

        //-----------------------------SCHEDA GRAFICA-------------------------------


        itemService.saveSchedaGrafica(new SchedaGraficaPayload("Nvidia","GeForce RTX4090","NVIDIA® GeForce RTX™ 4090 è la GPU GeForce definitiva. Si tratta di un enorme passo avanti in termini di prestazioni, efficienza e grafica basata su IA.",1829,LocalDate.of(2022,11,1),450,20,137,304,"2.52GHz",24));

        itemService.saveSchedaGrafica(new SchedaGraficaPayload("Nvidia","GeForce RTX4080","NVIDIA® GeForce RTX™ 4080 offre le prestazioni e le funzionalità ultra che giocatori e creativi appassionati richiedono.",1349,LocalDate.of(2022,11,1),320,20,137,304,"2.51GHz",16));

        itemService.saveSchedaGrafica(new SchedaGraficaPayload("Nvidia","GeForce RTX4070Ti","Preparati a un'esperienza di gioco e creatività straordinaria con le schede grafiche NVIDIA® GeForce RTX™ 4070 Ti e RTX 4070.",800,LocalDate.of(2023,1,1),285,20,112,244,"2.61GHz",12));

        itemService.saveSchedaGrafica(new SchedaGraficaPayload("Nvidia","GeForce RTX4070","Preparati a un'esperienza di gioco e creatività straordinaria con le schede grafiche NVIDIA® GeForce RTX™ 4070 Ti e RTX 4070.",669,LocalDate.of(2022,11,1),200,20,112,244,"2.48GHz",12));

        itemService.saveSchedaGrafica(new SchedaGraficaPayload("Nvidia","GeForce RTX4060Ti","Gioca, trasmetti in streaming, crea. Le schede GeForce RTX™ 4060 Ti e RTX 4060 permettono di utilizzare i giochi e le app più recenti con l'architettura ultra efficiente NVIDIA Ada Lovelace.",430,LocalDate.of(2023,1,1),165,20,98,244,"2.54GHz",8));

        itemService.saveSchedaGrafica(new SchedaGraficaPayload("Nvidia","GeForce RTX4060","Gioca, trasmetti in streaming, crea. Le schede GeForce RTX™ 4060 Ti e RTX 4060 permettono di utilizzare i giochi e le app più recenti con l'architettura ultra efficiente NVIDIA Ada Lovelace.",335,LocalDate.of(2022,11,1),115,20,98,244,"2.46GHz",8));


        itemService.saveSchedaGrafica(new SchedaGraficaPayload("Nvidia","GeForce RTX3090Ti","NVIDIA® GeForce RTX™ 3090 Ti.",1495.95,LocalDate.of(2021,11,1),450,20,138,313,"1.86GHz",24));

        itemService.saveSchedaGrafica(new SchedaGraficaPayload("Nvidia","GeForce RTX3090","NVIDIA® GeForce RTX™ 3090.",1239,LocalDate.of(2021,11,1),350,20,138,313,"1.70GHz",24));

        itemService.saveSchedaGrafica(new SchedaGraficaPayload("Nvidia","GeForce RTX3080Ti","NVIDIA® GeForce RTX™ 3080Ti.",1099,LocalDate.of(2021,11,1),350,20,112,285,"1.67GHz",12));

        itemService.saveSchedaGrafica(new SchedaGraficaPayload("Nvidia","GeForce RTX3080","NVIDIA® GeForce RTX™ 3080.",1029.44,LocalDate.of(2021,11,1),350,20,112,285,"1.71GHz",10));

        itemService.saveSchedaGrafica(new SchedaGraficaPayload("Nvidia","GeForce RTX3070Ti","NVIDIA® GeForce RTX™ 3070Ti.",799,LocalDate.of(2021,11,1),290,20,112,267,"1.77GHz",8));

        itemService.saveSchedaGrafica(new SchedaGraficaPayload("Nvidia","GeForce RTX3070","NVIDIA® GeForce RTX™ 3070.",568,LocalDate.of(2021,11,1),220,20,112,242,"1.73GHz",8));*/

        //--------------------------------------------VENTOLE----------------------------------------
    }
}
