package br.com.techie.shoppingstore.AP003.dto.view;

import java.math.BigDecimal;

public record ProductVIEW(
        Long id,
        String name,
        String category,
        BigDecimal price,
        String description,
        Integer stock
) { }
