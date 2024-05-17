package br.com.techie.shoppingstore.AP003.dto.form;

import br.com.techie.shoppingstore.AP003.dto.view.CartItemVIEW;
import br.com.techie.shoppingstore.AP003.dto.view.PaymentVIEW;
import br.com.techie.shoppingstore.AP003.dto.view.UserVIEW;

import java.util.Set;

public record CartFORM(
        UserVIEW user,
        PaymentVIEW payment,
        Set<CartItemVIEW> items
) {
    public CartFORM(UserVIEW user, PaymentVIEW payment, Set<CartItemVIEW> items) {
        this.user = user;
        this.payment = payment;
        this.items = items;
    }
}
