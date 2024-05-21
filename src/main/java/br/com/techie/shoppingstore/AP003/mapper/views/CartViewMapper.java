package br.com.techie.shoppingstore.AP003.mapper.views;

import br.com.techie.shoppingstore.AP003.dto.view.CartVIEW;
import br.com.techie.shoppingstore.AP003.mapper.Mapper;
import br.com.techie.shoppingstore.AP003.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartViewMapper implements Mapper<Cart, CartVIEW> {
    @Autowired
    private CartItemViewMapper cartItemViewMapper;

    @Autowired
    private PaymentViewMapper paymentViewMapper;

    @Autowired
    private UserSystemViewMapper userViewMapper;

    @Override
    public CartVIEW map(Cart i) {
        return new CartVIEW(
                i.getId(),
                i.getItems().stream().map(x -> cartItemViewMapper.map(x)).collect(Collectors.toSet()),
                paymentViewMapper.map(i.getPayment()),
                userViewMapper.map(i.getUser()),
                i.getTotalPrice(),
                i.getTotalItems(),
                i.getPurchaseDate(),
                i.getStatus()
        );
    }
}
