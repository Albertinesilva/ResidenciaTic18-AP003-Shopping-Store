package br.com.techie.shoppingstore.AP003.dto.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentVIEW(
        Long id,
        LocalDateTime payday,
        BigDecimal amount
) { }
