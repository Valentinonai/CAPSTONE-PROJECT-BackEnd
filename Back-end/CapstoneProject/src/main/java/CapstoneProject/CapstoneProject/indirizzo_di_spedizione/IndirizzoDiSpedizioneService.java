package CapstoneProject.CapstoneProject.indirizzo_di_spedizione;

import CapstoneProject.CapstoneProject.Enum.Stato;
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

    public IndirizzoDiSpedizione saveIndirizzo(IndirizzoDiSpedizionePayLoad body,User u) {
        IndirizzoDiSpedizione i = new IndirizzoDiSpedizione(body.via(), body.numero(), body.codice_postale(), body.paese(), body.numero_interno(), body.provincia(), u);
        return indirizzoDiSpedizioneRepository.save(i);
    }

    public IndirizzoDiSpedizione modifyIndirizzo(ModificaIndirizzoPayLoad body, User user) {
        IndirizzoDiSpedizione i = getSingleIndirizzo(user.getIndirizzoSpedizione().getId());
        i.setVia(body.via()==null?i.getVia(): body.via());
        i.setNumero(body.numero()==null?i.getNumero():Integer.parseInt(body.numero()));
        i.setPaese(body.paese()==null?i.getPaese(): body.paese());
        i.setCodice_postale(body.codice_postale()==null?i.getCodice_postale(): body.codice_postale());
        i.setNumero_interno(body.numero_interno()==null?i.getNumero_interno():Integer.parseInt(body.numero_interno()));
        i.setProvincia(body.provincia()==null?i.getProvincia(): body.provincia());
        return indirizzoDiSpedizioneRepository.save(i);
    }

    public void deleteIndirizzo(User user) {
        IndirizzoDiSpedizione i = getSingleIndirizzo(user.getIndirizzoSpedizione().getId());
        indirizzoDiSpedizioneRepository.delete(i);
    }

    public void setIndirizzoInattivo(long id){
        IndirizzoDiSpedizione i=getSingleIndirizzo(id);
        i.setStato(Stato.INATTIVO);
        indirizzoDiSpedizioneRepository.save(i);
    }

}
