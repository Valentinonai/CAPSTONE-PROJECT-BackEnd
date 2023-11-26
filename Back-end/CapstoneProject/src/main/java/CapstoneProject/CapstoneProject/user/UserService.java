package CapstoneProject.CapstoneProject.user;

import CapstoneProject.CapstoneProject.Enum.Ruolo;

import CapstoneProject.CapstoneProject.build.BuildService;
import CapstoneProject.CapstoneProject.carta_di_credito.CartaDiCredito;
import CapstoneProject.CapstoneProject.carta_di_credito.CartaDiCreditoService;
import CapstoneProject.CapstoneProject.cloudinary.CloudinaryService;
import CapstoneProject.CapstoneProject.exception.NotFoundException;
import CapstoneProject.CapstoneProject.indirizzo_di_spedizione.IndirizzoDiSpedizione;
import CapstoneProject.CapstoneProject.indirizzo_di_spedizione.IndirizzoDiSpedizionePayLoad;
import CapstoneProject.CapstoneProject.indirizzo_di_spedizione.IndirizzoDiSpedizioneService;
import CapstoneProject.CapstoneProject.item.Item;
import CapstoneProject.CapstoneProject.item.ItemService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private IndirizzoDiSpedizioneService indirizzoDiSpedizioneService;
    @Autowired
    private CartaDiCreditoService cartaDiCreditoService;
    @Autowired
    private BuildService buildService;
    @Autowired
    private ItemService itemService;

    public Page<User> getAllUsers(int page,int size,String order){
        Pageable p= PageRequest.of(page,size, Sort.by(order));
        return userRepository.findAll(p);
    }

    public User getSingleUser(long id){
        return userRepository.findById(id).orElseThrow(()->new NotFoundException("Elemento non trovato"));
    }

    public User saveUser(UserPayLoad body){
        User u=new User(body.nome(), body.cognome(), body.email(), passwordEncoder.encode(body.password()));
        return userRepository.save(u);
    }

    public User modificaPassword(ModificaPasswordPayLoad body,long id){
        User u=getSingleUser(id);
        u.setPassword(passwordEncoder.encode(body.password()));
        return userRepository.save(u);
    }

    public User modificaHasOrdine(ModificaHasOrdinePayLoad body,long id){
        User u=getSingleUser(id);
        u.setHas_dati_ordine(body.has_ordine());
        return userRepository.save(u);
    }

    public void deleteUser(long id){
        User u=getSingleUser(id);
        u.setRuolo(Ruolo.INATTIVO);
        if(u.getIndirizzoSpedizione()!=null)
        indirizzoDiSpedizioneService.setIndirizzoInattivo(u.getIndirizzoSpedizione().getId());
        if(u.getCartaDiCredito()!=null)
        cartaDiCreditoService.setCartaInattiva(u.getCartaDiCredito().getId());
        if(u.getBuilds()!=null)
        buildService.setBuildInattiva(u.getBuilds());
//        userRepository.delete(u);
        userRepository.save(u);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()->new NotFoundException("User con l'email selezionata non è stato trovato"));
    }

    public User saveIndirizzoUser(IndirizzoDiSpedizione indirizzoDiSpedizione, User u){
        User user=getSingleUser(u.getId());
        user.setIndirizzoSpedizione(indirizzoDiSpedizione);
        return userRepository.save(user);
    }

    public User uploadImg(MultipartFile file, long id) throws IOException {
        User u=getSingleUser(id);
        if(!u.getImmagineUrl().equals("https://res.cloudinary.com/dzr77mvcs/image/upload/v1699804243/bqnqdcricxpzxojhihxz.webp"))
            cloudinaryService.deleteImageByUrl(u.getImmagineUrl());
        String url=(String)  cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
        u.setImmagineUrl(url);
        return userRepository.save(u);

    }

    public Set<Item> aggiungiPreferiti(long item_id,long user_id)
    {
        User u=getSingleUser(user_id);
        Item i=itemService.getSingleItem(item_id);
        u.setItems(i);
        userRepository.save(u);
        return u.getItems();
    }
    public Set<Item> rimuoviPreferiti(long item_id,long user_id)
    {
        User u=getSingleUser(user_id);
        Item i=itemService.getSingleItem(item_id);
      u.deleteItem(i);
      userRepository.save(u);
        return u.getItems();
    }

}
