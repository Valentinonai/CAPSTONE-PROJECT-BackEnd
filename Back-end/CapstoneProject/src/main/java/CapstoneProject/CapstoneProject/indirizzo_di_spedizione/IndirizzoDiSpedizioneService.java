package CapstoneProject.CapstoneProject.indirizzo_di_spedizione;

import CapstoneProject.CapstoneProject.exception.NotFoundException;
import CapstoneProject.CapstoneProject.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class IndirizzoDiSpedizioneService {

    @Autowired
    private IndirizzoDiSpedizioneRepository indirizzoDiSpedizioneRepository;

    public Page<IndirizzoDiSpedizione> getAllIndirizzi(int page, int size, String order) {
        Pageable p = PageRequest.of(page, size, Sort.by(order));
        return indirizzoDiSpedizioneRepository.findAll(p);
    }

    public IndirizzoDiSpedizione getSingleIndirizzo(long id) {
        return indirizzoDiSpedizioneRepository.findById(id).orElseThrow(() -> new NotFoundException("Elemento non trovato"));
    }

    public IndirizzoDiSpedizione saveIndirizzo(IndirizzoDiSpedizionePayLoad body) {
        IndirizzoDiSpedizione i = new IndirizzoDiSpedizione(body.via(), body.numero(), body.codice_postale(), body.paese(), body.numero_interno(), body.provincia(), body.user());
        return indirizzoDiSpedizioneRepository.save(i);
    }

    public IndirizzoDiSpedizione modifyIndirizzo(IndirizzoDiSpedizione body, User user) {
        IndirizzoDiSpedizione i = getSingleIndirizzo(user.getIndirizzoSpedizione().getId());
        i.setVia(body.getVia());
        i.setNumero(body.getNumero());
        i.setPaese(body.getPaese());
        i.setCodice_postale(body.getCodice_postale());
        i.setNumero_interno(body.getNumero_interno());
        i.setProvincia(body.getProvincia());
        return indirizzoDiSpedizioneRepository.save(i);
    }

    public void deleteIndirizzo(User user) {
        IndirizzoDiSpedizione i = getSingleIndirizzo(user.getIndirizzoSpedizione().getId());
        indirizzoDiSpedizioneRepository.delete(i);
    }


}
