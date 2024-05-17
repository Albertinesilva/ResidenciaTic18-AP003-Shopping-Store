package br.com.techie.shoppingstore.AP003.dto.form;

import br.com.techie.shoppingstore.AP003.enums.PaymentStatusEnum;
import br.com.techie.shoppingstore.AP003.dto.view.CartItemVIEW;
import br.com.techie.shoppingstore.AP003.dto.view.PaymentVIEW;

import java.time.LocalDateTime;
import java.util.Set;

public record CartUpdateFORM(
        Long cart_id,
        Set<CartItemVIEW> items,
        PaymentVIEW payment,
        LocalDateTime purchase_date,
        PaymentStatusEnum status
) {
    public CartUpdateFORM(Long cart_id, Set<CartItemVIEW> items, PaymentVIEW payment, LocalDateTime purchase_date, PaymentStatusEnum status) {
        this.cart_id = cart_id;
        this.items = items;
        this.payment = payment;
        this.purchase_date = purchase_date;
        this.status = status;
    }
}
