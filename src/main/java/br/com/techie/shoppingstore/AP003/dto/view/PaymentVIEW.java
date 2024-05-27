package br.com.techie.shoppingstore.AP003.dto.view;

import br.com.techie.shoppingstore.AP003.enums.PaymentTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentVIEW(
        Long id,
        String payday,
        BigDecimal amount,
        PaymentTypeEnum paymentType
) { }
