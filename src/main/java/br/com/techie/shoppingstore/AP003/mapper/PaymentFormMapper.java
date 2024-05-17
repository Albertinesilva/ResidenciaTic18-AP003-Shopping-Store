package br.com.techie.shoppingstore.AP003.mapper;

import br.com.techie.shoppingstore.AP003.dto.form.PaymentFORM;
import br.com.techie.shoppingstore.AP003.model.Pagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentFormMapper implements Mapper<PaymentFORM, Pagamento> {

    @Autowired
    Ca

    @Override
    public Pagamento map(PaymentFORM i) {
        return new Pagamento(
                null,
                LocalDateTime.now(),
                null,
                null,
                i.amount()
        ),
    }
}
