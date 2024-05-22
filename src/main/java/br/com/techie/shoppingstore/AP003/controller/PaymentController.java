package br.com.techie.shoppingstore.AP003.controller;

import br.com.techie.shoppingstore.AP003.dto.form.PaymentFORM;
import br.com.techie.shoppingstore.AP003.dto.view.PaymentVIEW;
import br.com.techie.shoppingstore.AP003.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart/payment")
public class PaymentController {
  @Autowired
  private PaymentService paymentService;

  @GetMapping
  public ResponseEntity<Page<PaymentVIEW>> getAll(Pageable pageable) {
    Page<PaymentVIEW> payments = paymentService.findAllPaged(pageable);
    return ResponseEntity.ok().body(payments);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PaymentVIEW> getById(@PathVariable Long id) {
    PaymentVIEW paymentVIEW = paymentService.findById(id);
    return ResponseEntity.ok().body(paymentVIEW);
  }

  @PostMapping
  public ResponseEntity<PaymentVIEW> create(@RequestBody @Valid PaymentFORM paymentFORM) {
    PaymentVIEW newPayment = paymentService.insert(paymentFORM);
    return ResponseEntity.status(HttpStatus.CREATED).body(newPayment);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    paymentService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
