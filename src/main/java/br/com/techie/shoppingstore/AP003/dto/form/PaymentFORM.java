package br.com.techie.shoppingstore.AP003.dto.form;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentFORM(
        @NotNull
        @Future
        @JsonAlias({"date", "datePayment", "paymentDay"})
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime payday,
        @NotNull(message = "Payment amount is required!")
        @DecimalMin(value = "0.01", message = "Payment amount must be greater than or equal to 0.01")
        BigDecimal amount
) {
}
