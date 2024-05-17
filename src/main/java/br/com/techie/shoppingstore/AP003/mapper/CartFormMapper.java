package br.com.techie.shoppingstore.AP003.mapper;

import br.com.techie.shoppingstore.AP003.dto.form.CartFORM;
import br.com.techie.shoppingstore.AP003.dto.form.CartItemFORM;
import br.com.techie.shoppingstore.AP003.enums.PaymentStatusEnum;
import br.com.techie.shoppingstore.AP003.infra.exception.ProductNotFoundException;
import br.com.techie.shoppingstore.AP003.model.Carrinho;
import br.com.techie.shoppingstore.AP003.model.ItemCarrinho;
import br.com.techie.shoppingstore.AP003.model.Produto;
import br.com.techie.shoppingstore.AP003.model.Usuario;
import br.com.techie.shoppingstore.AP003.repository.ItemCarrinhoRepository;
import br.com.techie.shoppingstore.AP003.repository.ProdutoRepository;
import br.com.techie.shoppingstore.AP003.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class CartFormMapper implements Mapper<CartFORM, Carrinho> {

    @Autowired
    private ProdutoRepository productRepository;

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public Carrinho map(CartFORM i) {
        Carrinho cart = new Carrinho();
        Set<ItemCarrinho> items = new HashSet<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        Usuario user = userRepository.findById(i.user_id()).orElseThrow(() -> new EntityNotFoundException("User not found!"));

        for(CartItemFORM item : i.items()){
            Produto product = productRepository.findById(item.product_id()).orElseThrow(() -> new ProductNotFoundException("Product not found!"));
            ItemCarrinho cartItem = new ItemCarrinho(
                    null,
                    item.quantity(),
                    product.getPreco(),
                    product,
                    cart
            );
            items.add(cartItem);
            totalPrice = totalPrice.add(BigDecimal.valueOf(cartItem.getPreco() * cartItem.getQtd()));
        }

        cart.setDt_pedido(LocalDateTime.now());
        //cart.setStatus(PaymentStatusEnum.PENDING);
        cart.setUsuario(user);
        cart.setItem_carrinho_id(items);
        cart.setQtd_itens(items.size());
        //cart.setPreco_total(totalPrice);

        return cart;
    }
}
