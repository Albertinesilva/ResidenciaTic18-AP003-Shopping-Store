package br.com.techie.shoppingstore.AP003.dto.form;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CartItemFORM(
        @NotNull(message = "Product ID must be provided!")
        @JsonAlias({"productId", "id_product", "idProduct"})
        Long product_id,
        @NotNull(message = "Cart ID must be provided!")
        @JsonAlias({"cartId", "id_cart", "idCart"})
        Long cart_id,
        @NotNull(message = "Quantity must be provided!")
        @Min(value = 0, message = "Quantity must be greater or equal than 0!")
        @JsonAlias({"qtd", "qtd_product", "qtdProduct"})
        Integer quantity
) { }
