package CapstoneProject.CapstoneProject.order;

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

    public Ordine saveOrdine(OrdinePayLaod body){
        Ordine o=new Ordine(body.indirizzo_di_spedizione(),body.user(),body.builds(),body.items());
        User u=userService.getSingleUser(body.user().getId());
        if(!u.getHasDatiOrdine())   userService.modificaHasOrdine(new ModificaHasOrdinePayLoad(true),u.getId());
        ordineRepository.save(o);
        for(int i=0;i<body.builds().size();i++){
            for(int j=0;j< body.builds().get(i).getItems().size();j++){
                itemService.scalaQuandita(body.builds().get(i).getItems().get(j).getId());
            }
        }
        for(int i=0;i<body.items().size();i++){
            itemService.scalaQuandita(body.items().get(i).getId());
        }
        return o;
    }


}
