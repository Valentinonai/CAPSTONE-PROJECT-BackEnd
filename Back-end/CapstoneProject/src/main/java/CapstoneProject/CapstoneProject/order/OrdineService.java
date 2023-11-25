package CapstoneProject.CapstoneProject.order;

import CapstoneProject.CapstoneProject.Enum.Stato;
import CapstoneProject.CapstoneProject.build.Build;
import CapstoneProject.CapstoneProject.exception.NotFoundException;
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

@Service
public class OrdineService {
    @Autowired
    private OrdineRepository ordineRepository;
    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    public Page<Ordine> getAllOrdine(int page,int size,String order){
        Pageable p= PageRequest.of(page,size, Sort.by(order));
        return ordineRepository.findAll(p);
    }

    public Ordine getSingleOrdine(long id){
        return ordineRepository.findById(id).orElseThrow(()->new NotFoundException("Elemento non trovato"));
    }

    public Ordine saveOrdine(OrdinePayLaod body,long user_id){
        User u=userService.getSingleUser(user_id);
        Ordine o=new Ordine(u.getIndirizzoSpedizione(),u,body.builds(),body.items());
        boolean check=false;
        List<Integer> elementiDaRimuovere=new ArrayList<>();
        if(!u.getHasDatiOrdine())   userService.modificaHasOrdine(new ModificaHasOrdinePayLoad(true),u.getId());
        if(body.builds().size()>0)
        {
            for(int i=0;i<body.builds().size();i++){
                for(int j=0;j< body.builds().get(i).getItems().size();j++){
                    if(body.builds().get(i).getItems().get(j).getQuantità()==0)
                        check=true;
                }
                if(check==false){
                    for(int j=0;j< body.builds().get(i).getItems().size();j++){
                        itemService.scalaQuandita(body.builds().get(i).getItems().get(j).getId());
                    }
                }
                else{
                   elementiDaRimuovere.add(i);
                   check=false;
                }
            }
           for(int i=0;i<elementiDaRimuovere.size();i++){
               body.builds().remove(i);
           }
        }
        elementiDaRimuovere.removeAll(elementiDaRimuovere);
        for(int i=0;i<body.items().size();i++){
            if(body.items().get(i).getQuantità()==0){
              elementiDaRimuovere.add(i);
            }else{
                itemService.scalaQuandita(body.items().get(i).getId());
            }

        }
        for(int i=0;i<elementiDaRimuovere.size();i++){
            body.items().remove(i);
        }
        ordineRepository.save(o);
        return o;
    }

public void eliminaOrdine(long id){
Ordine o=getSingleOrdine(id);
o.setStato(Stato.INATTIVO);
ordineRepository.save(o);
}
}
