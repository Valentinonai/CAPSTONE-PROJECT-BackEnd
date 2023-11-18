package CapstoneProject.CapstoneProject.carta_di_credito;

import CapstoneProject.CapstoneProject.exception.NotFoundException;
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

    public CartaDiCredito saveCartaDiCredito(CartaDiCreditoPayLoad body) {
        CartaDiCredito c = new CartaDiCredito(body.numero_carta(), body.cvv(), body.data_di_scadenza(), body.user());
        return cartaDiCreditoRepository.save(c);
    }

    public CartaDiCredito modifyCartaDiCredito(CartaDiCreditoPayLoad body, User user) {
        CartaDiCredito c = getSingleCartaDiCredito(user.getCartaDiCredito().getId());
        c.setNumero_carta(body.numero_carta());
        c.setCvv(body.cvv());
        c.setData_di_scadenza(body.data_di_scadenza());
        return cartaDiCreditoRepository.save(c);
    }

    public void deleteCartaDiCredito(User user) {
        CartaDiCredito c = getSingleCartaDiCredito(user.getCartaDiCredito().getId());
        cartaDiCreditoRepository.delete(c);
    }
}
