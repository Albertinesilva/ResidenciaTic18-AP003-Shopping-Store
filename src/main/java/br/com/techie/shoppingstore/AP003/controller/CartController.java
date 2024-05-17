package br.com.techie.shoppingstore.AP003.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.techie.shoppingstore.AP003.dto.form.CartFORM;
import br.com.techie.shoppingstore.AP003.dto.form.CartUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.view.CartVIEW;
import br.com.techie.shoppingstore.AP003.dto.view.UserSystemVIEW;
import br.com.techie.shoppingstore.AP003.infra.exception.ErrorMessage;
import br.com.techie.shoppingstore.AP003.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "carts", description = "Contém todas as operações aos recursos para cadastro, edição e leitura de um cart.")
@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;
    
    @Operation(summary = "Listar todos os carts", description = "Carts", security = @SecurityRequirement(name = "security"), responses = {
      @ApiResponse(responseCode = "200", description = "Lista com todos os carts", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserSystemVIEW.class)))),
    })
    @GetMapping
    public ResponseEntity<Page<CartVIEW>> getAll(Pageable pageable) {
        Page<CartVIEW> carts = cartService.findAllPaged(pageable);
        return ResponseEntity.ok().body(carts);
    }
    
    @Operation(summary = "Recuperar cart pelo id", description = "Recuperar carts", security = @SecurityRequirement(name = "security"), responses = {
      @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserSystemVIEW.class))),
      @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar esse recurso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<CartVIEW> getById(@PathVariable Long id) {
        CartVIEW cart = cartService.findById(id);
        return ResponseEntity.ok().body(cart);
    }
    
    @Operation(summary = "Cria um novo cart", description = "Recurso para criar um novo cart no sistema.", responses = {
      @ApiResponse(responseCode = "201", description = "cart criado com sucesso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserSystemVIEW.class))),
      @ApiResponse(responseCode = "422", description = "Recursos não processados por dados de entrada invalidos.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
	})
    @PostMapping
    public ResponseEntity<CartVIEW> create(@RequestBody CartFORM cartForm) {
        CartVIEW novoCart = cartService.insert(cartForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCart);
    }
    
    @Operation(summary = "Atualizar cart", description = "Atualiza um cart", security = @SecurityRequirement(name = "security"), responses = {
      @ApiResponse(responseCode = "204", description = "Cart atualizado com sucesso."),
      @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar esse recurso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "422", description = "Campos invalidos ou formatados incorretamente.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<CartVIEW> update(@PathVariable Long id, @RequestBody CartUpdateFORM cartUpdateForm) {
        CartVIEW cartAtualizado = cartService.update(cartUpdateForm);
        return ResponseEntity.ok().body(cartAtualizado);
    }
    
    @Operation(summary = "Deletar cart", description = "Deleta um cart", security = @SecurityRequirement(name = "security"), responses = {
      @ApiResponse(responseCode = "200", description = "Cart deletado com sucesso."),
      @ApiResponse(responseCode = "403", description = "Usuário sem permissão para acessar esse recurso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
      @ApiResponse(responseCode = "422", description = "Campos invalidos ou formatados incorretamente.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cartService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
