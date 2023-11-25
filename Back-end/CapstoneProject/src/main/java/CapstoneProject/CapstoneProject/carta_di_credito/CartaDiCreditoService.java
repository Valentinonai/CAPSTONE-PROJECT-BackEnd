package CapstoneProject.CapstoneProject.carta_di_credito;

import CapstoneProject.CapstoneProject.Enum.Stato;
import CapstoneProject.CapstoneProject.exception.NotFoundException;
import CapstoneProject.CapstoneProject.indirizzo_di_spedizione.IndirizzoDiSpedizione;
import CapstoneProject.CapstoneProject.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CartaDiCreditoService {
    @Autowired
    private CartaDiCreditoRepository cartaDiCreditoRepository;

    public Page<CartaDiCredito> getAllCarteDiCredito(int page, int size, String order) {
        Pageable p = PageRequest.of(page, size, Sort.by(order));
        return cartaDiCreditoRepository.findAll(p);
    }

    public CartaDiCredito getSingleCartaDiCredito(long id) {
        return cartaDiCreditoRepository.findById(id).orElseThrow(() -> new NotFoundException("Nessun elemento trovato"));
    }

    public CartaDiCredito saveCartaDiCredito(CartaDiCreditoPayLoad body,User u) {
        CartaDiCredito c = new CartaDiCredito(body.numero_carta(), body.cvv(), body.data_di_scadenza(),u);
        return cartaDiCreditoRepository.save(c);
    }

    public CartaDiCredito modifyCartaDiCredito(ModificaCarta body, User user) {
        CartaDiCredito c = getSingleCartaDiCredito(user.getCartaDiCredito().getId());
        c.setNumero_carta(body.numero_carta()==null?c.getNumero_carta():body.numero_carta());
        c.setCvv(body.cvv()==null?c.getCvv(): body.cvv());
        c.setData_di_scadenza(body.data_di_scadenza()==null?c.getData_di_scadenza(): body.data_di_scadenza());
        return cartaDiCreditoRepository.save(c);
    }

    public void deleteCartaDiCredito(User user) {
        CartaDiCredito c = getSingleCartaDiCredito(user.getCartaDiCredito().getId());
        cartaDiCreditoRepository.delete(c);
    }
    public void setCartaInattiva(long id){
       CartaDiCredito c=getSingleCartaDiCredito(id);
        c.setStato(Stato.INATTIVO);
       cartaDiCreditoRepository.save(c);
    }
}
