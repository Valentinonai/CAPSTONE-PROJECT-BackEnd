package CapstoneProject.CapstoneProject.order;

import CapstoneProject.CapstoneProject.Enum.Stato;
import CapstoneProject.CapstoneProject.build.Build;
import CapstoneProject.CapstoneProject.build.BuildService;
import CapstoneProject.CapstoneProject.exception.NotFoundException;
import CapstoneProject.CapstoneProject.exception.SingleBadRequest;
import CapstoneProject.CapstoneProject.item.Item;
import CapstoneProject.CapstoneProject.item.ItemPayLoadQuantita;
import CapstoneProject.CapstoneProject.item.ItemService;
import CapstoneProject.CapstoneProject.user.ModificaHasOrdinePayLoad;
import CapstoneProject.CapstoneProject.user.User;
import CapstoneProject.CapstoneProject.user.UserPayLoad;
import CapstoneProject.CapstoneProject.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class OrdineService {
    @Autowired
    private OrdineRepository ordineRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private BuildService buildService;

    @Autowired
    private UserService userService;

    public Page<Ordine> getAllOrdine(int page,int size,String order){
        Pageable p= PageRequest.of(page,size, Sort.by(order));
        return ordineRepository.findAll(p);
    }

    public Ordine getSingleOrdine(long id){
        return ordineRepository.findById(id).orElseThrow(()->new NotFoundException("Elemento non trovato"));
    }

    public ElementiNonPresentiPayload saveOrdine(OrdinePayLaod body,long user_id){
        List<Build> buildList=new ArrayList<>();
        List<Item> itemList=new ArrayList<>();
        for(int i=0;i<body.builds_id().size();i++)    buildList.add(buildService.getSingleBuild(body.builds_id().get(i)));
        for(int i=0;i<body.items_id().size();i++)    itemList.add(itemService.getSingleItem(body.items_id().get(i)));
        User u=userService.getSingleUser(user_id);

        boolean check=false;
        List<Build> elementiDaRimuovere=new ArrayList<>();
        if(!u.getHasDatiOrdine())   userService.modificaHasOrdine(new ModificaHasOrdinePayLoad(true),u.getId());

        if(buildList.size()>0)
        {
            for(int i=0;i<buildList.size();i++){
                for(int j=0;j< buildList.get(i).getItems().size();j++){
                    if(buildList.get(i).getItems().get(j).getQuantità()==0)
                        check=true;
                }
                if(check==false){
                    for(int j=0;j< buildList.get(i).getItems().size();j++){
                        itemService.scalaQuandita(buildList.get(i).getItems().get(j).getId());
                    }
                }
                else{
                   elementiDaRimuovere.add(buildList.get(i));
                   check=false;
                }
            }
           for(int i=0;i<elementiDaRimuovere.size();i++){
              buildList.remove(elementiDaRimuovere.get(i));
           }
        }
        List<Item> itemsDaRimuovere=new ArrayList<>();
        for(int i=0;i<itemList.size();i++){
            if(itemList.get(i).getQuantità()==0){
            itemsDaRimuovere.add(itemList.get(i));
            }else{
                itemService.scalaQuandita(itemList.get(i).getId());
            }

        }
        for(int i=0;i<itemsDaRimuovere.size();i++){
            itemList.remove(itemsDaRimuovere.get(i));
        }
        Ordine o=new Ordine(u.getIndirizzoSpedizione(),u,buildList,itemList);
        o.setStato(Stato.IN_LAVORAZIONE);
        if(!buildList.isEmpty() || !itemList.isEmpty())
        ordineRepository.save(o);
        return new ElementiNonPresentiPayload(elementiDaRimuovere,itemsDaRimuovere);
    }

public void eliminaOrdine(long id){
Ordine o=getSingleOrdine(id);
o.setStato(Stato.INATTIVO);
ordineRepository.save(o);
}

    public Page<Ordine> getUserOrdini(int page,int size,String order,User u) {
        Pageable p=PageRequest.of(page,size,Sort.by(order));
        return ordineRepository.findByUser(p,u.getId());
    }



    public Ordine modificaStatoOrdine(Stato stato,long ordine_id ){
Ordine o=getSingleOrdine(ordine_id);
o.setStato(stato);
return ordineRepository.save(o);
    }

    public Ordine getSingleOrdineUser(long user_id, long id) {
        Ordine o=getSingleOrdine(id);
        if(o.getUser_id().getId()==user_id)    return o;
        else throw new SingleBadRequest("Non hai nessun ordine numero: "+id);
    }
}
