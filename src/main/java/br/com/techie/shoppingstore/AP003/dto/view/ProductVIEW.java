package br.com.techie.shoppingstore.AP003.dto.view;

import java.math.BigDecimal;

public record ProductVIEW(
        Long id,
        String name,
        String description,
        BigDecimal price,
        String imageUrl
) {
    public ProductVIEW(Long id, String name, String description, BigDecimal price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}
