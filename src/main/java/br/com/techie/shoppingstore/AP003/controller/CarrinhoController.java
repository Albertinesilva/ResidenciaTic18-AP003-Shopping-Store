package br.com.techie.shoppingstore.AP003.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.techie.shoppingstore.AP003.service.CarrinhoService;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @GetMapping
    public ResponseEntity<Page<CarrinhoForm>> listar(
    		@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10
	) Pageable paginacao) {
        Page<CarrinhoForm> carrinhos = carrinhoService.findAllPaged(paginacao);
        return ResponseEntity.ok().body(carrinhos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarrinhoForm> buscarPorId(@PathVariable Long id) {
        CarrinhoForm carrinho = carrinhoService.findById(id);
        return ResponseEntity.ok().body(carrinho);
    }

    @PostMapping
    public ResponseEntity<CarrinhoForm> criar(@RequestBody CarrinhoForm carrinhoForm) {
        CarrinhoForm novoCarrinho = carrinhoService.insert(carrinhoForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCarrinho);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        carrinhoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

