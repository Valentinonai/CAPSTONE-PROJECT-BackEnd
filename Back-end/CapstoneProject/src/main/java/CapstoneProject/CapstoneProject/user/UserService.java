package CapstoneProject.CapstoneProject.user;

import CapstoneProject.CapstoneProject.cloudinary.CloudinaryService;
import CapstoneProject.CapstoneProject.exception.NotFoundException;
import CapstoneProject.CapstoneProject.indirizzo_di_spedizione.IndirizzoDiSpedizione;
import CapstoneProject.CapstoneProject.indirizzo_di_spedizione.IndirizzoDiSpedizionePayLoad;
import CapstoneProject.CapstoneProject.item.Item;
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
        userRepository.delete(u);
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
}
